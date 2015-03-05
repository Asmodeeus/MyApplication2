package as.swarmapp.myapplication.BaseDeDonnees;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class DAOCoord extends DAOBase<Coordonnees> {
    // Nom de la table
    public static final String NAME = "Coordonnees";

    // Noms des champs de la table
    public static final String KEY          = "id";
    public static final String LATITUDE     = "latitude";
    public static final String LONGITUDE    = "longitude";
    public static final String ORDRE        = "ordre";

    // Requête de création
    public static final String CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s REAL, %s REAL, %s INTEGER DEFAULT 0);", NAME, KEY, LATITUDE, LONGITUDE, ORDRE);
    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;


    public DAOCoord(String leNom) throws ExceptionInInitializerError{
        super(leNom);

    }



    /*    -----------------  AJOUT    -----------------  */
    @Override
    public long ajoutALaVolee(Coordonnees c, SQLiteDatabase db) throws IllegalStateException {
        return sAjouter(c, db);
    }

    /** Fonction STATIC (s)
     * Permet d'ajouter des Coordonnées sur une BDD sans l'ouvrir. Elle doit donc être déjà ouverte.
     *
     * @param c
     * @param db
     * @return
     * @throws IllegalStateException
     */
    public static long sAjouter(Coordonnees c, SQLiteDatabase db) throws IllegalStateException{
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        ContentValues valeurs = new ContentValues();
        valeurs.put(LATITUDE, c.getLati());
        valeurs.put(LONGITUDE, c.getLongi());
        valeurs.put(ORDRE, c.getOrdre());
        return db.insert(NAME, null, valeurs);
    }



    /*    -----------------  SUPPRESSION    -----------------  */
    @Override
    public boolean supprimerALaVolee(long id, SQLiteDatabase db)  throws IllegalStateException{
        return sSupprimer(id, db);
    }

     public static boolean sSupprimer(long id, SQLiteDatabase db) throws IllegalStateException{
         if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

         return !(db.delete(NAME, KEY + " = " + Long.toString(id), null) == 0);
     }



    /*    -----------------  MODIFICATION    -----------------  */
    @Override
    public boolean modifierALaVolee(long id, Coordonnees nvT, SQLiteDatabase db) throws IllegalStateException{
        return sModifier(id, nvT, db);
    }

    public static boolean sModifier(long id, Coordonnees nvT, SQLiteDatabase db) throws IllegalStateException {
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        // TODO !!!
        return false;
    }



    /*    -----------------  SELECTION    -----------------  */
    @Override
    public Coordonnees selectionnerALaVolee(long id, SQLiteDatabase db) throws IllegalStateException {
        return sSelectionner(id, db);
    }

    /** Permet de sélectionner des Coordonnées sur une BDD sans l'ouvrir. Elle doit donc être déjà ouverte.
     *
     * @param id
     * @param db
     * @return
     * @throws IllegalStateException
     */
    public static Coordonnees sSelectionner(long id, SQLiteDatabase db) throws IllegalStateException{
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        String requSQL = "select * from " + NAME + " where "+ KEY +" = "+ Long.toString(id);
        Cursor c = db.rawQuery(requSQL, null);

        if (c.moveToFirst())
            return new Coordonnees(c.getInt(0), c.getFloat(1), c.getFloat(2), c.getInt(3));

        else
            return null;
    }



    /*    -----------------  BDD vers liste    -----------------  */

    @Override
    public List<String> bddVersListeALaVolee(SQLiteDatabase db) throws IllegalStateException{
        return sBDDversListe(db);
    }

    public List<String> sBDDversListe(SQLiteDatabase db) throws IllegalStateException{
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        Cursor c = db.rawQuery("select * from "+NAME, null);
        ArrayList<String> aLS = new ArrayList<String>();
        String s;

        if (c.getCount()!=0){
            while(c.moveToNext()){
                s = c.getColumnName(0) + " : " + Integer.toString(c.getInt(0)) + " | ";
                s+= c.getColumnName(1) + " : " + Float.toString(c.getFloat(1)) + " | ";
                s+= c.getColumnName(2) + " : " + Float.toString(c.getFloat(2)) + " | ";
                s+= c.getColumnName(3) + " : " + Integer.toString(c.getInt(3));
                aLS.add(s);
            }
        }

        return aLS;
    }
}
