package controleur;

public class Candidat {
    private int idCandidat;
    private String nom, prenom, email, motDePasse, telephone;
    private int age;

    // Constructeur avec idCandidat
    public Candidat(int idCandidat, String nom, String prenom, int age, String email, String motDePasse, String telephone) {
        this.idCandidat = idCandidat;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    // Constructeur sans idCandidat (idCandidat = 0 par défaut)
    public Candidat(String nom, String prenom, int age, String email, String motDePasse, String telephone) {
        this.idCandidat = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    // Getters et setters
    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}