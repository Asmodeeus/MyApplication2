package as.swarmapp.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import as.swarmapp.myapplication.BaseDeDonnees.*;


public class TestBDD extends ActionBarActivity {
    private ListView listeTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_test_bdd);
        listeTest 	= (ListView) findViewById(R.id.LVtest);

        // FIXME : ça ne doit pas être dans le ThreadUI
        DAOPDI d = new DAOPDI(Const.NOM_BASE_TEST);
        //Log.w("test", Long.toString(d.eAjouter(new PDI("Test 1", new Coordonnees(0.1f, -12.3f), "0xFF0000", "Point d'intérêt de test numéro 1"))));

        /*
        d.ajouter(new PDI("Test 1", "0x0000FF", "Point d'intérêt de test numéro 1", new Coordonnees(0.1f, -12.3f)));
        d.ajouter(new PDI("Test 2", new Coordonnees(0.1f, -12.3f)));
        d.ajouter(new PDI("Test 3", new Coordonnees(-0.1f, 1.3f)));
        d.ajouter(new PDI("Test 4", new Coordonnees(-0.1f, -91.5f)));
        d.ajouter(new PDI("Ta mère", new Coordonnees(0f, -7f)));
        d.ajouter(new PDI("Ma maison", new Coordonnees(180f, -19.5786f)));
        //*/
        listeTest.setAdapter(new AdaptateurListeSimple(this, d.bddVersListe()));

        PDI pd = d.selectionner(5);
        if (pd!=null)
            Log.w("TestBDD", pd.toString());

        /*
        Coordonnees co = d.eSelectionner(2);
        if (co!=null)
            Log.w("TestBDD", co.toString());
         co = d.eSelectionner(1);
        if (co!=null)
            Log.w("TestBDD", co.toString());
         co = d.eSelectionner(0);
        if (co!=null)
            Log.w("TestBDD", co.toString());
         co = d.eSelectionner(5);
        if (co!=null)
            Log.w("TestBDD", co.toString());
         co = d.eSelectionner(4);
        if (co!=null)
            Log.w("TestBDD", co.toString());
        //*/
        ///Log.d("test Db", d.eSelectionner(1).toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_bdd, menu);
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
