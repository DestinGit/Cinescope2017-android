package utilities.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;

import utilities.db.OpenManagerSQLite;

/**
 * Created by formation on 07/11/2017.
 */
public class ProviderSQLiteVille extends ContentProvider {

    private SQLiteDatabase db;

    public static final Uri CONTENT_URI = Uri.parse("content://utilities.provider.ProviderSQLiteVille");

    @Override
    public boolean onCreate() {
        Context context = getContext();
        // Connexion à la BD
        OpenManagerSQLite dbHelper = new OpenManagerSQLite(context, null);
        this.db = dbHelper.getWritableDatabase();
        return (this.db == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor curseur = null;
        // --- Tous les enregistrements
        try {
            // --- query(table, tColonnes, sWhere, tParamsWhere, sGroupBy, sHaving, sOrderBy)
            curseur = this.db.query("ville", projection, selection, selectionArgs, null, null, sortOrder);
        }
        catch(SQLiteException e) {
            curseur = null;
        }
        return curseur;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
} /// Class
