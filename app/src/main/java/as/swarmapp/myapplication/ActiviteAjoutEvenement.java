package as.swarmapp.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import as.swarmapp.myapplication.BaseDeDonnees.DAOEvenement;
import as.swarmapp.myapplication.BaseDeDonnees.Evenement;


public class ActiviteAjoutEvenement extends ActionBarActivity implements GestionHorsUI{
    private Button bAjout;
    private EditText eAddresse;

    private View.OnClickListener OCLajout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MAJaffichage(eAddresse.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajout_evenement);
        bAjout = (Button) findViewById(R.id.Bajout);
        eAddresse = (EditText) findViewById(R.id.EaddresseEvenement);

        bAjout.setOnClickListener(OCLajout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activite_ajout_evenement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void MAJaffichage(final Object o) {
        final String lAdresse = (String) o;
        if (lAdresse.length()!=0){
            new Thread(new Runnable() {
                public void run() {
                    // le comportement de aFaireEnUI dépend de la réponse de aFaireHorsUI
                    aFaireEnUI(aFaireHorsUI(lAdresse));

                }
            }).start();
        }
    }

    @Override
    public Object aFaireHorsUI(final Object o) {
        String s = (String) o;
        return DAOEvenement.ajouter(new Evenement(s, "http://"+s+".fr"));
    }

    @Override
    public void aFaireEnUI(final Object o) {
        if ((long) o == -1)
            return;

        runOnUiThread(new Runnable() {
            public void run() {
                startActivity(new Intent(ActiviteAjoutEvenement.this, MenuConnexion.class));
                finish();
            }
        });
    }
}
