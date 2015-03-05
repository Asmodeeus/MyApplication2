package as.swarmapp.myapplication.BaseDeDonnees;

import android.util.Log;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class Coordonnees {
    private long id;
    private float lati;
    private float longi;
    private long ordre;

    public Coordonnees(int lID, float laLati, float laLongi, long lOrdre) {
        super();
        this.id     = lID;
        this.lati   = laLati;
        this.longi  = laLongi;
        this.ordre  = lOrdre;

    }

    public Coordonnees(float l, float L, long lOrdre){
        this(-1, l, L, 0);
    }

    public Coordonnees(float l, float L){
        this(l, L, 0);
    }

    public long getId() {
        return id;
    }

    public long getOrdre() {
        return ordre;
    }

    public float getLati() {
        return lati;
    }

    public float getLongi() {
        return longi;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrdre(long ordre) {
        this.ordre = ordre;
    }

    public void setLati(float lati) {
        this.lati = lati;
    }

    public void setLongi(float longi) {
        this.longi = longi;
    }

    @Override
    public String toString() {
        return "Coordonnees{" +
                "id=" + id +
                ", ordre=" + ordre +
                ", lati=" + lati +
                ", longi=" + longi +
                '}';
    }

}
