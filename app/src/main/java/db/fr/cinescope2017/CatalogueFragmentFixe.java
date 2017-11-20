package db.fr.cinescope2017;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class CatalogueFragmentFixe extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_fragment_fixe);
    } /// onCreate

    public void retourAccueil(View vue) {
        this.finish();
    }
} /// class Catalogue

