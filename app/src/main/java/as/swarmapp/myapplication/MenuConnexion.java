package as.swarmapp.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import as.swarmapp.myapplication.BaseDeDonnees.DAOEvenement;


public class MenuConnexion extends ActionBarActivity implements GestionHorsUI{
    private Button  Bconn;
    private Button  Bspec;
    private Spinner spinEvenement;
    private String  lEvenement;
    //*
    private AdapterView.OnItemSelectedListener selectionEvenement = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MenuConnexion.this, "onItemSelected", Toast.LENGTH_SHORT).show();
            lEvenement = parent.getItemAtPosition(position).toString();

            if (lEvenement.compareTo(Const.NV_EVENEMENT)==0){
                // NV_EVENEMENT a été sélectionné manuellement : l'utilisateur veut créer un nouvel évènement
                startActivity(new Intent(MenuConnexion.this, ActiviteAjoutEvenement.class));
                Toast.makeText(MenuConnexion.this, "création d'un nouvel évènement", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(MenuConnexion.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener OCLBconn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Que faire quand on clique sur B1 ?
            //Toast.makeText(MenuConnexion.this, getString(R.string.Bconnexion) + " à " + lEvenement, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MenuConnexion.this, TestBDD.class));
        }
    };

    private View.OnClickListener OCLBspect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Que faire quand on clique sur B2 ?
            Toast.makeText(MenuConnexion.this, getString(R.string.Bspectater), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chargement_en_cours);
        MAJaffichage();

        /* /!\ */
        /*
        ArrayList<String> lesEvenements = new ArrayList<String>();
        lesEvenements.add("WRSC 2014");
        lesEvenements.add("WRSC 2015");
        lesEvenements.add(Const.NV_EVENEMENT);
        //*/


    }

    @Override
    public void MAJaffichage() {
        new Thread(new Runnable() { public void run() {
            /**try {
             Thread.sleep(4000);
             }catch (Exception e){}//*/

            // aFaireHorsUI nous dit si l'on doit afficher le layout normal ou passer directement à une autre activité
            Object params = aFaireHorsUI();
            aFaireEnUI(params);

        } }).start();
    }

    @Override
    public Object aFaireHorsUI(){

        List<String> lesEvenements = (DAOEvenement.bddVersListe());
        if (lesEvenements.isEmpty()) {
            // S'il n'y a aucun évènement dans la BDD, on return null
            return null;

        }else {
            lesEvenements.add(Const.NV_EVENEMENT);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MenuConnexion.this, android.R.layout.simple_spinner_item, lesEvenements);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            return dataAdapter;
        }
    }

    public void aFaireEnUI(final Object lAdapteur){
        runOnUiThread(new Runnable() { public void run() {

            if (lAdapteur == null) {
                // Aucun évènement n'est dans la base de données, on charge l'activité d'ajout d'un évènement
                startActivity(new Intent(MenuConnexion.this, ActiviteAjoutEvenement.class));
                finish();

            }else{
                // Il y a des évènements dans la BDD, l'affichage est celui auquel on s'attend
                setContentView(R.layout.activite_menu_connexion);
                Bconn           = (Button) findViewById(R.id.Bconnexion);
                Bspec           = (Button) findViewById(R.id.Bspectater);
                spinEvenement   = (Spinner) findViewById(R.id.SPchoixEvenement);

                Log.d("B1", Bconn.toString());
                Log.d("B2", Bspec.toString());

                Bconn.setOnClickListener(OCLBconn);
                Bspec.setOnClickListener(OCLBspect);

                spinEvenement.setAdapter((ArrayAdapter<String>) lAdapteur);
                spinEvenement.setOnItemSelectedListener(selectionEvenement);

            }

        }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_connexion, menu);
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
}
