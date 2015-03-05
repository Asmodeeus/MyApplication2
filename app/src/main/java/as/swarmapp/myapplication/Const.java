package as.swarmapp.myapplication;

import android.content.Context;


/**
 * Created by asmodeeus on 28/02/15.
 */
public final class Const {

    public static String NV_CIRCUIT     = "Créer un nouveau circuit";
    public static String NV_REJEU       = "Importer des données de replay";
    public static String NV_EVENEMENT   = "Ajouter un nouvel évènement";
    public static String NOM_BASE_TEST  = "test";

    public static String CHEMIN         = null;
    private Const(){
        throw new AssertionError("Const ne doit pas être instancié");
    }

    public static void setPath(Context leC){
        if (CHEMIN==null)
            CHEMIN = leC.getFilesDir().getPath()+"/";
    }
}
