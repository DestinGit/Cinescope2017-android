package asyncpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import db.fr.cinescope2017.R;

/**
 * Created by formation on 31/10/2017.
 */
public class TacheAsynchrone extends AsyncTask<String, Integer, String> {
    private TextView textViewCSV;
    private Context that;
    private GridView gridViewHPP,gridViewHPPTitle;
    private MyCallbackInterface callback;

    public TacheAsynchrone() {
    }

    public TacheAsynchrone(MyCallbackInterface callback) {
        this.callback = callback;
    }

    public TacheAsynchrone(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }

    public TacheAsynchrone(Context that, GridView gridViewHPP) {
        this.that = that;
        this.gridViewHPP = gridViewHPP;
    }

    public TacheAsynchrone(Context that, GridView gridViewHPPTitle, GridView gridViewHPP, MyCallbackInterface callback) {
        this.that = that;
        this.gridViewHPP = gridViewHPP;
        this.gridViewHPPTitle = gridViewHPPTitle;
        this.callback = callback;
    }

    public void setTextViewCSV(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }
    @Override
    // ----------------------------
    protected String doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        callback.onTaskFinished("TOTOI");

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
            String lsLigne = "";
            while ((lsLigne = br.readLine()) != null) {
                lsb.append(lsLigne);
                lsb.append("\n");
            }
            br.close();
            is.close();
        } catch (IOException e) {
            lsb.append(e.getMessage());
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }
        // Renvoie la valeur a onPostExecute
        return lsb.toString();
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(String s) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
//        textViewCSV.setText(s);

        List<String> itemsList = new ArrayList();
        List<String> itemsListTitres = new ArrayList();
        String[] tLignes = s.split("\n");

        for (int i = 0; i<tLignes.length;i++){
            Log.i("LSLIGNEEEEE", tLignes[i]);
            String[] tChamps = tLignes[i].split("#");
            itemsListTitres.add(tChamps[0]);
            Log.i("TIIITTRREE", tChamps[0]);
            for (int k = 1; k < tChamps.length;k++) {
                itemsList.add(tChamps[k]);
            }
        }

        gridViewHPPTitle.setAdapter(new ArrayAdapter<String>(that, R.layout.cellule_texte, itemsListTitres));
        gridViewHPP.setAdapter(new ArrayAdapter<String>(that, R.layout.cellule_texte, itemsList));

        callback.onTaskFinished(s);
    } /// onPostExecute
} /// TacheAsynchrone