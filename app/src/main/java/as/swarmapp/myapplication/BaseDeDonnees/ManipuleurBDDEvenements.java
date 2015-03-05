package as.swarmapp.myapplication.BaseDeDonnees;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asmodeeus on 02/03/15.
 */
public class ManipuleurBDDEvenements {

    public static void reCreer(SQLiteDatabase db){
        dropAll(db);
        createAll(db);
    }

    private static void createAll(SQLiteDatabase db) {
        db.execSQL(DAOEvenement.CREATE);
    }

    private static void dropAll(SQLiteDatabase db){
        db.execSQL(DAOEvenement.DROP);

    }

}
