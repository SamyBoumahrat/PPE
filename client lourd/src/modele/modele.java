package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Candidat;
import controleur.Examen;
import controleur.Moniteur;
import controleur.VCandidat;
import controleur.Vehicule;

public class modele {
    private static Connexion uneConnexion = new Connexion("localhost", "autoecole", "root", "");
	//private static Connexion uneConnexion=new Connexion ("172.20.111.115","user","user","");


    /*********************************** Gestion des Candidats *******************************/
    public static void insertCandidat(Candidat unCandidat) {
        String requete = String.format(
                "INSERT INTO candidat VALUES (null, '%s', '%s', '%d', '%s', '%s', '%s');",
                unCandidat.getNom(), unCandidat.getPrenom(), unCandidat.getAge(),
                unCandidat.getEmail(), unCandidat.getMotDePasse(), unCandidat.getTelephone());
        executerRequete(requete);
    }

    /*********************************** Gestion des Examens *******************************/
    public static void insertExamen(Examen unExamen) {
        String requete = String.format(
                "INSERT INTO examen VALUES (null, '%s', '%s', '%s');",
                unExamen.getDateExamen(), unExamen.getLieu(), unExamen.getTypePermis());
        executerRequete(requete);
    }

    /*********************************** Gestion des Moniteurs *******************************/
    public static void insertMoniteur(Moniteur unMoniteur) {
        String requete = String.format(
                "INSERT INTO moniteur VALUES (null, '%s', '%s', '%s', '%s', '%s', '%s');",
                unMoniteur.getNom(), unMoniteur.getPrenom(), unMoniteur.getEmail(),
                unMoniteur.getMdp(), unMoniteur.getRole(), unMoniteur.getTel());
        executerRequete(requete);
    }

    /*********************************** Gestion des Vehicules *******************************/
    public static void insertVehicule(Vehicule unVehicule) {
        String requete = String.format(
                "INSERT INTO vehicule VALUES (null, '%s', '%s', '%s');",
                unVehicule.getMarque(), unVehicule.getModele(), unVehicule.getMatricule());
        executerRequete(requete);
    }

    /*********************************** Suppression des �l�ments ****************************/
    public static void deleteCandidat(int idCandidat) {
        String requete = "DELETE FROM candidat WHERE idCandidat = " + idCandidat + ";";
        executerRequete(requete);
    }

    public static void deleteExamen(int idexamen) {
        String requete = "DELETE FROM examen WHERE idexamen = " + idexamen + ";";
        executerRequete(requete);
    }

    public static void deleteMoniteur(int idmoniteur) {
        String requete = "DELETE FROM moniteur WHERE idmoniteur = " + idmoniteur + ";";
        executerRequete(requete);
    }

    public static void deleteVehicule(int idvehicule) {
        String requete = "DELETE FROM vehicule WHERE idvehicule = " + idvehicule + ";";
        executerRequete(requete);
    }

    /*********************************** Mise � jour des �l�ments ****************************/
    public static void updateCandidat(Candidat unCandidat) {
        String requete = "UPDATE candidat SET nom = '" + unCandidat.getNom() +
                "', prenom = '" + unCandidat.getPrenom() + "', age = " + unCandidat.getAge() +
                ", email = '" + unCandidat.getEmail() + "', mdp = '" + unCandidat.getMotDePasse() +
                "', tel = '" + unCandidat.getTelephone() + "' WHERE idCandidat = " + unCandidat.getIdCandidat() + ";";
        executerRequete(requete);
    }

    public static void updateExamen(Examen unExamen) {
        String requete = "UPDATE examen SET dateExamen = '" + unExamen.getDateExamen() +
                "', lieu = '" + unExamen.getLieu() + "', typePermis = '" + unExamen.getTypePermis() +
                "' WHERE idexamen = " + unExamen.getIdExamen() + ";";
        executerRequete(requete);
    }

    public static void updateMoniteur(Moniteur unMoniteur) {
        String requete = "UPDATE moniteur SET nom = '" + unMoniteur.getNom() +
                "', prenom = '" + unMoniteur.getPrenom() + "', email = '" + unMoniteur.getEmail() +
                "', mdp = '" + unMoniteur.getMdp() + "', role = '" + unMoniteur.getRole() +
                "', tel = '" + unMoniteur.getTel() + "' WHERE idmoniteur = " + unMoniteur.getIdMoniteur() + ";";
        executerRequete(requete);
    }

    public static void updateVehicule(Vehicule unVehicule) {
        String requete = "UPDATE vehicule SET marque = '" + unVehicule.getMarque() +
                "', modele = '" + unVehicule.getModele() + "', immatriculation = '" + unVehicule.getMatricule() +
                "' WHERE idvehicule = " + unVehicule.getIdVehicule() + ";";
        executerRequete(requete);
    }

    /*********************************** S�lection des �l�ments ******************************/
    public static Candidat selectWhereCandidat(int idCandidat) {
        String requete = "SELECT * FROM candidat WHERE idCandidat = " + idCandidat + ";";
        Candidat unCandidat = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete); // fetch
            if (lesResultats.next()) {
                unCandidat = new Candidat(
                        lesResultats.getInt("idCandidat"), lesResultats.getString("nom"),
                        lesResultats.getString("prenom"), lesResultats.getInt("age"),
                        lesResultats.getString("email"), lesResultats.getString("mdp"),
                        lesResultats.getString("tel")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te: " + requete);
        }
        return unCandidat;
    }

    public static Examen selectWhereExamen(int idexamen) {
        String requete = "SELECT * FROM examen WHERE idexamen = " + idexamen + ";";
        Examen unExamen = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete); // fetch
            if (lesResultats.next()) {
                unExamen = new Examen(
                        lesResultats.getInt("idexamen"), lesResultats.getString("dateExamen"),
                        lesResultats.getString("lieu"), lesResultats.getString("typePermis")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te: " + requete);
        }
        return unExamen;
    }

    public static Moniteur selectWhereMoniteur(int idmoniteur) {
        String requete = "SELECT * FROM moniteur WHERE idmoniteur = " + idmoniteur + ";";
        Moniteur unMoniteur = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete); // fetch
            if (lesResultats.next()) {
                unMoniteur = new Moniteur(
                        lesResultats.getInt("idmoniteur"), lesResultats.getString("nom"),
                        lesResultats.getString("prenom"), lesResultats.getString("email"),
                        lesResultats.getString("mdp"), lesResultats.getString("role"),
                        lesResultats.getString("tel")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te: " + requete);
        }
        return unMoniteur;
    }
    public static Moniteur selectWhereMoniteur(String email, String mdp) {
        String requete = "SELECT * FROM moniteur WHERE email = '" + email + "' and mdp ='"+mdp+"';";
        Moniteur unMoniteur = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete); // fetch
            if (lesResultats.next()) {
                unMoniteur = new Moniteur(
                        lesResultats.getInt("idmoniteur"), lesResultats.getString("nom"),
                        lesResultats.getString("prenom"), lesResultats.getString("email"),
                        lesResultats.getString("mdp"), lesResultats.getString("role"),
                        lesResultats.getString("tel")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te: " + requete);
        }
        return unMoniteur;
    }

    public static Vehicule selectWhereVehicule(int idvehicule) {
        String requete = "SELECT * FROM vehicule WHERE idvehicule = " + idvehicule + ";";
        Vehicule unVehicule = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete); // fetch
            if (lesResultats.next()) {
                unVehicule = new Vehicule(
                        lesResultats.getInt("idvehicule"), lesResultats.getString("marque"),
                        lesResultats.getString("modele"), lesResultats.getString("immatriculation")
                );
            }
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te: " + requete);
        }
        return unVehicule;
    }
    /************************select all************************/
    public static ArrayList<Candidat> selectAllCandidats() {
        String requete = "SELECT * FROM candidat;";
        ArrayList<Candidat> lesCandidats = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Candidat unCandidat = new Candidat(
                        lesResultats.getInt("idcandidat"),
                        lesResultats.getString("nom"),
                        lesResultats.getString("prenom"),
                        lesResultats.getInt("age"),
                        lesResultats.getString("email"),
                        lesResultats.getString("mdp"),
                        lesResultats.getString("tel")
                        );
            	lesCandidats.add(unCandidat);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesCandidats;
    }
    /***************************select like*************************************/
    public static ArrayList<Candidat> selectLikeCandidats(String filtre) {
        String requete = String.format(
                "SELECT * FROM candidat WHERE nom LIKE '%%%s%%' OR prenom LIKE '%%%s%%' OR " +
                        "age LIKE '%%%s%%' OR email LIKE '%%%s%%'OR tel LIKE '%%%s%%';",
                filtre, filtre, filtre, filtre);
        ArrayList<Candidat> lesCandidats = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Candidat unCandidat = new Candidat(
            			 lesResultats.getInt("idmoniteur"),
                         lesResultats.getString("nom"),
                         lesResultats.getString("prenom"),
                         lesResultats.getInt("age"),
                         lesResultats.getString("email"),
                         lesResultats.getString("mdp"),
                         lesResultats.getString("tel")
                         );
                      
                lesCandidats.add(unCandidat);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesCandidats;
    }
    public static ArrayList<Moniteur> selectAllMoniteurs() {
        String requete = "SELECT * FROM moniteur;";
        ArrayList<Moniteur> lesMoniteurs = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Moniteur unMoniteur = new Moniteur(
                        lesResultats.getInt(" idmoniteur "),
                        lesResultats.getString("nom"),
                        lesResultats.getString("prenom"),
                        lesResultats.getString("email"),
                        lesResultats.getString("mdp"),
                        lesResultats.getString("role"),
                        lesResultats.getString("tel")
                        );
            	lesMoniteurs.add(unMoniteur);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesMoniteurs;
    }
    /***************************select like*************************************/
    public static ArrayList<Moniteur> selectLikeMoniteurs(String filtre) {
        String requete = String.format(
                "SELECT * FROM moniteur WHERE nom LIKE '%%%s%%' OR prenom LIKE '%%%s%%' OR " +
                        "age LIKE '%%%s%%' OR email LIKE '%%%s%%'OR tel LIKE '%%%s%%';",
                filtre, filtre, filtre, filtre);
        ArrayList<Moniteur> lesMoniteurs = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Moniteur unMoniteur = new Moniteur(
            			 lesResultats.getInt("idmoniteur"),
                         lesResultats.getString("nom"),
                         lesResultats.getString("prenom"),
                         lesResultats.getString("age"),
                         lesResultats.getString("email"),
                         lesResultats.getString("mdp"),
                         lesResultats.getString("tel")
                         );
                      
                lesMoniteurs.add(unMoniteur);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesMoniteurs;
    }
    /************************select all************************/
    public static ArrayList<Examen> selectAllExamen() {
        String requete = "SELECT * FROM examen;";
        ArrayList<Examen> lesExamens = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Examen unExamen = new Examen(
                        lesResultats.getInt("idexamen"),
                        lesResultats.getString("dateExamen"),
                        lesResultats.getString("lieu"),
                        lesResultats.getString("typePermis")
                        );
            	lesExamens.add(unExamen);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesExamens;
    }
    /***************************select like*************************************/
    public static ArrayList<Examen> selectLikeExamens(String filtre) {
        String requete = String.format(
                "SELECT * FROM examen WHERE nom LIKE '%%%s%%' OR prenom LIKE '%%%s%%' OR " +
                        "age LIKE '%%%s%%' OR email LIKE '%%%s%%'OR tel LIKE '%%%s%%';",
                filtre, filtre, filtre, filtre);
        ArrayList<Examen> lesExamens = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Examen unExamen = new Examen(
            			 lesResultats.getInt("idexamen"),
                         lesResultats.getString("dateExamen"),
                         lesResultats.getString("lieu"),
                         lesResultats.getString("typePermis")
                         );
                      
                lesExamens.add(unExamen);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesExamens;
    }
    /************************select all************************/
    public static ArrayList<Vehicule> selectAllVehicules() {
        String requete = "SELECT * FROM vehicule;";
        ArrayList<Vehicule> lesVehicules = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Vehicule unVehicule = new Vehicule(
                        lesResultats.getInt(" idvehicule "),
                        lesResultats.getString("marque"),
                        lesResultats.getString("modele"),
                        lesResultats.getString("matricule")
                        );
                        
            	lesVehicules.add(unVehicule);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesVehicules;
    }
    /***************************select like*************************************/
    public static ArrayList<Vehicule> selectLikeVehicules(String filtre) {
        String requete = String.format(
                "SELECT * FROM vehicule WHERE nom LIKE '%%%s%%' OR prenom LIKE '%%%s%%' OR " +
                        "age LIKE '%%%s%%' OR email LIKE '%%%s%%'OR tel LIKE '%%%s%%';",
                filtre, filtre, filtre, filtre);
        ArrayList<Vehicule> lesVehicules = new ArrayList<>();
 
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);
 
            while (lesResultats.next()) {
            	Vehicule unVehicule = new Vehicule(
            			 lesResultats.getInt("idvehicule"),
                         lesResultats.getString("marque"),
                         lesResultats.getString("modele"),
                         lesResultats.getString("matricule")
                        
                         );
                      
                lesVehicules.add(unVehicule);
            }
 
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'ex�cution de la requ�te : " + requete);
        }
        return lesVehicules;
    }
   
     
    /******************** Autres m�thodes ***************************/
	public static void executerRequete(String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}

	public static ArrayList<VCandidat> selectAllVCandidats() {
		// TODO Auto-generated method stub
		return null;
	}
	public static int count(String table) {
		String requete = "select count(*) as nb from   "+table+";"; 
		int nb = 0; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				nb = unResultat.getInt("nb");
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return nb;
	}
	
}