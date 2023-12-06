package labortime.app.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {
    private DatabaseHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void agregarUsuario(String rut, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rut", rut);
        values.put("password", password);

        db.insert("usuarios", null, values);

        db.close();
    }

    public boolean verificarUsuario(String rut, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "usuarios",
                null,
                "rut = ? AND password = ?",
                new String[]{rut, password},
                null,
                null,
                null
        );

        boolean resultado = cursor.moveToFirst();

        cursor.close();
        db.close();

        return resultado;
    }
}
