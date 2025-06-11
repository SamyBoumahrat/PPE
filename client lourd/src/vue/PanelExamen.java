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

import controleur.Examen;
import controleur.Controleur;
import controleur.Tableau;

public class PanelExamen extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new JPanel (); 
	private JPanel panelListe = new JPanel ();
	
	private JTextField txtidexamen = new JTextField(); 
	private JTextField txtdateExamen = new JTextField();
	private JTextField txtlieu = new JTextField();
	private JTextField txttypePermis = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel (); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel lbNbClients = new JLabel();  
	
	public PanelExamen() {
		super("Gestion des Examens");
		
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
		
		
		
		this.panelForm.add(new JLabel("dateExamen Client :")); 
		this.panelForm.add(this.txtdateExamen); 
		
		this.panelForm.add(new JLabel("lieu :")); 
		this.panelForm.add(this.txtlieu);
		
		this.panelForm.add(new JLabel("typePermis :")); 
		this.panelForm.add(this.txttypePermis); 
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		//on ajoute le formualire dans la vue 
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		String entetes [] = {"idexamen", "dateExamen", "lieu","typePermis" ,};
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
					txtidexamen.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtdateExamen.setText(unTableau.getValueAt(numLigne,2).toString());
					txtlieu.setText(unTableau.getValueAt(numLigne, 3).toString());
					txttypePermis.setText(unTableau.getValueAt(numLigne, 4).toString()) ;
					
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
		ArrayList<Examen> lesExamens ;
		
		if (filtre.equals("")) {
				lesExamens = Controleur.selectAllExamen(); 
		} else {
			lesExamens = Controleur.selectLikeExamen(filtre); 
		}
		
		//création d'une matrice de données 
		Object[][] matrice = new Object[lesExamens.size()][6]; 
		int i = 0; 
		for (Examen unExamen : lesExamens) {
			matrice[i][0] = unExamen.getIdExamen(); 
			matrice[i][1] = unExamen.getDateExamen(); 
			matrice[i][2] = unExamen.getLieu(); 
			matrice[i][3] = unExamen.getTypePermis(); 
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtidexamen.setText("");
			this.txtdateExamen.setText("");
			this.txtlieu.setText("");
			this.txttypePermis.setText("");
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//recuperer les champs saisis
			//int idexamen = this.txtidexamen.getText(); 
			String dateExamen = this.txtdateExamen.getText();
			String lieu = this.txtlieu.getText();
			String typePermis = this.txttypePermis.getText();
			
			//instancier la classe Examen 
			Examen unExamen = new Examen( dateExamen, lieu, typePermis ); 
			
			//inserer le client dans la BDD 
			Controleur.insertExamen(unExamen);
			
			//on affiche un message d'insertion reussie 
			JOptionPane.showMessageDialog(this, "Insertion réussie du client.");
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
			
			//on vide les champs 
			this.txtidexamen.setText("");
			this.txtdateExamen.setText("");
			this.txtlieu.setText("");
			this.txttypePermis.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//on recupere l'id du client a supprimer 
			int numLigne , idExamen ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idExamen = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le client ?", 
					"Suppression du client", JOptionPane.YES_NO_OPTION);
			if (retour ==0) {
						//on supprime de la base de données 
						Controleur.deleteExamen(idExamen);
			
						//on actualise l'affichage 
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du client.");
						this.lbNbClients.setText("Nombre de clients : " + this.unTableau.getRowCount());
						
						//on vide les champs 
						this.txtidexamen.setText("");
						this.txtdateExamen.setText("");
						this.txtlieu.setText("");
						this.txttypePermis.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			int numLigne , idexamen ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idexamen = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			  
			String dateExamen = this.txtdateExamen.getText();
			String lieu = this.txtlieu.getText();
			String typePermis = this.txttypePermis.getText();
			
			//on modifie dans la bdd 
			Examen unExamen = new Examen(idexamen, dateExamen, lieu, typePermis); 
			Controleur.updateExamen(unExamen);
			
			//on actualise l'affichage du tableau 
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du client.");
			
			//message de confirmation et on vide les champs 
			this.txtidexamen.setText("");
			this.txtdateExamen.setText("");
			this.txtlieu.setText("");
			this.txttypePermis.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}
		else if (e.getSource() == this.btFiltrer) {
			
			//recuperer le fitre
			String filtre = this.txtFiltre.getText(); 
			
			//on actualise l'affichage du tableau avec les Examens trouves 
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		}
	}
}


















