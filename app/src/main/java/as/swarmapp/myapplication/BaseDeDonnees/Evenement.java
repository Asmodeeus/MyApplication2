package as.swarmapp.myapplication.BaseDeDonnees;

/**
 * Created by asmodeeus on 03/03/15.
 */
public class Evenement {
    private long id;
    private String nom;
    private String lien;
    private String description;

    public Evenement(long lId, String leNom, String leLien, String laDescription) {
        super();
        if (laDescription == null)
            laDescription = "";

        this.id = lId;
        this.nom = leNom;
        this.lien = leLien;
        this.description = laDescription;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
