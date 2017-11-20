package db.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AuthentificationInclusion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification_inclusion);
    }


    public void retourAccueil(View vue) {
        finish();
    } /// retourAccueil

    public void afficherAuthentification(View vue) {
        Intent intention = new Intent(this, AuthentificationInclusion.class);
        startActivity(intention);
    } /// afficherAuthentification

    public void afficherCatalogue(View vue) {
        Intent intention = new Intent(this, CatalogueInclusion.class);
        startActivity(intention);
    } /// afficherCatalogue

}
