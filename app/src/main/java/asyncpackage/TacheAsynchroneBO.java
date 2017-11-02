package asyncpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import db.fr.cinescope2017.R;

/**
 * Created by formation on 31/10/2017.
 */
public class TacheAsynchroneBO extends AsyncTask<String, Integer, JSONArray> {
    private TextView textViewCSV;
    private Context that;
    private GridView gridViewBoxOffice,gridViewBoxOfficeTitle;

    public TacheAsynchroneBO() {
    }

    public TacheAsynchroneBO(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }

    public TacheAsynchroneBO(Context that, GridView gridViewBoxOffice) {
        this.that = that;
        this.gridViewBoxOffice = gridViewBoxOffice;
    }

    public TacheAsynchroneBO(Context that, GridView gridViewBoxOfficeTitle, GridView gridViewBoxOffice) {
        this.that = that;
        this.gridViewBoxOffice = gridViewBoxOffice;
        this.gridViewBoxOfficeTitle = gridViewBoxOfficeTitle;
    }

    public void setTextViewCSV(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }
    @Override
    // ----------------------------
    protected JSONArray doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        JSONArray tableauJSON = null;
        StringBuilder lsb = new StringBuilder();
        String lsURL;
        String lsRessource;
        lsURL = asParametres[0];
        lsRessource = asParametres[1];
        URL url = null;
        HttpURLConnection httpConnection = null;
        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource);
            httpConnection = (HttpURLConnection) url.openConnection();
            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContenu = br.readLine();

            tableauJSON = new JSONArray(lsContenu);

            br.close();
            is.close();
        } catch (IOException e) {
            lsb.append(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }
        // Renvoie la valeur a onPostExecute
        return tableauJSON;
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(JSONArray s) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
//        textViewCSV.setText(s);

        List<String> itemsList = new ArrayList();
        List<String> itemsListTitres = new ArrayList();
        try {
            JSONObject objet;
            for (int i = 0; i < s.length();i++) {
                objet = s.getJSONObject(i);
                itemsListTitres.add(objet.getString("titre"));
                itemsList.add(objet.getString("entree"));
            }
            gridViewBoxOfficeTitle.setAdapter(new ArrayAdapter<String>(that, R.layout.cellule_texte, itemsListTitres));
            gridViewBoxOffice.setAdapter(new ArrayAdapter<String>(that, R.layout.cellule_texte, itemsList));

        }catch (Exception e) {

        }
    } /// onPostExecute
} /// TacheAsynchrone
