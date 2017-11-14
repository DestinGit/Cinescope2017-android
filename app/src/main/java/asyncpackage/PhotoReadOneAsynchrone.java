package asyncpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import utilities.classes.Globale;

/**
 * Created by formation on 14/11/2017.
 */
public class PhotoReadOneAsynchrone extends AsyncTask<String, Integer, Bitmap> {

    private Context contexte;
    private ImageView imageView;

    public PhotoReadOneAsynchrone(Context contexte, ImageView imageView) {
        super();
        this.contexte = contexte;
        this.imageView = imageView;
    } /// Constructeur

    // ----------------------------
    protected Bitmap doInBackground(String... asParametres) {
        // --- Ressource distante
        Bitmap bitmap = null;
        try {
            String lsURL = "http://172.26.10.39/images/" + asParametres[0];
            URL url = new URL(lsURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // ca plante si l'image n'existe pas malgre le try/catch
                // c'est pour cela que la requete HEAD est testee avant
                InputStream is = url.openStream();
                // --- Chargement et decodage
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }
        } catch (Exception e) {
            Toast.makeText(contexte, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Renvoie la valeur a onPostExecute
        return bitmap;
    } /// doInBackground

    @Override
    // -------------------------
    protected void onPostExecute(Bitmap bitmap) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
        imageView.setImageBitmap(bitmap);
    } /// onPostExecute

} /// class