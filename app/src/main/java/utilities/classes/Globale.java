package utilities.classes;

/**
 * Created by formation on 06/11/2017.
 */
public class Globale {
    private static String id;
    private static String nom = "";
    private static String mdp;
    private static String email;

    public Globale() {
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Globale.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Globale.nom = nom;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        Globale.mdp = mdp;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Globale.email = email;
    }
}
