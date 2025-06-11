package controleur;

public class Examen {
    private int idExamen;
    private String dateExamen, lieu, typePermis;

    // Constructeur avec idExamen
    public Examen(int idExamen, String dateExamen, String lieu, String typePermis) {
        this.idExamen = idExamen;
        this.dateExamen = dateExamen;
        this.lieu = lieu;
        this.typePermis = typePermis;
    }

    // Constructeur sans idExamen (idExamen = 0 par défaut)
    public Examen(String dateExamen, String lieu, String typePermis) {
        this.idExamen = 0;
        this.dateExamen = dateExamen;
        this.lieu = lieu;
        this.typePermis = typePermis;
    }

    // Getters et setters
    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public String getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(String dateExamen) {
        this.dateExamen = dateExamen;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTypePermis() {
        return typePermis;
    }

    public void setTypePermis(String typePermis) {
        this.typePermis = typePermis;
    }
}