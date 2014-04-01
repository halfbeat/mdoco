package es.jcyl.cs.mdoco.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DOCODatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DOCO_DATABASE_NAME = "doco.db";
    public static final String DOCO_USUARIOS_TABLE = "DOCO_USUARIOS";
    public static final String DOCO_USUARIOS_ID = "C_USUARIO_ID";
    public static final String DOCO_USUARIOS_NIF = "C_NIF_ID";
    public static final String DOCO_USUARIOS_PASS = "A_PASSWORD";
    private static final String DOCO_DB_CREATE = "CREATE TABLE " + DOCO_USUARIOS_TABLE + " (" +
            DOCO_USUARIOS_ID + " integer primary key autoincrement" + " , " +
            DOCO_USUARIOS_NIF + " text not null" + " , " +
            DOCO_USUARIOS_PASS + " text not null);";

    public DOCODatabaseHelper(Context context) {
        super(context, DOCO_DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
          db.execSQL(DOCO_DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DOCODatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DOCO_USUARIOS_TABLE);
        onCreate(db);
    }

}
