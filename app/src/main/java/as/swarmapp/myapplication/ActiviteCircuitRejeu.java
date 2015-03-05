package as.swarmapp.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ActiviteCircuitRejeu extends ActionBarActivity {
    ListView listeCircuits;
    ListView listeRejeux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_circuit_rejeu);
        listeCircuits 	= (ListView) findViewById(R.id.LVcircuits);
        listeRejeux 	= (ListView) findViewById(R.id.LVrejeux);
        afficherListe(null, listeCircuits, Const.NV_CIRCUIT);
        afficherListe(null, listeRejeux, Const.NV_REJEU);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_circuit_rejeu, menu);
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

    private void afficherListe(List<String> lQ, ListView lV, String aAjouter){
        if (lQ==null){
            lQ = new ArrayList<String>();
        }
        lQ.add(aAjouter);
        lV.setAdapter(new AdaptateurListeSimple(this, lQ));
    }
}
