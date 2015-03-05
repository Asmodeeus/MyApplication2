package as.swarmapp.myapplication.BaseDeDonnees;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import as.swarmapp.myapplication.Const;

/**
 * Created by asmodeeus on 02/03/15.
 */
public abstract class DAOBase<T>{
    // Nous sommes à la première version de la base
    protected final static int VERSION = 1;
    protected final static String NOM_A_FORMATER_ = "as.myapplication.%s.db";//"/as.myapplication.%s.db";

    // Le nom du fichier qui représente ma base
    protected String nom;

    protected SQLiteDatabase maBDD = null;
    protected ManipuleurBDD monManipuleur = null;

    /** Constructeur du DAO
     *
     * @param leNom : le nom de la BDD. "as.swarmapp." sera automatiquement ajouté devant et ".db" après.
     * @throws ExceptionInInitializerError
     */
    public DAOBase(String leNom) throws ExceptionInInitializerError{
        if (leNom!=null) {
            //this.nom = pContext.getFilesDir().getPath()+String.format(NOM_A_FORMATER_,leNom);
            this.nom = Const.CHEMIN + String.format(NOM_A_FORMATER_,leNom);
            Log.w("le nom", this.nom);
            this.monManipuleur = new ManipuleurBDD(leNom);

        }else{
            throw new ExceptionInInitializerError("Le nom ne peut pas être null");

        }
    }

    /** On ne peut pas utiliser SQLiteOpenHelper.getWritableDatabase() à cause du fait des nombreuses bases de données 1 par évènement (+ 1 pour tous les évènements)
     *
     * @return
     */
    public SQLiteDatabase open() {
        if (!(maBDD == null || !maBDD.isOpen()))
            maBDD.close();

        try {
            maBDD = SQLiteDatabase.openDatabase(this.nom, null, SQLiteDatabase.OPEN_READWRITE);
            checkerLaVersion();

        }catch(SQLiteCantOpenDatabaseException e){
            Log.d("création de", this.nom);
            maBDD = SQLiteDatabase.openOrCreateDatabase(this.nom, null);
            maBDD.setVersion(VERSION);
            monManipuleur.reCreer(maBDD);

        }
        Log.d("open", maBDD.getPath() + " ouverte");

        return maBDD;
    }

    private void checkerLaVersion() {
        if (maBDD.getVersion()!=VERSION)
            monManipuleur.reCreer(maBDD);
        /*
        if (maBDD.getVersion()>VERSION)
            monManipuleur.onDowngrade(maBDD, VERSION, maBDD.getVersion());
        else if (maBDD.getVersion()<VERSION)
            monManipuleur.onUpgrade(maBDD, maBDD.getVersion(), VERSION);
        */
    }

    public void close() {
        maBDD.close();
    }

    /** A-t'on vraiment besoin d'accéder à la BDD ?
        Il est préférable d'éviter d'utiliser cette classe. Elle n'est là que pour vérifier qu'on en a effectivement pas besoin.
    // */
    @Deprecated
    public SQLiteDatabase getBDD() {
        return maBDD;
    }
    // */

    /*
    ----------------------- Toutes les méthodes suivantes nécessitent d'ouvrir puis fermer la BDD -----------------------
    -------------------- Il faut donc leur donner une forme générique, ainsi qu'une extension eTruc ---------------------
     */
    public abstract long ajoutALaVolee(T aAjouter, SQLiteDatabase db);
    public long ajouter(T aAjouter){
        open();
        long toR = ajoutALaVolee(aAjouter, maBDD);
        close();
        return toR;
    }


    public abstract boolean supprimerALaVolee(long id, SQLiteDatabase db) throws IllegalStateException;
    public boolean supprimer(long id){
        open();
        boolean toR = supprimerALaVolee(id, maBDD);
        close();
        return toR;
    }

    public abstract boolean modifierALaVolee(long id, T nvT, SQLiteDatabase db) throws IllegalStateException;
    public boolean modifier(long id, T nvT){
        open();
        boolean toR = modifierALaVolee(id, nvT, maBDD);
        close();
        return toR;
    }

    public abstract T selectionnerALaVolee(long id, SQLiteDatabase db) throws IllegalStateException;
    public T selectionner(long id){
        open();
        T toR = selectionnerALaVolee(id, maBDD);
        close();
        return toR;
    }

    public abstract List<String> bddVersListeALaVolee(SQLiteDatabase db) throws IllegalStateException;
    public List<String> bddVersListe(){
        open();
        ArrayList<String> toR = (ArrayList<String>) bddVersListeALaVolee(maBDD);
        close();
        return toR;
    }

    /* Concept d'opération à la volée :
    J'appelle opération à la volée une opération effectuée sur un BDD déjà ouverte.
    Dans une cascade d'opérations sur une BDD la première opération doit donc TOUJOURS être à la volée, et les autres, jamais.
    */
}
