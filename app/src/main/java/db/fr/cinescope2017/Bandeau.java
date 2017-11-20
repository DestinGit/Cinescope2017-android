package db.fr.cinescope2017;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
// --- Absolument celui-ci
import android.support.v4.app.Fragment;
// --- Et pas celui-ci
//import android.app.Fragment;

/**
 * Cette classe et son unique methode sert a deserialiser le fragment
 */
public class Bandeau extends Fragment implements OnClickListener {

    private Button buttonAuthentification;
    private Button buttonCatalogue;

    // Constructeur
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        // Ligne « équivalente » à setContentView(vue)
        // View = inflate(int resource, ViewGroup root, boolean attachToRoot)
        View vue = inflater.inflate(R.layout.bandeau, container, false);

        buttonAuthentification = (Button) vue.findViewById(R.id.buttonAuthentification);
        buttonAuthentification.setOnClickListener(this);

        buttonCatalogue = (Button) vue.findViewById(R.id.buttonCatalogue);
        buttonCatalogue.setOnClickListener(this);
        return vue;

    } /// onCreateView



    @Override
    public void onClick(View v) {
        if (v == buttonAuthentification) {
            Intent intention = new Intent(this.getActivity(), AuthentificationFragmentFixe.class);
            startActivity(intention);
        } /// onClick

        if (v == buttonCatalogue) {
            Intent intention = new Intent(this.getActivity(), CatalogueFragmentFixe.class);
            startActivity(intention);
        } /// buttonCatalogue
    } /// onClick

} /// class
