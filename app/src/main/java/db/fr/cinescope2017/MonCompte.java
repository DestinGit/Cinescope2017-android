package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import asyncpackage.TaskAsyncUserGet;
import asyncpackage.TaskAsyncUserGetDel;
import asyncpackage.TaskAsyncUserGetEdit;

public class MonCompte extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSupprimer, buttonModifier;
    private EditText editTextUser, editTextEmail, editTextPwd;
    private TextView textViewID, textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mon_compte);

        initInterface();
        initEvents();

        Bundle params = this.getIntent().getExtras();
        String id = params.getString("id");
        String nom = params.getString("nom");
        String mdp = params.getString("mdp");
        String email = params.getString("email");

        textViewID.setText(id);
        editTextUser.setText(nom);
        editTextPwd.setText(mdp);
        editTextEmail.setText(email);

    } //onCreate

    private void initInterface() {
        buttonSupprimer = (Button)findViewById(R.id.buttonSupprimer);
        buttonModifier = (Button)findViewById(R.id.buttonModifier);

        editTextUser = (EditText)findViewById(R.id.editTextUser);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPwd = (EditText)findViewById(R.id.editTextPwd);

        textViewID = (TextView)findViewById(R.id.textViewID);
        textViewMessage = (TextView)findViewById(R.id.textViewMessage);
    }

    private void initEvents() {
        buttonSupprimer.setOnClickListener(this);
        buttonModifier.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==buttonSupprimer){
            String lsUrl = "http://172.26.10.39:8080/Cinescope2017Web/";
            String lsRessources = "User?action=delete";

            new TaskAsyncUserGetDel(textViewMessage).execute(lsUrl, lsRessources, textViewID.getText().toString());

        }

        if (v==buttonModifier){
            String lsUrl = "http://172.26.10.39:8080/Cinescope2017Web/";
            String lsRessources = "User?action=edit";

            new TaskAsyncUserGetEdit(textViewMessage).execute(lsUrl, lsRessources,
                    textViewID.getText().toString(), editTextEmail.getText().toString(), editTextPwd.getText().toString());

        }
    }
}/// classe
