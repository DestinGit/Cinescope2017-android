package utilities.classes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import db.fr.cinescope2017.R;

/**
 * Created by Administrateur on 20/11/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context contexte;

    private Integer[] mThumbIds = {
            R.drawable.articles, R.drawable.reprises,
            R.drawable.aviscritiques, R.drawable.newfilms,
            R.drawable.festival, R.drawable.festival2,
    };

    public ImageAdapter(Context c) {
        contexte = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(contexte);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
