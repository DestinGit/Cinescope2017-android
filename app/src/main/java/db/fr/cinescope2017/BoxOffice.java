package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import asyncpackage.TacheAsynchroneBO;
import utilities.classes.MenuItemChoix;

public class BoxOffice extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider, buttonAnnuler;
    private GridView gridViewBoxOfficeTitle, gridViewBoxOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_office);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        gridViewBoxOfficeTitle = (GridView)findViewById(R.id.gridViewBoxOfficeTitle);
        gridViewBoxOffice = (GridView)findViewById(R.id.gridViewBoxOffice);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://172.26.10.39:8080/Cinescope2017Web/";
        String lsRessource = "BoxOffice";

        new TacheAsynchroneBO(this, gridViewBoxOfficeTitle, gridViewBoxOffice).execute(lsURL, lsRessource);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonAnnuler) {
            setResult(RESULT_CANCELED);
            finish();
        }
        if (v==buttonValider) {
            setResult(RESULT_OK);
            finish();
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
