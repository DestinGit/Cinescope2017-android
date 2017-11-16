package asynchronous.other;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import db.fr.cinescope2017.TousLesFilms;

/**
 * Created by formation on 16/11/2017.
 */
public class AllMoviesAsyncTask extends AsyncTask<Map<String, String>, Integer, JSONArray> {
    private Context context;
    private ListView listViewWidget;

    public AllMoviesAsyncTask() {
    }

    public AllMoviesAsyncTask(Context context, ListView listViewWidget) {
        this.context = context;
        this.listViewWidget = listViewWidget;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        // Instanciation de la boite de progression
//        progressDialog = new ProgressDialog(this);
//
//        // Le texte de la boite de progression
//        progressDialog.setMessage("Patientez ...");
//
//        // Affiche la boite de progression
//        progressDialog.show();
//
//        //
//        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface di) {
////                di.cancel(true);
//            }
//        });

    }

    @Override
    protected JSONArray doInBackground(Map... asParams) {
        JSONArray arrJSON = null;
        Map<String, String> params2 = asParams[0];

        URL url = null;
        HttpURLConnection httpConnection = null;

        // Get the uri and resources
        String uriWithRes = getUriWithResouces(params2);
        // Get the uri parameters'
        StringBuilder uriParams = getUriParameters(params2);

//        arrJSON = performRequestAndGetResponseWithMethod("POST", uriWithRes, uriParams);
        arrJSON = performRequestAndGetResponse(uriWithRes, uriParams);

        return arrJSON;
    }


    @Override
    // ----------------------------
    protected void onProgressUpdate(Integer... aiProgress) {

    }

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

        ArrayAdapter<String> aaList = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, tTitresIds);
        listViewWidget.setAdapter(aaList);

//        progressDialog.cancel();
    }

    /**
     * Perform Request and get the JSON response
     * @param uriWithRes
     * @param uriParams
     * @return
     */
    private JSONArray performRequestAndGetResponse(String uriWithRes, StringBuilder uriParams) {
        JSONArray arrJSON = null;
        HttpURLConnection httpConnection = null;

        try{
            // HttpURLConnection of instantiation with url object
            URL url = new URL(uriWithRes + uriParams);

            // Prepare connection
            httpConnection = (HttpURLConnection) url.openConnection();

            // connection
            httpConnection.connect();

            // Perform http request
            InputStream is = httpConnection.getInputStream();

            // get response
            // as we receive an ASCII stream
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContent = br.readLine();

            arrJSON = new JSONArray(lsContent);

            br.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            arrJSON = null;
        } catch (IOException e) {
            e.printStackTrace();
            arrJSON = null;
        } catch (JSONException e) {
            e.printStackTrace();
            arrJSON = null;
        } finally {
            // Disconnection
            httpConnection.disconnect();
        }

        return arrJSON;
    }

    /**
     * Get the uri and resources
     * @param params2
     * @return
     */
    private String getUriWithResouces(Map<String, String> params2) {
        String uriWithRes = params2.get("lsUrl") + params2.get("lsRes");
        return uriWithRes;
    }

    /**
     * Get the uri parameters'
     * @param params2
     * @return
     */
    private StringBuilder getUriParameters(Map<String, String> params2) {
        Set<String> keys = params2.keySet();
        StringBuilder urlParameters = new StringBuilder();
        boolean firstParam = true;

        for (String key: keys) {
            if (!key.equals("lsUrl") && !key.equals("lsRes")) {
                String clause = (firstParam) ? "?":"&";

                urlParameters.append(clause);
                urlParameters.append(key);
                urlParameters.append("=");
                urlParameters.append(params2.get(key));

                firstParam = false;
            }
        }

        return urlParameters;
    }
/*
    private JSONArray performRequestAndGetResponseWithMethod(String method, String uriWithRes, StringBuilder uriParams) {
        JSONArray arrJSON = null;
        HttpURLConnection httpConnection = null;
        try {
            // HttpURLConnection of instantiation with url object
            URL url = new URL(uriWithRes);

            // Prepare connection
            httpConnection = (HttpURLConnection) url.openConnection();
            // Choix de la methode get ou post
            // Il faudra passer en POST
            httpConnection.setRequestMethod(method);

            // Autorise l'envoi de donnees
            // Sets the flag indicating whether this URLConnection allows input.
            httpConnection.setDoInput(true);

            // Connexion
            httpConnection.connect();

            String params = uriParams.toString();
Log.i("GGGGGG : ", uriWithRes);
Log.i("GGGGGG : ", params);
            // Execution de la requete parametree
            OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());
            osw.write(params);
            osw.close();


            // Perform http request
            InputStream is = httpConnection.getInputStream();

            // get response
            // as we receive an ASCII stream
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContent = br.readLine();

            arrJSON = new JSONArray(lsContent);

            br.close();
            is.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
            arrJSON = null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            arrJSON = null;
        } catch (IOException e) {
            e.printStackTrace();
            arrJSON = null;
        } catch (JSONException e) {
            e.printStackTrace();
            arrJSON = null;
        }
        return arrJSON;
    }

*/

}
