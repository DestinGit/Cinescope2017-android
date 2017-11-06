package utilities.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by formation on 06/11/2017.
 */
public class OpenManagerSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "cinescope2017.db";

    private static final String DROP_TABLE_COUNTRY = "DROP TABLE IF EXISTS ville";
    private static final String CREATE_TABLE_COUNTRY = "CREATE TABLE IF NOT EXISTS ville(" +
            "ID_VILLE INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ID_DEPARTEMENT INTEGER NOT NULL, " +
            "CP VARCHAR(5) NOT NULL, " +
            "NOM_VILLE VARCHAR(50) NOT NULL" +
            ")";

    public OpenManagerSQLite(Context context, SQLiteDatabase.CursorFactory factory) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(context, DB_NAME,factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // --- Creation de la table pays
        db.execSQL(CREATE_TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // --- Suppression de la table puis appel a la creation des tables
        db.execSQL(DROP_TABLE_COUNTRY);
        onCreate(db);
    }
}
