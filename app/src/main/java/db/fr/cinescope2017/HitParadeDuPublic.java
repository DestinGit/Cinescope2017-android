package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import asyncpackage.TacheAsynchrone;

public class HitParadeDuPublic extends AppCompatActivity implements View.OnClickListener{
    private Button buttonValider;
    private Button buttonAnnuler;
    private TextView textViewContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hit_parade_du_public);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        textViewContenu = (TextView) findViewById(R.id.textViewContenu);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://172.26.10.3:8084/Cinescope2017Web/";
        String lsRessource = "HitParadeDuPublic";
        //new TacheAsynchrone().execute(lsURL, lsRessource);
        TacheAsynchrone tae = new TacheAsynchrone();
        tae.setTextViewCSV(textViewContenu);
        tae.execute(lsURL, lsRessource);

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
}
