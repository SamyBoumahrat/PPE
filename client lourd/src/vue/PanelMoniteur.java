package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import controleur.Moniteur;
import controleur.Controleur;
import controleur.Tableau;

public class PanelMoniteur extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JPanel panelListe = new JPanel ();
	
	private JTextField txtidmoniteur = new JTextField(); 
	private JTextField txtnom = new JTextField();
	private JTextField txtprenom = new JTextField();
	private JTextField txtemail = new JTextField();
	private JTextField txtmdp = new JTextField();
	private JTextField txtrole = new JTextField();
	private JTextField txttel = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel (); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel lbNbClients = new JLabel();  
	
	public PanelMoniteur() {
		super("Gestion des Moniteurs");
		
		//installation du bouton supprimer 
		this.btSupprimer.setBounds(40, 340, 300, 40);
		this.add(this.btSupprimer); 
		this.btSupprimer.setVisible(false);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.addActionListener(this);
		
		//installation du Panel Formulaire 
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(40, 80, 300, 220);
		this.panelForm.setLayout(new GridLayout(6,2));
		
		this.panelForm.add(new JLabel("idmoniteur Client :")); 
		this.panelForm.add(this.txtidmoniteur); 
		
		this.panelForm.add(new JLabel("nom Client :")); 
		this.panelForm.add(this.txtnom); 
		
		this.panelForm.add(new JLabel("prenom Postale :")); 
		this.panelForm.add(this.txtprenom);
		
		this.panelForm.add(new JLabel("Email :")); 
		this.panelForm.add(this.txtemail); 
		
		this.panelForm.add(new JLabel("mdp :")); 
		this.panelForm.add(this.txtmdp); 
		
		this.panelForm.add(new JLabel("role :")); 
		this.panelForm.add(this.txtrole); 
		
		this.panelForm.add(new JLabel("tel :")); 
		this.panelForm.add(this.txttel); 
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		//on ajoute le formualire dans la vue 
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		String entetes [] = {"ID","Nom", "prenom", "Email","mdp" ,"role","tel"};
		this.unTableau = new Tableau (this.obtenirDonnees(""), entetes); 
		this.uneTable = new JTable(this.unTableau); 
		JScrollPane uneScroll = new JScrollPane(this.uneTable); 
		
		uneScroll.setBounds(400, 80, 500, 340);
		this.add(uneScroll); 
		
		//implementation du click sur une ligne de la table 
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0 ; 
				
				if (e.getClickCount() >= 1) {
					numLigne = uneTable.getSelectedRow(); 
					txtidmoniteur.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtnom.setText(unTableau.getValueAt(numLigne,2).toString());
					txtprenom.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtemail.setText(unTableau.getValueAt(numLigne, 4).toString());
					txtmdp.setText(unTableau.getValueAt(numLigne, 5).toString());
					txtrole.setText(unTableau.getValueAt(numLigne, 4).toString());
					txttel.setText(unTableau.getValueAt(numLigne, 5).toString());
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
				
			}
		});
		//installation du panel filtre 
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 500, 20);
		this.panelFiltre.add(new JLabel("Filtrer les clients par : ")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer); 
		this.btFiltrer.addActionListener(this);
		this.add(this.panelFiltre);
		
		//installation du Label Nb Clients 
		this.lbNbClients.setBounds(580, 430, 400, 20);
		this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
		this.add(this.lbNbClients); 
		
	}
	
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les clients de la base de données 
		ArrayList<Moniteur> lesMoniteurs ;
		
		if (filtre.equals("")) {
				lesMoniteurs = Controleur.selectAllMoniteurs(); 
		} else {
			lesMoniteurs = Controleur.selectLikeMoniteurs(filtre); 
		}
		
		//création d'une matrice de données 
		Object[][] matrice = new Object[lesMoniteurs.size()][6]; 
		int i = 0; 
		for (Moniteur unMoniteur : lesMoniteurs) {
			matrice[i][0] = unMoniteur.getIdMoniteur(); 
			matrice[i][1] = unMoniteur.getNom(); 
			matrice[i][2] = unMoniteur.getPrenom(); 
			matrice[i][3] = unMoniteur.getEmail(); 
			matrice[i][4] = unMoniteur.getMdp(); 
			matrice[i][5] = unMoniteur.getRole(); 
			matrice[i][6] = unMoniteur.getTel();
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtidmoniteur.setText("");
			this.txtnom.setText("");
			this.txtprenom.setText("");
			this.txtemail.setText("");
			this.txtmdp.setText("");
			this.txtrole.setText("");
			this.txttel.setText("");
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//recuperer les champs saisis
			 
			String nom = this.txtnom.getText();
			String prenom = this.txtprenom.getText();
			String email = this.txtemail.getText();
			String mdp = this.txtmdp.getText();
			String role = this.txtrole.getText();
			String tel = this.txttel.getText();
			
			
			//instancier la classe Moniteur
			Moniteur unMoniteur = new Moniteur( nom, prenom, email, mdp, role, tel); 
			
			//inserer le client dans la BDD 
			Controleur.insertMoniteur(unMoniteur);
			
			//on affiche un message d'insertion reussie 
			JOptionPane.showMessageDialog(this, "Insertion réussie du client.");
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
			
			//on vide les champs 
			this.txtidmoniteur.setText("");
			this.txtnom.setText("");
			this.txtprenom.setText("");
			this.txtemail.setText("");
			this.txtmdp.setText("");
			this.txtrole.setText("");
			this.txttel.setText("");

			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//on recupere l'id du client a supprimer 
			int numLigne , idclient ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le client ?", 
					"Suppression du client", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
						//on supprime de la base de données 
						Controleur.deleteMoniteur(idclient);
			
						//on actualise l'affichage 
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du client.");
						this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
						
						//on vide les champs 
						this.txtidmoniteur.setText("");
						this.txtnom.setText("");
						this.txtprenom.setText("");
						this.txtemail.setText("");
						this.txtmdp.setText("");
						this.txtrole.setText("");
						this.txttel.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			int numLigne , idmoniteur ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idmoniteur = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			 
			String nom = this.txtnom.getText();
			String prenom = this.txtprenom.getText();
			String email = this.txtemail.getText();
			String mdp = this.txtmdp.getText();
			String role = this.txtrole.getText();
			String tel = this.txttel.getText();
			
			//on modifie dans la bdd 
			Moniteur unMoniteur = new Moniteur(idmoniteur, nom, prenom, email, mdp, role, tel); 
			Controleur.updateMoniteur(unMoniteur);
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du client.");
			
			//message de confirmation et on vide les champs 
			this.txtidmoniteur.setText("");
			this.txtnom.setText("");
			this.txtprenom.setText("");
			this.txtemail.setText("");
			this.txtmdp.setText("");
			this.txtrole.setText("");
			this.txttel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}
		else if (e.getSource() == this.btFiltrer) {
			
			//recuperer le fitre
			String filtre = this.txtFiltre.getText(); 
			
			//on actualise l'affichage du tableau avec les Moniteur trouves 
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		}
	}
}


















