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


public class MenuConnexion extends ActionBarActivity {
    private Button  Bconn;
    private Button  Bspec;
    private Spinner spinEvenement;
    private String  lEvenement;

    private AdapterView.OnItemSelectedListener clicEvenement = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            lEvenement = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Ne fait rien.
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
            Toast.makeText(MenuConnexion.this, getString(R.string.BSpectater), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_menu_connexion);
        Bconn           = (Button) findViewById(R.id.Bconnexion);
        Bspec           = (Button) findViewById(R.id.BSpectater);
        spinEvenement   = (Spinner) findViewById(R.id.SPchoixEvenement);

        Log.d("B1", Bconn.toString());
        Log.d("B2", Bspec.toString());

        Bconn.setOnClickListener(OCLBconn);
        Bspec.setOnClickListener(OCLBspect);

        /* /!\ */
        /*
        ArrayList<String> lesEvenements = new ArrayList<String>();
        lesEvenements.add("WRSC 2014");
        lesEvenements.add("WRSC 2015");
        lesEvenements.add(Const.NV_EVENEMENT);
        //*/

        //*
        List<String> lesEvenements = (DAOEvenement.bddVersListe());
        lesEvenements.add(Const.NV_EVENEMENT);
        //*/

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesEvenements);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinEvenement.setAdapter(dataAdapter);
        spinEvenement.setOnItemSelectedListener(clicEvenement);

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
