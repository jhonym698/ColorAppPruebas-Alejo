package android.colorapppruebas.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper{
    public Connection(Context context) {
        super(context, "bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE  TABLE  configuracion (estado TEXT)");
        sqLiteDatabase.execSQL("CREATE  TABLE  puntuacion (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, puntaje INTEGER, tiempo INTEGER, configuracion TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO configuracion VALUES('true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',2,3,'true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',6,3,'true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',9,3,'true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',23,3,'true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',10,3,'true')");
        sqLiteDatabase.execSQL("INSERT INTO puntuacion (nombre, puntaje, tiempo, configuracion) VALUES('Alejo',1,3,'true')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
