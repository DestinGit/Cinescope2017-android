package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import utilities.classes.MenuItemChoix;

public class Articles extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonAnnuler) {
            finish();
        }
        if (v==buttonValider) {
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
