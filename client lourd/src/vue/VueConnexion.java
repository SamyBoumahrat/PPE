package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Moniteur;
import controleur.Castellane;


public class VueConnexion extends JFrame implements ActionListener 
{
    private JButton btSeConnecter = new JButton("Se Connecter") ; 
    private JButton btAnnuler = new JButton("Annuler") ;
    private JTextField txtEmail = new JTextField(""); 
    private JPasswordField txtMdp = new JPasswordField(""); 

    private JPanel panelForm = new JPanel (); 

    public VueConnexion() {
        this.setTitle("Application Auto-Ecole-2025");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 600, 300);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);

        //ajout de l'image logo
        ImageIcon uneImage = new ImageIcon("src/images/logo.png");
        JLabel unLogo = new JLabel(uneImage); 
        unLogo.setBounds(40, 40, 200, 200);
        this.add(unLogo);

        //Construction du panel Formulaire 
        this.panelForm.setBounds(280, 40, 260, 200);
        this.panelForm.setBackground(Color.darkGray);
        this.panelForm.setLayout(new GridLayout(3,2)); //matrice de 3 lignes et de 2 colonnes
        this.panelForm.add(new JLabel("Email")); 
        this.panelForm.add(this.txtEmail);

        this.panelForm.add(new JLabel("MDP")); 
        this.panelForm.add(this.txtMdp);

        this.panelForm.add(this.btAnnuler); 
        this.panelForm.add(this.btSeConnecter);

        this.add(this.panelForm); //ajout du panel dans la fenetre 

        //rendre les boutons ecoutables 
        this.btAnnuler.addActionListener(this);
        this.btSeConnecter.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == this.btAnnuler) {
             this.txtEmail.setText("");
             this.txtMdp.setText("");
         }else if (e.getSource() == this.btSeConnecter) {

             String email = this.txtEmail.getText(); 
             String mdp = new String (this.txtMdp.getPassword()); 

             //on v�rifie la pr�sence du moniteur dans la BDD 
             Moniteur unMoniteur = Controleur.selectWhereMoniteur(email, mdp); 
             if (unMoniteur == null) {
                 JOptionPane.showMessageDialog(this, "Veuillez v�rifier vos idenifiants !", 
                         "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
             }else {
                 JOptionPane.showMessageDialog(this, "Bienvenue Nom : "+unMoniteur.getNom()
                 + " Pr�nom : "+unMoniteur.getPrenom(), 
                         "Connexion � Auto_Ecole_2025 Application", JOptionPane.INFORMATION_MESSAGE);

                 Castellane.rendreVisible(false); //fermeture de la connexion

                 Castellane.creerVueGenerale(true); //ouverture du logiciel 

             }
         }
    }
}