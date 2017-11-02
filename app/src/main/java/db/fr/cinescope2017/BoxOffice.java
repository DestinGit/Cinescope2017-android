package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import asyncpackage.TacheAsynchroneBO;

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
}
