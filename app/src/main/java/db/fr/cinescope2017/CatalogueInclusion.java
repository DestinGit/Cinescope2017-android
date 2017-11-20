package db.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CatalogueInclusion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_inclusion);
    }

    public void afficherAuthentification(View vue) {
        Intent intention = new Intent(this, AuthentificationInclusion.class);
        startActivity(intention);
    } /// afficherAuthentification

    public void afficherCatalogue(View vue) {
        Intent intention = new Intent(this, CatalogueInclusion.class);
        startActivity(intention);
    } /// afficherCatalogue

    public void retourAccueil(View vue) {
        this.finish();
        Intent intention = new Intent(this, InclusionsDeBase.class);
        startActivity(intention);
    } /// retourAccueil
}
