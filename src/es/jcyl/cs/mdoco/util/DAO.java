package es.jcyl.cs.mdoco.util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO<T extends DBItem> {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public DAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}
