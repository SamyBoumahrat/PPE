package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controleur.Castellane;
 
public class VueGenerale extends JFrame implements ActionListener
{
	private JButton btCandidat = new JButton("Candidats");
	private JButton btMoniteur = new JButton("Moniteurs");
	private JButton btExamen = new JButton("Examens");
	private JButton btVehicule = new JButton("Vehicule");
	 
	private JButton btStats = new JButton("Statistiques");
	private JButton btQuitter = new JButton("Quitter");
	private JPanel panelMenu = new JPanel (); 
	 
	private static PanelCandidat unPanelCandidat = new PanelCandidat(); 
	private static PanelExamen unPanelExamen = new PanelExamen(); 
	private static PanelMoniteur unPanelMoniteur = new PanelMoniteur(); 
	private static PanelVehicule unPanelVehicule = new PanelVehicule();
	private static PanelStats unPanelStats = new PanelStats(); 
	public VueGenerale() {
		 this.setTitle("Application Orange 2025");
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setResizable(false);
		 this.setBounds(100, 100, 1000, 600);
		 this.setLayout(null);
		 this.getContentPane().setBackground(Color.darkGray);
		 //construction du panel Menu 
		 this.panelMenu.setBackground(Color.darkGray);
		 this.panelMenu.setBounds(50, 10, 900, 40);
		 this.panelMenu.setLayout(new GridLayout(1, 6));
		   
		 this.panelMenu.add(this.btCandidat); 
		 this.panelMenu.add(this.btExamen); 
		 this.panelMenu.add(this.btMoniteur); 
		 this.panelMenu.add(this.btVehicule); 
		 this.panelMenu.add(this.btStats); 
		 this.panelMenu.add(this.btQuitter); 
		 this.add(this.panelMenu); 
		 //rendre les boutons ecoutables 
		// this.btAccueil.addActionListener(this);
		 this.btCandidat.addActionListener(this);
		 this.btExamen.addActionListener(this);
		 this.btMoniteur.addActionListener(this);
		 this.btVehicule.addActionListener(this);
		 this.btQuitter.addActionListener(this);
		 //ajouts des panels à la fenetre 
		 
		 this.add(this.unPanelCandidat);
		 this.add(this.unPanelExamen);
		 this.add(this.unPanelMoniteur);
		 this.add(this.unPanelVehicule);
		 this.add(this.unPanelStats);
		 this.setVisible(true);
	}
	public void afficherPanel (int choix) {
		 
		this.unPanelCandidat.setVisible(false);
		this.unPanelExamen.setVisible(false);
		this.unPanelMoniteur.setVisible(false);
		this.unPanelVehicule.setVisible(false);
		this.unPanelStats.setVisible(false);
		switch (choix) {
		
		case 1 : this.unPanelCandidat.setVisible(true); break;
		case 3: this.unPanelExamen.setVisible(true); break;
		case 2 : this.unPanelMoniteur.setVisible(true); break;
		case 4 : this.unPanelVehicule.setVisible(true); break;
		case 5 : this.unPanelStats.setVisible(true); break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btQuitter) {
			 int retour = JOptionPane.showConfirmDialog(this,
					 "Voulez-vous quitter l'application ?", 
					 "Quitter l'application", JOptionPane.YES_NO_OPTION); 
			 if (retour ==0) {
			 Castellane.rendreVisible(true); //ouverture de nouveau de la connexion
			 Castellane.creerVueGenerale(false); //fermeture de la vue genérale 
			 }
		 }
		 
		 else if (e.getSource() == this.btCandidat) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btExamen) {
			 this.afficherPanel(3);
		 }
		 else if (e.getSource() == this.btMoniteur) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btVehicule) {
			 this.afficherPanel(4);
		 }
		 else if (e.getSource() == this.btStats) {
			 this.afficherPanel(5);
		 }
	}
 
}