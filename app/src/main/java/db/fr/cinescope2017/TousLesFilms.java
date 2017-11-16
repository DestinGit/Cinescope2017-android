package db.fr.cinescope2017;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import asynchronous.other.AllMoviesAsyncTask;
import utilities.classes.MenuItemChoix;

public class TousLesFilms extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener , AdapterView.OnItemClickListener{
    private Button buttonValider;
    private Button buttonVideo;

    private ListView ListViewFilm;
    private EditText editTextRecherche;
    private Button buttonRechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tous_les_films);

        initInterface();

        initEvents();

        getFilms();
    }

    private void initEvents() {
        buttonValider.setOnClickListener(this);
        buttonVideo.setOnClickListener(this);

        buttonRechercher.setOnClickListener(this);
        editTextRecherche.setOnFocusChangeListener(this);

//        ListViewFilm.setOnItemClickListener(this);
    }

    private void initInterface() {
        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonVideo = (Button)findViewById(R.id.buttonVideo);

        buttonRechercher = (Button) findViewById(R.id.buttonRechercher);
        ListViewFilm = (ListView) findViewById(R.id.ListViewFilm);
        editTextRecherche = (EditText) findViewById(R.id.editTextRecherche);
        ListViewFilm.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onClick(View v) {
        Intent intention;

        if(v==buttonVideo) {
            intention = new Intent(this, UnFilmUneVideo.class);
            startActivity(intention);
//            finish();
        }
        if (v==buttonValider) {
            intention = new Intent(this, UnFilm.class);
            startActivity(intention);
//            finish();
        }

        if (v == buttonRechercher) {
//            new TAFilmGetIdTitre(this, ListViewFilm).execute("http://172.26.10.39:8084/Cinescope2017Web/", "FilmGetIdTitle", editTextRecherche.getText().toString());

            getFilms();
        }
    }

    /**
     * Recupère tous les films
     */
    private void getFilms() {
        Map<String, String> params = new HashMap();
        params.put("lsUrl", "http://172.26.10.39:8084/Cinescope2017Web/");
        params.put("lsRes", "FilmGetIdTitle");
        params.put("chaine",  editTextRecherche.getText().toString());

        new AllMoviesAsyncTask(this,ListViewFilm).execute(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    } // / onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuItemChoix.menuItemChoix(this, item.getItemId());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == editTextRecherche) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, ListViewFilm.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

//        parent.getChildAt(position).setBackgroundColor(Color.BLUE);

        ListViewFilm.setItemChecked(position, true);
        view.setBackgroundColor(Color.BLUE);

    }
}
