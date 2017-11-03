package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import android.view.View;
import android.view.View.*;
import android.view.Menu;
import android.view.MenuItem;

import asyncpackage.TacheAsynchroneInscription;

public class Inscription extends AppCompatActivity implements OnClickListener, OnFocusChangeListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.inscription);
//    }

    // Attributs pour les widgets
    private EditText editTextUser;
    private EditText editTextEmail;
    private EditText editTextPwd;
    private Button buttonValider;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        initInterface();
        initEvents();

    } /// onCreate

    /**
     *
     */
    private void initInterface() {
// Liaison widget <--> Attribut
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    } /// initInterface

    /**
     *
     */
    private void initEvents() {
// Liaison widget <--> Events
        editTextUser.setOnFocusChangeListener(this);
        editTextEmail.setOnFocusChangeListener(this);
        editTextPwd.setOnFocusChangeListener(this);
        buttonValider.setOnClickListener(this);
    } /// initEvents

    @Override
    public void onClick(View v) {

        if (v == buttonValider) {
            String lsUrl = "http://172.26.10.39:8080/Cinescope2017Web/";
            String lsRessources = "UtilisateurInsert";
            new TacheAsynchroneInscription(textViewMessage).execute(lsUrl,lsRessources, editTextUser.getText().toString(),
                    editTextPwd.getText().toString(), editTextEmail.getText().toString());

//            setResult(RESULT_CANCELED);
//            finish();

        }

    } /// onClick

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v == editTextUser) {
        }

        if (v == editTextEmail) {
        }

        if (v == editTextPwd) {
        }

    } /// onFocusChange

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    } /// onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } /// onCreateOptionsMenu

}
