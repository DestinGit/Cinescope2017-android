package db.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView myList;
    private TextView textViewSelection;
    private ImageView imagViewLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] tMenu = {"Tous les films","Avant Première","Nouveaux Films","Reprises","Articles",
//        "Festivals", "Box Office", "Hit Parade", "Avis des critiques", "Salles de Paris", "salles de périphérie"};

        myList = (ListView)findViewById(R.id.listView);
        textViewSelection = (TextView)findViewById(R.id.itemMainMenu);
        imagViewLogo = (ImageView)findViewById(R.id.imagViewLogo);

        // Initialiser notre tableau de menu, en récupérant la liste dans arrays.xml
        String[] tMenu = getResources().getStringArray(R.array.menus);

        // Recupere l'id de l'image ... en base decimale
        // Transforme les images dans en string et le stocker dans le tableau String
        String[] tImages = new String[8];
        tImages[0] = String.valueOf(R.drawable.tousfilms);
        tImages[1] = String.valueOf(R.drawable.festival2);
        tImages[2] = String.valueOf(R.drawable.hitparade);
        tImages[3] = String.valueOf(R.drawable.aviscritiques);
        tImages[4] = String.valueOf(R.drawable.newfilms);
        tImages[5] = String.valueOf(R.drawable.nouveauxfilms);
        tImages[6] = String.valueOf(R.drawable.reprises);
        tImages[7] = String.valueOf(R.drawable.rechercher);

        // --- Creation de l'ArrayList pour remplir la ListView
        List<Map<String, String>> listMenu = new ArrayList();
        // --- HashMap pour les informations pour un item
        Map<String, String> hm;

        // --- Remplissage dynamique
        for(int i = 0; i < tMenu.length; i++) {
            hm = new HashMap();
            hm.put("image", tImages[i]);
            hm.put("itemMenu", tMenu[i]);
            hm.put("help", "help aide");
            listMenu.add(hm);
        }

        SimpleAdapter sa = new SimpleAdapter(
                this.getBaseContext(),
                listMenu,
                R.layout.ligne_main_menu,
                new String[] { "image", "itemMenu", "help"},
                new int[] { R.id.imagViewLogo, R.id.itemMainMenu, R.id.textViewComment}
        );

        // Affectation directe du Simple Adapter a la ListView
        myList.setAdapter(sa);

        myList.setOnItemClickListener(this);

//        onItemClick(myList, null, 2,2);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Map<String,String> map = (Map<String, String>) parent.getAdapter().getItem(position);
//        Toast.makeText(this, map.get("itemMenu"), Toast.LENGTH_SHORT).show();

        Intent intention = new Intent();

        if (position==1) {
            intention.setClass(this, BoxOffice.class);
            startActivityForResult(intention, 1);
        }
        if (position==2) {
            intention.setClass(this, HitParadeDuPublic.class);
            startActivityForResult(intention, 2);
        }

        if (position==7){
            intention.setClass(this, RechercheAvancee.class);
            intention.putExtra("motRecherche","mon mot");
            startActivityForResult(intention, 7);
        }
    }

    // -------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1: // BO
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KO", Toast.LENGTH_SHORT).show();
                        return;
                } // / switch (resultCode)

            case 2: // Hit Parade
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KO", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 7: // Hit Parade
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        String lsDataRetour = data.getStringExtra("result");
                        Toast.makeText(this, lsDataRetour, Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        String lsDataRetour1 = data.getStringExtra("result");
                        Toast.makeText(this, lsDataRetour1, Toast.LENGTH_SHORT).show();
                        return;
                }
        } // / switch (requestCode)

    } // / onActivityResult


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    } // / onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        // Détermine quelle entrée a été sélectionnée.
        switch (item.getItemId()) {

            // Aiguille
            case (R.id.action_settings):
                Toast.makeText(this, "Configuration", Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemAide):
                Toast.makeText(this, "Aide", Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemAPropos):
                Toast.makeText(this, "A propos\nLes barres d'action\nVersion 0.9", Toast.LENGTH_LONG).show();
                return true;

            default:
                // Renvoie false si les entrées n’ont pas été gérées.
                return false;
        }

    } // / onOptionsItemSelected
}
