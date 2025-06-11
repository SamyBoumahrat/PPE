package controleur;
 
import java.util.ArrayList;
 
 
import modele.modele;

 
public class Controleur {
	/************************* Gestion des candidats ******************/
	public static void insertCandidat (Candidat unCandidat) {
		modele.insertCandidat(unCandidat);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	public static void deleteCandidat(int idCandidat) {
		modele.deleteCandidat(idCandidat);
	}
	public static void updateCandidat(Candidat unCandidat) {
		modele.updateCandidat(unCandidat);
	}
	public static Candidat selectWhereCandidat(int idCandidat) {
		return modele.selectWhereCandidat(idCandidat);
	}
	public static ArrayList<Candidat> selectAllCandidats(){
		return modele.selectAllCandidats(); 
	}
	public static ArrayList<Candidat> selectLikeCandidats(String filtre){
		return modele.selectLikeCandidats(filtre); 
	}
	public static ArrayList<VCandidat> selectAllVCandidats(){
		return modele.selectAllVCandidats();
	}
	/************************* Gestion des Examens ******************/
	public static void insertExamen (Examen unExamen) {
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
		modele.insertExamen(unExamen);
	}
	public static void deleteExamen(int idExamen) {
		modele.deleteExamen(idExamen);
	}
	public static void updateExamen(Examen unExamen) {
		modele.updateExamen(unExamen);
	}
	public static Examen selectWhereExamen(int idExamen) {
		return modele.selectWhereExamen(idExamen);
	}
	public static ArrayList<Examen> selectAllExamen(){
		return modele.selectAllExamen(); 
	}
	public static ArrayList<Examen> selectLikeExamen(String filtre){
		return modele.selectLikeExamens(filtre); 
	}
	/************************* Gestion des Moniteurs ******************/
	public static void insertMoniteur (Moniteur unMoniteur) {
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
		modele.insertMoniteur(unMoniteur);
	}
	public static void deleteMoniteur(int idMoniteur) {
		modele.deleteMoniteur(idMoniteur);
	}
	public static void updateMoniteur(Moniteur unMoniteur) {
		modele.updateMoniteur(unMoniteur);
	}
	public static Moniteur selectWhereMoniteur(int idMoniteur) {
		return modele.selectWhereMoniteur(idMoniteur);
	}
	public static ArrayList<Moniteur> selectAllMoniteurs(){
		return modele.selectAllMoniteurs(); 
	}
	public static ArrayList<Moniteur> selectLikeMoniteurs(String filtre){
		return modele.selectLikeMoniteurs(filtre); 
	}

	public static Moniteur selectWhereMoniteur(String email, String mdp) {
		return modele.selectWhereMoniteur(email, mdp);
	}
	/************************* Gestion des Vehicules ******************/
	public static void insertVehicule (Vehicule unvehicule) {
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
		modele.insertVehicule(unvehicule);
	}
	public static void deletevehicule(int idvehicule) {
		modele.deleteVehicule(idvehicule);
	}
	public static void updatevehicule(Vehicule unvehicule) {
		modele.updateVehicule(unvehicule);
	}
	public static Vehicule selectWhereVehicule(int idVehicule) {
		return modele.selectWhereVehicule(idVehicule);
	}
	public static ArrayList<Vehicule> selectAllVehicules(){
		return modele.selectAllVehicules(); 
	}
	public static ArrayList<Vehicule> selectLikevehicule(String filtre){
		return modele.selectLikeVehicules(filtre); 
	}
	public static int count(String table) {
		return modele.count(table);
	}

}