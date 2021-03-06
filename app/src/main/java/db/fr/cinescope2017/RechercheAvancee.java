package db.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import utilities.classes.MenuItemChoix;

public class RechercheAvancee extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_avancee);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        Bundle params = this.getIntent().getExtras();
        String valeur = params.getString("motRecherche");
        Toast.makeText(this, valeur,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v==buttonAnnuler) {
            Intent intentionResultat = new Intent();
            intentionResultat.putExtra("result", "KO C'est mort");
            setResult(RESULT_CANCELED, intentionResultat);
            finish();
        }
        if (v==buttonValider) {
            Intent intentionResultat = new Intent();
            intentionResultat.putExtra("result", "OK Ca passe");
            setResult(RESULT_OK, intentionResultat);
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
