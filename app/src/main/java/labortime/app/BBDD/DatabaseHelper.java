package labortime.app.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "mi_base_de_datos";
    private static final int DATABASE_VERSION = 1;

    // Consulta SQL para crear la tabla de usuarios
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, rut TEXT, password TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Puedes implementar la actualización de la base de datos si es necesario
    }
}
