package controleur;

public class Vehicule {
    private int idVehicule;
    private String marque, modele, matricule;

    // Constructeur avec idVehicule
    public Vehicule(int idVehicule, String marque, String modele, String matricule) {
        this.idVehicule = idVehicule;
        this.marque = marque;
        this.modele = modele;
        this.matricule = matricule;
    }

    // Constructeur sans idVehicule (idVehicule = 0 par défaut)
    public Vehicule(String marque, String modele, String matricule) {
        this.idVehicule = 0;
        this.marque = marque;
        this.modele = modele;
        this.matricule = matricule;
    }

    // Getters et setters
    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}