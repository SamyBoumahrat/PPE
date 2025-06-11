package vue;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controleur.Controleur;
import controleur.Tableau;
import controleur.VCandidat;
 
 
public class PanelStats extends PanelPrincipal {
 
	private static JTable uneTable;
	private JPanel panelCount = new JPanel();
 
	public PanelStats() {
		super ("Gestion des statistiques");
		String entetes []= {"nom, prenom, email, mdp, role, tel"};
		 JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(20,80,400,350);
		this.add(uneScroll);
		this.add(uneScroll);
		this.panelCount.setBackground(Color.cyan);
		this.panelCount.setLayout(new GridLayout(2,2));
		this.panelCount.setBounds(400,80,300,200);
		int nbAccueil = Controleur.count("Accueil");
		int nbCandidat = Controleur.count("Candidat");
		int nbExamen = Controleur.count("Examen");
		int nbMoniteur = Controleur.count("Moniteur");
		int nbVehicule = Controleur.count("Vehicule");

		this.panelCount.add(new JLabel("nombre de Accueil : " + nbAccueil));
		this.panelCount.add(new JLabel("nombre de Candidat : " + nbCandidat));
		this.panelCount.add(new JLabel("nombre de Examen : " + nbExamen));
		this.panelCount.add(new JLabel("nombre de Moniteur : " + nbMoniteur));
        this.panelCount.add(new JLabel("nombre de Vehicule : " + nbVehicule));
	}
}