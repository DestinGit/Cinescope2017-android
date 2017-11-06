package asyncpackage;

import android.os.AsyncTask;
import android.util.Log;
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

import utilities.classes.Globale;

/**
 * Created by formation on 31/10/2017.
 */
public class TaskAsyncUserGetDel extends AsyncTask<String, Integer, String> {

    private TextView textViewMessage;

    public TaskAsyncUserGetDel(TextView textViewMessage) {
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

        String lsContent = "";
        JSONArray tableauJSON = null;

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
            params += "&id=" + URLEncoder.encode(asParametres[2], "UTF-8");
//            params += "&mdp=" + URLEncoder.encode(asParametres[3], "UTF-8");

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

            lsContent = br.readLine();
//            tableauJSON = new JSONArray(lsContent);

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
            lsContent = "Erreur réseau";
        }

        // Renvoie la valeur a onPostExecute
        return lsContent;
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(String asResultat) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final

        if (asResultat.equalsIgnoreCase("Erreur réseau")) {
            textViewMessage.setText(asResultat);
        } else {
            try {
                JSONArray tJson = new JSONArray(asResultat);
                if (tJson.length() > 0) {
                    textViewMessage.setText("Suppression OK");

//                    JSONObject objet = tJson.getJSONObject(0);
//                    Globale.setNom(objet.getString("nom"));
//                    Globale.setEmail(objet.getString("email"));
//                    Globale.setId(objet.getString("id"));
//                    Globale.setMdp(objet.getString("mdp"));

                } else {
                    textViewMessage.setText("Erreur Suppression");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } /// if
    } /// onPostExecute
} /// TacheAsynchrone
