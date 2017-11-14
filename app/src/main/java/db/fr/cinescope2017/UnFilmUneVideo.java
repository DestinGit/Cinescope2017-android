package db.fr.cinescope2017;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class UnFilmUneVideo extends AppCompatActivity {
    private VideoView videoViewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_film_une_video);
        videoViewVideo = (VideoView) findViewById(R.id.videoViewVideo);

        lectureVideo();
    }

    private void lectureVideo() {
        // --- Ressource distante ... trop lent
        try {
//             videoViewVideo.setVideoURI(Uri.parse("http://10.3.50.124/videos/requin.mp4"));
            videoViewVideo.setVideoURI(Uri.parse("http://www.youtube.com/watch?feature=player_detailpage&v=4aQHyxeo-pg"));
            videoViewVideo.start();
        } catch (Exception e) {

        }
    }
}
