package asyncpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import db.fr.cinescope2017.R;

/**
 * Created by formation on 31/10/2017.
 */
public class GenericAsyncTask extends AsyncTask<Map<String, String>, Integer, String> {
    private CallbackInterface callback;

    public GenericAsyncTask() {
    }

    public GenericAsyncTask(CallbackInterface callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Map... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        StringBuilder lsbResultat = new StringBuilder();
        String lsURL = "", lsRessources = "";
        URL urlConnection = null;
        HttpURLConnection httpConnection = null;
        boolean lbErreur = false;

        String lsContent = "";

        Map<String, String> params2 = asParametres[0];
        Set<String> keys = params2.keySet();
        StringBuilder urlParameters = new StringBuilder();
        for (String cle: keys) {
            if (!cle.equals("lsUrl") && !cle.equals("lsRes")) {
                urlParameters.append("&");
                urlParameters.append(cle);
                urlParameters.append("=");
                urlParameters.append(params2.get(cle));
            }
        }

        try {
            // Probleme avec les espaces
            // Donc URL_encode ...

            lsURL = params2.get("lsUrl");
            lsRessources = params2.get("lsRes");

            Log.i("LLLLLL : ", lsURL);
            Log.i("LLLLLL : ", lsRessources);

            // Instanciation de HttpURLConnection avec l'objet url
            urlConnection = new URL(lsURL + lsRessources);
            httpConnection = (HttpURLConnection) urlConnection.openConnection();

            // Choix de la methode get ou post
            // Il faudra passer en POST
            httpConnection.setRequestMethod("POST");

            // Autorise l'envoi de donnees
            // Sets the flag indicating whether this URLConnection allows input.
            httpConnection.setDoInput(true);

            // Connexion
            httpConnection.connect();

            String params = urlParameters.toString();
            Log.i("LLLLLL : ", params);
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
    protected void onPostExecute(String s) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
        JSONArray tableauJSON = null;
        try {
            tableauJSON = new JSONArray(s);
            callback.onTaskFinished(tableauJSON);
        } catch (JSONException e) {
            callback.onTaskFinished(tableauJSON);
            e.printStackTrace();
        }
    } /// onPostExecute
} /// TacheAsynchrone