package controleur;

public class Accueil {
    private int idAccueil;
    private String nom, prenom, email, motDePasse, telephone;
    private int age;

    // Constructeur avec idAccueil
    public Accueil(int idAccueil, String nom, String prenom, int age, String email, String motDePasse, String telephone) {
        this.idAccueil = idAccueil;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    // Constructeur sans idAccueil (idAccueil = 0 par défaut)
    public Accueil(String nom, String prenom, int age, String email, String motDePasse, String telephone) {
        this.idAccueil = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    // Getters et setters
    public int getIdAccueil() {
        return idAccueil;
    }

    public void setIdAccueil(int idAccueil) {
        this.idAccueil = idAccueil;
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