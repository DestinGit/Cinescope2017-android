package db.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import asyncpackage.PhotoReadOneAsynchrone;

public class UnFilm extends AppCompatActivity {
private ImageView imageViewPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_film);

        imageViewPhoto = (ImageView)findViewById(R.id.imageViewPhoto);
        new PhotoReadOneAsynchrone(this, imageViewPhoto).execute("FA_illustration_000218_0x260.jpg");
    }
}
