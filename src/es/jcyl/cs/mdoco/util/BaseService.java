package es.jcyl.cs.mdoco.util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseService {
    protected DOCODatabaseHelper dbHelper;
    protected SQLiteDatabase database;

    public BaseService(Context context) {
        dbHelper = new DOCODatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
