package as.swarmapp.myapplication.BaseDeDonnees;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by asmodeeus on 03/03/15.
 */
public class Utiles {


    public static String adresseSource(Context ctx){
        BufferedReader in;
        String l = "";
        try {
            InputStream instream = ctx.openFileInput("rien.txt");
            in = new BufferedReader(new InputStreamReader(instream),100);
            if((l = in.readLine())!=null){
                File f = new File(l);
                l = f.getParent();
            }
            in.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}
