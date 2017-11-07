package db.fr.cinescope2017;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import asyncpackage.CallbackInterface;
import asyncpackage.GenericAsyncTask;
import utilities.db.OpenManagerSQLite;

public class ImportBD extends AppCompatActivity implements View.OnClickListener {
//    private OpenManagerSQLite oms;
//    private SQLiteDatabase bd;

    private TextView textViewMessage;
    private Button countriesImportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_bd);

        initInterface();
        initEvents();
    }

    /**
     *
     */
    private void initInterface() {
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        countriesImportButton = (Button) findViewById(R.id.countriesImportButton);
    }

    /**
     *
     */
    private void initEvents() {
        countriesImportButton.setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v == countriesImportButton) {
            OpenManagerSQLite oms = new OpenManagerSQLite(this, null);
            SQLiteDatabase bd = oms.getWritableDatabase();

            // Préparation des params
            Map<String, String> params = new HashMap();
            params.put("lsUrl", "http://172.26.10.39:8080/Cinescope2017Web/");
            params.put("lsRes", "GetData?table=ville");

            // Lancement de la tâche asynchrone
            new GenericAsyncTask(new MyCallback(textViewMessage, oms, bd)).execute(params);
        }
    }

    /**
     * Class MyCallback pour la gestion du retour de la tâche asynchrone
     */
    private static class MyCallback implements CallbackInterface {
        private TextView textViewMessage;
        private OpenManagerSQLite oms;
        private SQLiteDatabase bd;

        /**
         * Constructor
         *
         * @param textViewMessage
         * @param oms
         * @param bd
         */
        public MyCallback(TextView textViewMessage, OpenManagerSQLite oms, SQLiteDatabase bd) {
            this.textViewMessage = textViewMessage;
            this.oms = oms;
            this.bd = bd;
        }

        /**
         * Execute à la fin de la tâche Asynchrone
         *
         * @param json
         */
        @Override
        public void onTaskFinished(JSONArray json) {
            // Insertion des données
            if (json.length() > 0) {
                saveDataIntoDB(json);
            } else {
                this.textViewMessage.setText("Finished Nothing");
            }
            // Fermeture de la BD
            oms.close();
            bd = null;
        }

        /**
         * Insertion des données dans la table
         *
         * @param json
         */
        private void saveDataIntoDB(JSONArray json) {
            try {
                // début de la transaction
                bd.beginTransaction();

                JSONObject obj;
                ContentValues hmValues = new ContentValues();
                for (int i = 0; i < json.length(); i++) {
                    obj = json.getJSONObject(i);

                    hmValues.put("ID_VILLE", obj.getInt("ID_VILLE"));
                    hmValues.put("ID_DEPARTEMENT", obj.getInt("ID_DEPARTEMENT"));
                    hmValues.put("CP", obj.getString("CP"));
                    hmValues.put("NOM_VILLE", obj.getString("NOM_VILLE"));

                    // Inserte renvoi le nouvel ID
                    long llNum = bd.insert("ville", null, hmValues);
                }
                // commit
                bd.setTransactionSuccessful();
                // Fin de la transaction
                bd.endTransaction();
                // Afficher le message de fin
                this.textViewMessage.setText("Finished");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
