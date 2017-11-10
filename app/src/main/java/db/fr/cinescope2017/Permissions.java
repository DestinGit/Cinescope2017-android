package db.fr.cinescope2017;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Permissions extends AppCompatActivity implements View.OnClickListener {
    private final int PERMISSION_REQUEST_ALL = 99;

    private CheckBox checkBoxCamera;
    private CheckBox checkBoxRecordAudio;
    private CheckBox checkBoxStorage;

    private Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions);

        checkBoxCamera = (CheckBox) findViewById(R.id.checkBoxCamera);
        checkBoxRecordAudio = (CheckBox) findViewById(R.id.checkBoxRecordAudio);
        checkBoxStorage = (CheckBox) findViewById(R.id.checkBoxStorage);

        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(this);

    } /// onCreate


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("Granted", Integer.toString(grantResults.length));
        /*
        Cette méthode est appelée par ActivityCompat.requestPermissions()
        */
        switch (requestCode) {
            case PERMISSION_REQUEST_ALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        Toast.makeText(this, permissions[i], Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "La requête a été annulée", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        } /// switch
    } /// onRequestPermissionsResult

    @Override
    public void onClick(View v) {
        boolean lbEtatCamera = checkBoxCamera.isChecked();
        boolean lbEtatRecordAudio = checkBoxRecordAudio.isChecked();
        boolean lbEtatStorage = checkBoxStorage.isChecked();

        /*
        Toutes les permissions en une seule fois avec une seule boite de dialogue et un phenomeme de "diapo"
         */
        List<String> listePermissions = new ArrayList();
        if (lbEtatCamera) {
            listePermissions.add(Manifest.permission.CAMERA);
        }
        if (lbEtatRecordAudio) {
            listePermissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (lbEtatStorage) {
            listePermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (listePermissions.size() > 0) {
            String[] tPermissions = listePermissions.toArray(new String[listePermissions.size()]);
            ActivityCompat.requestPermissions(this, tPermissions, PERMISSION_REQUEST_ALL);
        }
    } /// onClick
}
