package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        String[] tMenu = getResources().getStringArray(R.array.menus);

        myList = (ListView)findViewById(R.id.listView);
        textViewSelection = (TextView)findViewById(R.id.itemMainMenu);
        imagViewLogo = (ImageView)findViewById(R.id.imagViewLogo);


        // Recupere l'id de l'image ... en base decimale
        String[] tImages = new String[11];
        tImages[0] = String.valueOf(R.drawable.tousfilms);
        tImages[1] = String.valueOf(R.drawable.nouveauxfilms);
        tImages[2] = String.valueOf(R.drawable.newfilms);
        tImages[3] = String.valueOf(R.drawable.reprises);
        tImages[4] = String.valueOf(R.drawable.articles);
        tImages[5] = String.valueOf(R.drawable.festival);
        tImages[6] = String.valueOf(R.drawable.festival2);
        tImages[7] = String.valueOf(R.drawable.hitparade);
        tImages[8] = String.valueOf(R.drawable.aviscritiques);
        tImages[9] = String.valueOf(R.drawable.salleparis);
        tImages[10] = String.valueOf(R.drawable.salleperi);

        List<Map<String, String>> listMenu = new ArrayList();
        Map<String, String> hm;

        for(int i = 0; i < tMenu.length; i++) {
            hm = new HashMap();
            hm.put("image", tImages[i]);
            hm.put("itemMenu", tMenu[i]);
            listMenu.add(hm);
        }

        SimpleAdapter sa = new SimpleAdapter(
                this.getBaseContext(),
                listMenu,
                R.layout.ligne_main_menu,
                new String[] { "image", "itemMenu"},
                new int[] { R.id.imagViewLogo, R.id.itemMainMenu}
        );

        // Affectation directe du Simple Adapter a la ListView
        myList.setAdapter(sa);

        myList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String,String> map = (Map<String, String>) parent.getAdapter().getItem(position);
        Toast.makeText(this, map.get("itemMenu"), Toast.LENGTH_SHORT).show();
    }
}
