package asyncpackage;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by formation on 31/10/2017.
 */
public class TacheAsynchrone extends AsyncTask<String, Integer, String> {
    private TextView textViewCSV;

    public TacheAsynchrone() {
    }

    public TacheAsynchrone(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }

    public void setTextViewCSV(TextView textViewCSV) {
        this.textViewCSV = textViewCSV;
    }
    @Override
    // ----------------------------
    protected String doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
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
        textViewCSV.setText(s);
    } /// onPostExecute
} /// TacheAsynchrone
