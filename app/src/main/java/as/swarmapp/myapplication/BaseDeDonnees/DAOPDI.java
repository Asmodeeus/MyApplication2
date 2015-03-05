package as.swarmapp.myapplication.BaseDeDonnees;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class DAOPDI extends DAOBase<PDI> {
    // Nom de la table
    public static final String NAME = "PDI";

    // Noms des champs de la table
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String COULEUR = "couleur";
    public static final String DESCRIPTION = "description";
    public static final String COORDONNEES = "coord";

    // Requête de création
    public static final String CREATE = "CREATE TABLE " + NAME + " (" +
                    KEY         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM         + " TEXT, " +
                    COULEUR     + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    COORDONNEES + " INTEGER," +
                    "FOREIGN KEY(" + COORDONNEES + ") REFERENCES " + DAOCoord.NAME + "(" + DAOCoord.KEY + ") );";
    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;


    /**
     * Constructeur du DAO
     *
     * @param leNom    : le nom de la BDD. "as.swarmapp." sera automatiquement ajouté devant et ".db" après.
     * @throws ExceptionInInitializerError
     */
    public DAOPDI(String leNom) throws ExceptionInInitializerError {
        super(leNom);
    }



    /*    -----------------  AJOUT    -----------------  */
    @Override
    public long ajoutALaVolee(PDI p, SQLiteDatabase db) throws IllegalStateException{
        return sAjouter(p, db);
    }

    public static long sAjouter(PDI p, SQLiteDatabase db) throws IllegalStateException{
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        /// On crée un nouveau point de coordonnées aux coordonnées du PDI
        long id_coord = DAOCoord.sAjouter(new Coordonnees(p.getCoord().getLati(), p.getCoord().getLongi()), db);

        // On met les données dans un ContentValues
        ContentValues valeurs = new ContentValues();
        valeurs.put(NOM         , p.getNom());
        valeurs.put(COULEUR     , p.getCouleur());
        valeurs.put(DESCRIPTION , p.getDescription());
        valeurs.put(COORDONNEES , id_coord);

        // On poste la requête dont on récupère la réponse
        return db.insert(NAME, null, valeurs);

    }



    /*    -----------------  SUPPRESSION    -----------------  */
    @Override
    public boolean supprimerALaVolee(long id, SQLiteDatabase db) {
        return sSupprimer(id, db);
    }

    public static boolean sSupprimer(long id, SQLiteDatabase db) throws IllegalStateException {
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        return !(db.delete(NAME, KEY + " = " + Long.toString(id), null) == 0);
    }




    /*    -----------------  MODIFICATION    -----------------  */
    @Override
    public boolean modifierALaVolee(long id, PDI nvT, SQLiteDatabase db) throws IllegalStateException{
        return sModifier(id, nvT, db);
    }
    // TODO !!!
    public static boolean sModifier(long id, PDI nvT, SQLiteDatabase db) throws IllegalStateException {
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        return false;
    }



    /*    -----------------  SELECTION    -----------------  */
    @Override
    public PDI selectionnerALaVolee(long id, SQLiteDatabase db) throws IllegalStateException {
        return sSelectionner(id, db);
    }

    public static PDI sSelectionner(long id, SQLiteDatabase db) throws IllegalStateException {
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        String requSQL = "select * from " + NAME + " where "+ KEY +" = "+ Long.toString(id);
        Cursor c = db.rawQuery(requSQL, null);

        if (c.moveToFirst())
            return new PDI(c.getLong(0), c.getString(1), c.getString(2), c.getString(3), DAOCoord.sSelectionner(c.getLong(4), db));

        else
            return null;
    }



    public List<String> bddVersListeALaVolee(SQLiteDatabase db) throws IllegalStateException{
        return sBDDversListe(db);
    }

    public static List<String> sBDDversListe(SQLiteDatabase db) throws IllegalStateException{
        if (!db.isOpen())
            throw new IllegalStateException("Cette fonction ne peut être appelée que sur une BDD ouverte");

        Cursor c = db.rawQuery("select * from "+NAME, null);
        ArrayList<String> aLS = new ArrayList<String>();
        String s;

        if (c.getCount()!=0){
            while(c.moveToNext()){
                s = c.getColumnName(0) + " : " + Integer.toString(c.getInt(0)) + " | ";
                s+= c.getColumnName(1) + " : " + c.getString(1) + " | ";
                s+= c.getColumnName(2) + " : " + c.getString(2) + " | ";
                s+= c.getColumnName(3) + " : " + c.getString(3) + " | ";
                //s+= c.getColumnName(4) + " : " + Integer.toString(c.getInt(4));
                s+= c.getColumnName(4) + " : " + DAOCoord.sSelectionner(c.getLong(4), db).toString();
                aLS.add(s);
            }
        }
        return aLS;
    }
}
