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

import controleur.Candidat;
import controleur.Controleur;
import controleur.Tableau;

public class PanelCandidat extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JPanel panelListe = new JPanel ();
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTel = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel (); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel lbNbClients = new JLabel();  
	
	public PanelCandidat() {
		super("Gestion des clients");
		
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
		
		this.panelForm.add(new JLabel("Nom Candidat :")); 
		this.panelForm.add(this.txtNom); 
		
		this.panelForm.add(new JLabel("Prenom Candidat :")); 
		this.panelForm.add(this.txtPrenom); 
		
		this.panelForm.add(new JLabel("Telephone :")); 
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(new JLabel("Email :")); 
		this.panelForm.add(this.txtEmail); 
		
		this.panelForm.add(new JLabel("Age :")); 
		this.panelForm.add(this.txtTel); 
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		//on ajoute le formualire dans la vue 
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		String entetes [] = {"ID ","Nom", "Prenom", "Telephone","Email" ,"Age"};
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
					txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(numLigne,2).toString());
					txtAdresse.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
					txtTel.setText(unTableau.getValueAt(numLigne, 5).toString());
					
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
		ArrayList<Candidat> lesCandidats ;
		
		if (filtre.equals("")) {
				lesCandidats = Controleur.selectAllCandidats(); 
		} else {
			lesCandidats = Controleur.selectLikeCandidats(filtre); 
		}
		
		//création d'une matrice de données 
		Object[][] matrice = new Object[lesCandidats.size()][6]; 
		int i = 0; 
		for (Candidat unCandidat : lesCandidats) {
			matrice[i][0] = unCandidat.getIdCandidat(); 
			matrice[i][1] = unCandidat.getNom(); 
			matrice[i][2] = unCandidat.getPrenom(); 
			matrice[i][3] = unCandidat.getTelephone();
			matrice[i][4] = unCandidat.getEmail(); 
			matrice[i][5] = unCandidat.getAge();
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//recuperer les champs saisis
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText();
			String tel = this.txtAdresse.getText();
			String email = this.txtEmail.getText();
			int age = Integer.parseInt(this.txtTel.getText());
			String mdp =""; 
			
			//instancier la classe Client 
			Candidat unCandidat = new Candidat(nom, prenom,age, email, mdp, tel); 
			
			//inserer le client dans la BDD 
			Controleur.insertCandidat(unCandidat);
			
			//on affiche un message d'insertion reussie 
			JOptionPane.showMessageDialog(this, "Insertion réussie du candidat.");
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
			
			//on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//on recupere l'id du client a supprimer 
			int numLigne , idcandidat ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idcandidat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le candidat ?", 
					"Suppression du client", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
						//on supprime de la base de données 
						Controleur.deleteCandidat(idcandidat);
			
						//on actualise l'affichage 
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du candidat.");
						this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
						
						//on vide les champs 
						this.txtNom.setText("");
						this.txtPrenom.setText("");
						this.txtAdresse.setText("");
						this.txtEmail.setText("");
						this.txtTel.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			int numLigne , idcandidat ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idcandidat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText();
			String tel = this.txtAdresse.getText();
			String email = this.txtEmail.getText();
			int age = Integer.parseInt(this.txtTel.getText());
			String mdp = ""; 
			//on modifie dans la bdd 
			Candidat unCandidat = new Candidat(idcandidat, nom, prenom,age, email, mdp, tel); 
			Controleur.updateCandidat(unCandidat);
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du candidat.");
			
			//message de confirmation et on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}
		else if (e.getSource() == this.btFiltrer) {
			
			//recuperer le fitre
			String filtre = this.txtFiltre.getText(); 
			
			//on actualise l'affichage du tableau avec les Candidats trouves 
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		}
	}
}
