package asyncpackage;

import android.content.Context;
import android.os.AsyncTask;
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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import db.fr.cinescope2017.R;

/**
 * Created by formation on 31/10/2017.
 */
public class TacheAsynchroneInscription extends AsyncTask<String, Integer, String> {

    private TextView textViewMessage;

    public TacheAsynchroneInscription(TextView textViewMessage) {
        super();
        this.textViewMessage = textViewMessage;
    } /// Constructeur

    // ----------------------------
    protected String doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        StringBuilder lsbResultat = new StringBuilder();
        String lsURL = "", lsRessources = "";
        URL urlConnection = null;
        HttpURLConnection httpConnection = null;
        boolean lbErreur = false;

        try {
            // Probleme avec les espaces
            // Donc URL_encode ...
            lsURL = asParametres[0];
            lsRessources = asParametres[1];

            // Instanciation de HttpURLConnection avec l'objet url
            urlConnection = new URL(lsURL+lsRessources);
            httpConnection = (HttpURLConnection) urlConnection.openConnection();

            // Choix de la methode get ou post
            // Il faudra passer en POST
            httpConnection.setRequestMethod("POST");

            // Autorise l'envoi de donnees
            // Sets the flag indicating whether this URLConnection allows input.
            httpConnection.setDoInput(true);

            // Connexion
            httpConnection.connect();

            String params = "";
            params += "nom=" + URLEncoder.encode(asParametres[2], "UTF-8");
            params += "&mdp=" + URLEncoder.encode(asParametres[3], "UTF-8");
            params += "&email=" + URLEncoder.encode(asParametres[4], "UTF-8");

            // Execution de la requete parametree
            OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());
            osw.write(params);
//            osw.flush();
            osw.close();

            // Execution de la requete parametree
            // Lecture du flux de la REPONSE
            InputStream inputStream = httpConnection.getInputStream();

            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String lsLigne = "";
            while ((lsLigne = br.readLine()) != null) {
                if (!lsLigne.trim().equals("")) {
                    lsbResultat.append(lsLigne);
                    lsbResultat.append("\n");
                }
            }
            br.close();
            inputStream.close();

        } catch (UnsupportedEncodingException e) {
            lsbResultat.append(e.getMessage());
            lbErreur = true;
        } catch (IOException e) {
            lsbResultat.append(e.getMessage());
            lbErreur = true;
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }

        if (lbErreur) {
            lsbResultat.setLength(0);
            lsbResultat.append("Erreur réseau");
        }

        // Renvoie la valeur a onPostExecute
        return lsbResultat.toString();
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(String asResultat) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final

        if (asResultat.equalsIgnoreCase("Erreur réseau")) {
            textViewMessage.setText(asResultat);
        } else {
            textViewMessage.setText(asResultat.trim() + " insertion réalisée");
        } /// if
    } /// onPostExecute
} /// TacheAsynchrone
