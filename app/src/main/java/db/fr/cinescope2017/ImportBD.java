package db.fr.cinescope2017;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import asyncpackage.CallbackInterface;
import asyncpackage.GenericAsyncTask;
import utilities.db.OpenManagerSQLite;

public class ImportBD extends AppCompatActivity implements View.OnClickListener{
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
        textViewMessage = (TextView)findViewById(R.id.textViewMessage);
        countriesImportButton = (Button)findViewById(R.id.countriesImportButton);
    }

    /**
     *
     */
    private void initEvents() {
        countriesImportButton.setOnClickListener(this);
    }

    /**
     *
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
            params.put("lsRes", "Ville?action=get");

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

        public MyCallback(TextView textViewMessage, OpenManagerSQLite oms, SQLiteDatabase bd) {
            this.textViewMessage = textViewMessage;
            this.oms = oms;
            this.bd = bd;
        }

        @Override
        public void onTaskFinished(JSONArray result) {
            // Insertion des données
            // do something
            // do something

            // Fermeture de la BD
            oms.close();
            bd = null;
        }
    }
}
