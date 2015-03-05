package as.swarmapp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import as.swarmapp.myapplication.BaseDeDonnees.DAOEvenement;
import as.swarmapp.myapplication.BaseDeDonnees.Utiles;


public class ActiviteOuverture extends ActionBarActivity {
    private static String txtB1 = "Bonjour, je suis le bouton 1.";
    private static String txtB2 = "Moi, c'est le bouton 2 !";
    private Button b1;
    private Button b2;
    private EditText un;
    private EditText pw;

    private View.OnClickListener OCLb1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Que faire quand on clique sur B1 ?
            Toast.makeText(ActiviteOuverture.this, txtB1, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActiviteOuverture.this, MenuConnexion.class));
        }
    };

    private View.OnClickListener OCLb2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Que faire quand on clique sur B2 ?
            Toast.makeText(ActiviteOuverture.this, txtB2, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActiviteOuverture.this, ActiviteCircuitRejeu.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ouverture);
        Const.setPath(this);

        b1 = (Button) findViewById(R.id.Bb1);
        b2 = (Button) findViewById(R.id.Bspectater);
        un = ((EditText) findViewById(R.id.Eident));
        pw = ((EditText) findViewById(R.id.Emdp));

        b1.setOnClickListener(OCLb1);
        b2.setOnClickListener(OCLb2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activite_ouverture, menu);
        getMenuInflater().inflate(R.menu.menu_generique, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.Iaide:
                return true;

            case R.id.Iparametres:
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    /** Callback du bouton de connexion.<br>
     * La liaison entre le bouton et cette fonction de callback est effectuée dans le layout via android:onClick="btnConn"<br><br>
     * Ici, on récupère les identifiants des champs remplis et on execute une requête POST pour se connecter
     *
     * @param view : la vue dont le android:onClick a été déclenché. Il s'agit d'une grammaire imposée.
     */
    /*
    public void btnConn(final View view){
        // On récupère les identifiants
        String nom = un.getText().toString();
        String mdp = pw.getText().toString();

        // On filtre les comportements stériles
        if(!requeteEnCours && !nom.equals("") && !mdp.equals("")){
            requeteEnCours = true;

            // On construit les données de la requête POST
            final List<NameValuePair> identifiants = new ArrayList<NameValuePair>(3);
            identifiants.add(new BasicNameValuePair("csrfmiddlewaretoken", N3Utiles.leCSRF(sharedPref)));
            identifiants.add(new BasicNameValuePair("username", nom));
            identifiants.add(new BasicNameValuePair("password", mdp));

            // On crée un nouveau Thread pour toutes les opérations réseaux qui paralysent l'UI
            new Thread(new Runnable() { public void run() {

                try {
                    // On forme une requête POST contenant données précédemment constituées
                    HttpPost httpPost = new HttpPost(N3Const.CONNEXION);
                    httpPost.setEntity(new UrlEncodedFormEntity(identifiants));

                    // On execute la requête et on récupère les cookies de sessionid et csrftoken
                    response = httpClient.execute(httpPost);
                    N3Utiles.cookies_ALLClientVersSharedPref(httpClient, sharedPref);

                    // Si le couple login/mdp a été accepté par le serveur, on change d'activité
                    if (bienConnecte(response)){
                        startActivity(new Intent(Pageconnexion.this, MenuPrincipal.class));

                        // On bloque la possibilité de revenir à l'écran de connexion via Home/Back
                        finish();
                    }
                    requeteEnCours = false;

                } catch (IOException e) {
                    // Le serveur n'a pas répondu : on affiche un Toast
                    N3Utiles.toastLong(Pageconnexion.this,N3Const.ECHEC_HTTP);
                    e.printStackTrace();
                    requeteEnCours = false;
                }
            } }).start();

            // On garde le nom d'utilisateur en mémoire
            sharedPref.edit().putString(N3Const.USERNAME, un.getText().toString()).commit();
            sharedPref.edit().putBoolean(N3Const.SE_SOUVENIR, true).commit(); // TODO : mettre une checkbox
        }
    }
    */
}
