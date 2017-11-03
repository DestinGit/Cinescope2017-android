package db.fr.cinescope2017;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import asyncpackage.TacheAsynchroneInscription;
import asyncpackage.TaskAsyncUserGet;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView nom;
    private EditText password;
    private Button nom_sign_in_button;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        textViewMessage = (TextView)findViewById(R.id.textViewMessage);

        nom = (AutoCompleteTextView)findViewById(R.id.nom);
        password = (EditText)findViewById(R.id.password);
        nom_sign_in_button = (Button)findViewById(R.id.nom_sign_in_button);

        nom_sign_in_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==nom_sign_in_button) {
            String lsUrl = "http://172.26.10.39:8080/Cinescope2017Web/";
            String lsRessources = "User?action=get";

            new TaskAsyncUserGet(textViewMessage).execute(lsUrl, lsRessources,
                    nom.getText().toString(), password.getText().toString());
        }
    }
}
