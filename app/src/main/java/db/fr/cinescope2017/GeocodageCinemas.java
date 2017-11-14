package db.fr.cinescope2017;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import asyncpackage.CallbackInterface;
import asyncpackage.GenericAsyncTask;

public class GeocodageCinemas extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextVoie, editTextVille, editTextPays;
    private TextView textViewLatitude, textViewLongitude, textViewMessage;

    private Button buttonGeocodage, buttonGeocodageInverse;
    private Button buttonGetAllAdresses, buttonGetAllCoords, buttonSetAllCoords;

    private Geocoder igeocodeur;
    private boolean ibGeocodeur;

    private double[] tLatLng;
    private String gURL = "http://172.26.10.39:8084/Cinescope2017Web/";
    private String gRES = "GetAdressFromOneCinema?code=";
    private String gCODE = "35A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geocodage_cinemas);

        initInterface();
        initEvents();

        initEditTextFields();

        testGeolocPossible();
    }

    private void initInterface() {
        editTextVoie = (EditText) findViewById(R.id.editTextVoie);
        editTextVille = (EditText) findViewById(R.id.editTextVille);
        editTextPays = (EditText) findViewById(R.id.editTextPays);

        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        buttonGeocodage = (Button) findViewById(R.id.buttonGeocodage);
        buttonGeocodageInverse = (Button) findViewById(R.id.buttonGeocodageInverse);

        buttonGetAllAdresses = (Button)findViewById(R.id.buttonGetAllAdresses);
        buttonGetAllCoords = (Button)findViewById(R.id.buttonGetAllCoords);
        buttonSetAllCoords = (Button)findViewById(R.id.buttonSetAllCoords);
    }

    private void initEvents() {
        buttonGeocodage.setOnClickListener(this);
        buttonGeocodageInverse.setOnClickListener(this);

        buttonGetAllAdresses.setOnClickListener(this);
        buttonGetAllCoords.setOnClickListener(this);
        buttonSetAllCoords.setOnClickListener(this);
    }

    private void testGeolocPossible() {
        // Test la presence du geocodage via une methode statique
        ibGeocodeur = Geocoder.isPresent();
        String message = null;
        if (ibGeocodeur) {
            message = "Géocodage possible";
            //Instancie un Geocoder en parametrant la Locale pour geocoder
            igeocodeur = new Geocoder(this, Locale.FRANCE);
        } else {
            message = "Géocodage impossible";
        }

        textViewMessage.setText(message);
    }

    /**
     * getLatLongFromAdresse : les coordonnees d'une adresse, géocodage
     *
     * @param adresse
     * @return
     */
    private double[] getLatLongFromAdresse(Geocoder geocodeur, String adresse) {
        // Fonction perso pour le géocodage
        double tLatLng[] = {0.0, 0.0};

        try {
            List<Address> adresses = geocodeur.getFromLocationName(adresse, 1);
            if (adresses.size() > 0) {
                tLatLng[0] = adresses.get(0).getLatitude();
                tLatLng[1] = adresses.get(0).getLongitude();
            }
        } catch (IOException e) {
        }

        return tLatLng;

    } // / getLatLongFromAdresse

    @Override
    public void onClick(View v) {
        if (v == buttonGeocodage) {
            String voie = editTextVoie.getText().toString();
            String ville = editTextVille.getText().toString();
            String pays = editTextPays.getText().toString();

            String adresse = voie + ville + pays;

            tLatLng = getLatLongFromAdresse(igeocodeur, adresse);
            textViewLatitude.setText(Double.toString(tLatLng[0]));
            textViewLongitude.setText(Double.toString(tLatLng[1]));
        }

        if (v == buttonGeocodageInverse) {

        }

        if (v == buttonGetAllAdresses) {
            // Préparation des params
            Map<String, String> params = new HashMap();
            params.put("lsUrl", gURL);
            params.put("lsRes", "GetAdressFromOneCinema");
            new GenericAsyncTask(new GetAllAdrCallback(textViewMessage, igeocodeur)).execute(params);
        }

        if (v == buttonGetAllCoords) {
            textViewMessage.setText(null);
        }
    }


    /**
     *
     */
    private void initEditTextFields() {
        // Préparation des params
        Map<String, String> params = new HashMap();
        params.put("lsUrl", gURL);
        params.put("lsRes", gRES + gCODE);

        new GenericAsyncTask(new MyCallback(editTextVoie, editTextVille, editTextPays)).execute(params);
    }

    private static class MyCallback implements CallbackInterface {
        private EditText editTextVoie, editTextVille, editTextPays;

        public MyCallback(EditText editTextVoie, EditText editTextVille, EditText editTextPays) {
            this.editTextVoie = editTextVoie;
            this.editTextVille = editTextVille;
            this.editTextPays = editTextPays;
        }

        @Override
        public void onTaskFinished(String result) {
            try {
                JSONObject objet = new JSONObject(result);
                editTextVoie.setText(objet.getString("adresse"));
                editTextVille.setText(objet.getString("ville"));
                editTextPays.setText("France");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static class GetAllAdrCallback implements CallbackInterface {
        private TextView textViewMessage;
        private Geocoder igeocodeur;
        private double[] tLatLng;

        public GetAllAdrCallback(TextView textViewMessage) {
            this.textViewMessage = textViewMessage;
        }

        public GetAllAdrCallback(TextView textViewMessage, Geocoder igeocodeur) {
            this.textViewMessage = textViewMessage;
            this.igeocodeur = igeocodeur;
        }

        /**
         * getLatLongFromAdresse : les coordonnees d'une adresse, géocodage
         *
         * @param adresse
         * @return
         */
        private double[] getLatLongFromAdresse(Geocoder geocodeur, String adresse) {
            // Fonction perso pour le géocodage
            double tLatLng[] = {0.0, 0.0};

            try {
                List<Address> adresses = geocodeur.getFromLocationName(adresse, 1);
                if (adresses.size() > 0) {
                    tLatLng[0] = adresses.get(0).getLatitude();
                    tLatLng[1] = adresses.get(0).getLongitude();
                }
            } catch (IOException e) {
            }

            return tLatLng;

        } // / getLatLongFromAdresse

        @Override
        public void onTaskFinished(String result) {
//            textViewMessage.setText(result);
            Map<String, String> mp;
            List<Map<String, String>> coords = new ArrayList();
            try {
                JSONArray jsonTab = new JSONArray(result);
                for (int i = 0; i < jsonTab.length(); i++) {
                    JSONObject object = jsonTab.getJSONObject(i);
                    String adresse = object.getString("adresse") + object.getString("ville") + "France";
                    tLatLng = getLatLongFromAdresse(igeocodeur, adresse);

                    mp = new HashMap();
                    mp.put("code", object.getString("code"));
                    mp.put("Lat", String.valueOf(tLatLng[0]));
                    mp.put("Lng", String.valueOf(tLatLng[1]));
                    coords.add(mp);
                }
                textViewMessage.setText(coords.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
