package es.jcyl.cs.mdoco.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import es.jcyl.cs.mdoco.util.DOCODatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDatasource {
    private DOCODatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = {DOCODatabaseHelper.DOCO_USUARIOS_ID, DOCODatabaseHelper.DOCO_USUARIOS_NIF,
            DOCODatabaseHelper.DOCO_USUARIOS_PASS};

    public UsuariosDatasource(Context context) {
        dbHelper = new DOCODatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Usuario> obttenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        Cursor cursor = database.query(DOCODatabaseHelper.DOCO_USUARIOS_TABLE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario usuario = cursorToComment(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return usuarios;
    }

    private Usuario cursorToComment(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(0));
        usuario.setNif(cursor.getString(1));
        usuario.setPassword(cursor.getString(2));
        return usuario;
    }

    public Usuario aniadirUsuario(String nif, String passwd) {
        ContentValues values = new ContentValues();
        values.put(DOCODatabaseHelper.DOCO_USUARIOS_NIF, nif);
        values.put(DOCODatabaseHelper.DOCO_USUARIOS_PASS, passwd);
        long insertId = database.insert(DOCODatabaseHelper.DOCO_USUARIOS_TABLE, null,
                values);
        Cursor cursor = database.query(DOCODatabaseHelper.DOCO_USUARIOS_TABLE,
                allColumns, DOCODatabaseHelper.DOCO_USUARIOS_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Usuario nuevoUsuario = cursorToComment(cursor);
        cursor.close();
        return nuevoUsuario;

    }
}
