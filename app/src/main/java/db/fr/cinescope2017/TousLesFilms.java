package db.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import utilities.classes.MenuItemChoix;

public class TousLesFilms extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider;
    private Button buttonVideo;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tous_les_films);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonVideo = (Button)findViewById(R.id.buttonVideo);

        buttonExit = (Button)findViewById(R.id.buttonExit);

        buttonValider.setOnClickListener(this);
        buttonVideo.setOnClickListener(this);

        buttonExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intention;

        if (v == buttonExit) {
            finish();
        }
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

}
