package asyncpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.*;
import java.io.*;
import java.net.*;


/**
 * Created by Administrateur on 31/10/2017.
 */
public class TAFilmGetIdTitre extends AsyncTask<String, Integer, JSONArray> {

    private Context contexte;
    private ListView lv;


    public TAFilmGetIdTitre() {
    }

    public TAFilmGetIdTitre(Context contexte, ListView lv) {
        this.contexte = contexte;
        this.lv = lv;
        Log.i("construct", "construct");
    }

    @Override
    // ----------------------------
    protected JSONArray doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan

        JSONArray tableauJSON = null;

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsDebutdeChaine = asParametres[2];

        URL url = null;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource + "?chaine=" + lsDebutdeChaine);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContenu = br.readLine();
            Log.i("lsContenu", lsContenu);
            tableauJSON = new JSONArray(lsContenu);
            br.close();
            is.close();
        } catch (Exception e) {
            //lsb.append(e.getMessage());
            //tableauJSON = new JSONArray(e.getMessage());
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }
        // Renvoie la valeur a onPostExecute
        return tableauJSON;
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(JSONArray arrayJSON) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
        StringBuilder lsb = new StringBuilder();
        JSONObject objet;
        int n = arrayJSON.length();
        String[] tTitresIds = new String[n];
        try {
            for (int i = 0; i < arrayJSON.length(); i++) {
                //objet=new JSONObject();
                objet = (JSONObject) arrayJSON.get(i);
                tTitresIds[i] = objet.get("id") + "-" + objet.get("titre");
            }
        } catch (Exception e) {
            //lsAdresse = e.getMessage();
            Log.i("erreur", e.getMessage());
        }
        ArrayAdapter<String> aaListe = new ArrayAdapter<>(contexte, android.R.layout.simple_list_item_1, tTitresIds);
        lv.setAdapter(aaListe);

    } /// onPostExecute
} /// TacheAsynchrone