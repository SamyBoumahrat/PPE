package controleur;

public class Moniteur {
    private int idmoniteur;
    private String nom, prenom, email, mdp, role, tel;

    // Constructeur avec idMoniteur
    public Moniteur(int idmoniteur, String nom, String prenom, String email, String mdp, String role, String tel) {
        this.idmoniteur = idmoniteur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.tel = tel;
    }

    // Constructeur sans idMoniteur (idMoniteur = 0 par défaut)
    public Moniteur(String nom, String prenom, String email, String mdp, String role, String tel) {
        this.idmoniteur = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.tel = tel;
    }

    // Getters et setters
    public int getIdMoniteur() {
        return idmoniteur;
    }

    public void setIdMoniteur(int idmoniteur) {
        this.idmoniteur = idmoniteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}