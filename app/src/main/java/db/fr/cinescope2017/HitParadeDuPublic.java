package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import asyncpackage.MyCallbackInterface;
import asyncpackage.TacheAsynchrone;
import utilities.classes.MenuItemChoix;

public class HitParadeDuPublic extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider;
    private Button buttonAnnuler;
    private TextView textViewContenu;
    private GridView gridViewHPP, gridViewHPPTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hit_parade_du_public);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        textViewContenu = (TextView) findViewById(R.id.textViewContenu);

        gridViewHPP = (GridView)findViewById(R.id.gridViewHPP);
        gridViewHPPTitle = (GridView)findViewById(R.id.gridViewHPPTitle);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://172.26.10.39:8080/Cinescope2017Web/";
        String lsRessource = "HitParadeDuPublic";

//        new TacheAsynchrone(textViewContenu).execute(lsURL, lsRessource);
        new TacheAsynchrone(this, gridViewHPPTitle, gridViewHPP, new MyCallback(textViewContenu)).execute(lsURL, lsRessource);

//        TacheAsynchrone tae = new TacheAsynchrone();
//        tae.setTextViewCSV(textViewContenu);
//        tae.execute(lsURL, lsRessource);

    }

    private static class MyCallback implements MyCallbackInterface {
        private TextView textViewContenu;

        public MyCallback(TextView textViewContenu) {
            this.textViewContenu = textViewContenu;
        }

        public void onTaskFinished(String result) {
            // do something
            Log.i("CECI EST : ", " UN TEST");
            textViewContenu.setText("CECI EST UN TEST");
        }
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
