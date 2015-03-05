package as.swarmapp.myapplication.BaseDeDonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class ManipuleurBDD{
    public String nomBDD;

    public ManipuleurBDD(String name) {
        nomBDD = name;
    }

    public void reCreer(SQLiteDatabase db){
        dropAll(db);
        createAll(db);
    }

    private void createAll(SQLiteDatabase db) {
        if (nomBDD.compareTo(DAOEvenement.NOM_BDD) != 0) {
            // TODO : rajouter ici toutes les DAO nécessaires pour une BDD d'évènement
            db.execSQL(DAOCoord.CREATE);
            db.execSQL(DAOPDI.CREATE);
            //db.execSQL(DAOCoord.CREATE);

        }else {
            // La base de données des évènements ne contient qu'une seule table
            db.execSQL(DAOEvenement.CREATE);
        }
    }

    private void dropAll(SQLiteDatabase db){
        db.execSQL(DAOCoord.DROP);
        db.execSQL(DAOPDI.DROP);
        db.execSQL(DAOEvenement.DROP);

    }

    /*
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAll(db);
        createAll(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAll(db);
        createAll(db);
    }
    */
}
