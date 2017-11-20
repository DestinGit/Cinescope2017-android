package db.fr.cinescope2017;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class AuthentificationFragmentFixe extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification_fragment_fixe);
    } /// onCreate

    public void retourAccueil(View vue) {
        finish();
    }
} /// class Authentification
