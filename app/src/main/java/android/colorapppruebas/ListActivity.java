package android.colorapppruebas;

import android.colorapppruebas.data.Connection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView);

        cargarLista();
    }

    private void cargarLista() {
        try {
            Connection connection = new Connection(this);
            SQLiteDatabase sqLiteDatabase = connection.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM puntuacion ORDER BY puntaje DESC LIMIT 4", null);
            int i = 1;
            while (cursor.moveToNext()){
                list.add(i+" Puntaje : "+cursor.getString(2));
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
