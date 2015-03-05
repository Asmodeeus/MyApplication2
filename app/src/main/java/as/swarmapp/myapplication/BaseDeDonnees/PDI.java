package as.swarmapp.myapplication.BaseDeDonnees;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class PDI {
    private long id;
    private String nom;
    private String couleur;
    private String description;
    private Coordonnees coord;

    public PDI(long lId, String leNom, String laCouleur, String laDescription, Coordonnees laC) {
        super();
        if (laDescription == null)
            laDescription = "";
        if (laCouleur == null)
            laCouleur = "0xFF0000";

        this.id = lId;
        this.nom = leNom;
        this.coord = laC;
        this.couleur = laCouleur;
        this.description = laDescription;
    }

    public PDI(String leNom, String laCouleur, String laDescription, Coordonnees laC) {
        this(-1, leNom, laCouleur, laDescription, laC);
    }

    public PDI(String leNom, Coordonnees laC){
        this(-1, leNom, null, null, laC);
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public void setCoord(Coordonnees coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "PDI{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", couleur='" + couleur + '\'' +
                ", description='" + description + '\'' +
                ", coord=" + coord +
                '}';
    }
}
