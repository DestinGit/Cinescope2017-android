package db.fr.cinescope2017;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import asyncpackage.CallbackInterface;
import asyncpackage.GenericAsyncTask;
import asyncpackage.TacheAsynchroneInscription;
import asyncpackage.TaskAsyncUserGet;
import utilities.classes.Globale;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView nom;
    private EditText password;
    private Button nom_sign_in_button;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        nom = (AutoCompleteTextView) findViewById(R.id.nom);
        password = (EditText) findViewById(R.id.password);
        nom_sign_in_button = (Button) findViewById(R.id.nom_sign_in_button);

        nom_sign_in_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == nom_sign_in_button) {

            Map<String, String> params = new HashMap();
            params.put("lsUrl", "http://172.26.10.39:8080/Cinescope2017Web/");
            params.put("lsRes", "User?action=get");
            params.put("nom",  nom.getText().toString());
            params.put("mdp",  password.getText().toString());

            new GenericAsyncTask(new MyCallback(textViewMessage)).execute(params);

        }
    }

    private static class MyCallback implements CallbackInterface {
        private TextView textViewMessage;

        public MyCallback(TextView textViewMessage) {
            this.textViewMessage = textViewMessage;
        }

        @Override
        public void onTaskFinished(String res) {
            try {
                JSONArray result = new JSONArray(res);
                if (result.length() > 0) {
                    textViewMessage.setText("Vous etes connectés");

                    JSONObject objet = result.getJSONObject(0);
                    Globale.setNom(objet.getString("nom"));
                    Globale.setEmail(objet.getString("email"));
                    Globale.setId(objet.getString("id"));
                    Globale.setMdp(objet.getString("mdp"));

                } else {
                    textViewMessage.setText("Erreur d'authentification");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
