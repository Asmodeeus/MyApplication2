package as.swarmapp.myapplication.BaseDeDonnees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import as.swarmapp.myapplication.Const;

/** "Evenements" est une base particulière puisqu'elle ne contient qu'une seule table et sa BDD est unique. On peut donc se permettre de ne pas descendre de DAOBase
 * Created by asmodeeus on 03/03/15.
 */
public final class DAOEvenement {
    // Nom de la table
    public static final String NAME = "Evenements";
    public static final String NOM_BDD = "lesEvenements";

    // Noms des champs de la table
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String LIEN = "url";
    public static final String DESCRIPTION = "description";

    // Requête de création
    public static final String CREATE = "CREATE TABLE " + NAME + " (" +
            KEY         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOM         + " TEXT UNIQUE, " +
            LIEN        + " TEXT UNIQUE, " +
            DESCRIPTION + " TEXT );";
    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;

    protected static SQLiteDatabase maBDD = null;

    private DAOEvenement(){
        throw new AssertionError("DAOEvenement ne doit pas être instancié");
    }

    /*
    //public DAOEvenement(Context pContext) throws ExceptionInInitializerError {
    public DAOEvenement() {
        //this.nom = pContext.getFilesDir().getPath()+String.format(DAOBase.NOM_A_FORMATER_,NOM_BDD);
    }*/

    /** On ne peut pas utiliser SQLiteOpenHelper.getWritableDatabase() à cause du fait des nombreuses bases de données (1 par évènement + 1 pour tous les évènements)
     *
     * @return
     */
    public static SQLiteDatabase open() {
        if (!(maBDD == null || !maBDD.isOpen()))
            maBDD.close();

        String nom = Const.CHEMIN + String.format(DAOBase.NOM_A_FORMATER_,NOM_BDD);
        try {
            maBDD = SQLiteDatabase.openDatabase(nom, null, SQLiteDatabase.OPEN_READWRITE);
            checkerLaVersion();

        }catch(SQLiteCantOpenDatabaseException e){
            Log.d("création de", nom);
            maBDD = SQLiteDatabase.openOrCreateDatabase(nom, null);
            maBDD.setVersion(DAOBase.VERSION);
            ManipuleurBDDEvenements.reCreer(maBDD);

        }
        Log.d("open", maBDD.getPath() + " ouverte");

        return maBDD;
    }

    private static void checkerLaVersion() {
        if (maBDD.getVersion()!=DAOBase.VERSION)
            ManipuleurBDDEvenements.reCreer(maBDD);
        /*
        if (maBDD.getVersion()>VERSION)
            monManipuleur.onDowngrade(maBDD, VERSION, maBDD.getVersion());
        else if (maBDD.getVersion()<VERSION)
            monManipuleur.onUpgrade(maBDD, maBDD.getVersion(), VERSION);
        */
    }

    public static void close() {
        maBDD.close();
    }


    /*    -----------------  AJOUT          -----------------  */
    public static long ajouter(Evenement aAjouter){
        open();

        // On met les données dans un ContentValues
        ContentValues valeurs = new ContentValues();
        valeurs.put(NOM         , aAjouter.getNom());
        valeurs.put(LIEN        , aAjouter.getLien());
        valeurs.put(DESCRIPTION , aAjouter.getDescription());

        // On poste la requête dont on récupère la réponse
        long toR = maBDD.insert(NAME, null, valeurs);

        close();
        return toR;
    }



    /*    -----------------  SUPPRESSION    -----------------  */
    public static boolean supprimer(long id){
        open();

        // TODO
        boolean toR = false;

        close();
        return toR;
    }



    /*    -----------------  MODIFICATION   -----------------  */
    public boolean modifier(long id, Evenement nvT){
        open();

        // TODO
        boolean toR = false;

        close();
        return toR;
    }



    /*    -----------------  SELECTION      -----------------  */
    public Evenement selectionner(long id){
        open();

        Evenement toR = null;
        String requSQL = "select * from " + NAME + " where "+ KEY +" = "+ Long.toString(id);
        Cursor c = maBDD.rawQuery(requSQL, null);

        if (c.moveToFirst())
            toR =  new Evenement(c.getLong(0), c.getString(1), c.getString(2), c.getString(3));

        close();
        return toR;
    }



    public static List<String> bddVersListe(){
        open();

        Cursor c = maBDD.rawQuery("select * from "+NAME, null);
        ArrayList<String> aLS = new ArrayList<String>();

        if (c.getCount()!=0){
            String s;

            while(c.moveToNext()){
                s= c.getString(1);
                /*
                s = c.getColumnName(0) + " : " + Long.toString(c.getLong(0)) + " | ";
                s+= c.getColumnName(1) + " : " + c.getString(1) + " | ";
                s+= c.getColumnName(2) + " : " + c.getString(2) + " | ";
                s+= c.getColumnName(3) + " : " + c.getString(3) + " | ";
                //*/
                aLS.add(s);
            }

        }

        close();
        return aLS;
    }

}
