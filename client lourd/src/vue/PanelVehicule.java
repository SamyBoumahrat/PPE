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

import controleur.Vehicule;
import controleur.Controleur;
import controleur.Tableau;

public class PanelVehicule extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JPanel panelListe = new JPanel ();
	
	private JTextField txtidvehicule = new JTextField(); 
	private JTextField txtmarque = new JTextField();
	private JTextField txtmodele = new JTextField();
	private JTextField txtmatricule = new JTextField();
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
	
	public PanelVehicule() {
		super("Gestion des Vehicules");
		
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
		
		this.panelForm.add(new JLabel("marque Client :")); 
		this.panelForm.add(this.txtidvehicule); 
		
		this.panelForm.add(new JLabel("modele Client :")); 
		this.panelForm.add(this.txtmarque); 
		
		this.panelForm.add(new JLabel("matricule Client :")); 
		this.panelForm.add(this.txtmodele);
		
		this.panelForm.add(new JLabel("Tel :")); 
		this.panelForm.add(this.txtmatricule);  
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		//on ajoute le formualire dans la vue 
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		String entetes [] = {"ID","marque", "modele", "matricule","Tel"};
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
					txtidvehicule.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtmarque.setText(unTableau.getValueAt(numLigne,2).toString());
					txtmodele.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtmatricule.setText(unTableau.getValueAt(numLigne, 4).toString());
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
		ArrayList<Vehicule> lesVehicules ;
		
		if (filtre.equals("")) {
				lesVehicules = Controleur.selectAllVehicules(); 
		} else {
			lesVehicules = Controleur.selectLikevehicule(filtre); 
		}
		
		//création d'une matrice de données 
		Object[][] matrice = new Object[lesVehicules.size()][6]; 
		int i = 0; 
		for (Vehicule unVehicule : lesVehicules) {
			matrice[i][0] = unVehicule.getIdVehicule(); 
			matrice[i][1] = unVehicule.getMarque(); 
			matrice[i][2] = unVehicule.getModele(); 
			matrice[i][3] = unVehicule.getMatricule();  
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtidvehicule.setText("");
			this.txtmarque.setText("");
			this.txtmodele.setText("");
			this.txtmatricule.setText("");
			this.txtTel.setText("");
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//recuperer les champs saisis
			
			String marque = this.txtmarque.getText();
			String modele = this.txtmodele.getText();
			String matricule = this.txtmatricule.getText();
			String tel = this.txtTel.getText();
			
			//instancier la classe Vehicule 
			Vehicule unVehicule = new Vehicule( marque, modele, matricule); 
			
			//inserer le client dans la BDD 
			Controleur.insertVehicule(unVehicule);
			
			//on affiche un message d'insertion reussie 
			JOptionPane.showMessageDialog(this, "Insertion réussie du client.");
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
			
			//on vide les champs 
			this.txtidvehicule.setText("");
			this.txtmarque.setText("");
			this.txtmodele.setText("");
			this.txtmatricule.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//on recupere l'id du client a supprimer 
			int numLigne , idVehicule ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idVehicule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le client ?", 
					"Suppression du client", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
						//on supprime de la base de données 
						Controleur.deletevehicule(idVehicule);
			
						//on actualise l'affichage 
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du client.");
						this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
						
						//on vide les champs 
						this.txtidvehicule.setText("");
						this.txtmarque.setText("");
						this.txtmodele.setText("");
						this.txtmatricule.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			int numLigne , idvehicule ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idvehicule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String idvehicules = this.txtidvehicule.getText(); 
			String marque = this.txtmarque.getText();
			String modele = this.txtmodele.getText();
			String matricule = this.txtmatricule.getText();
			String tel = this.txtTel.getText();
			
			//on modifie dans la bdd 
			Vehicule unVehicule = new Vehicule(idvehicule, marque, modele, matricule); 
			Controleur.updatevehicule(unVehicule);
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du client.");
			
			//message de confirmation et on vide les champs 
			this.txtidvehicule.setText("");
			this.txtmarque.setText("");
			this.txtmodele.setText("");
			this.txtmatricule.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}
		else if (e.getSource() == this.btFiltrer) {
			
			//recuperer le fitre
			String filtre = this.txtFiltre.getText(); 
			
			//on actualise l'affichage du tableau avec les Vehicules trouves 
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		}
	}
}


















