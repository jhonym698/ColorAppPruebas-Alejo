package android.colorapppruebas;

import android.colorapppruebas.data.Connection;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    Button button, btnDialog, btnMaps;
    Switch aSwitch;
    Connection connection = new Connection(this);
    EditText editText;
    EditText edThis;
    Button mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch3);
        
        cargarConfiguracion();

        edThis = findViewById(R.id.editText3);
        btnMaps = findViewById(R.id.btnMaps);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearAlert();
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = connection.getWritableDatabase();
                if (aSwitch.isChecked()){
                    sqLiteDatabase.execSQL("UPDATE configuracion SET estado = 'true'");
                } else {
                    sqLiteDatabase.execSQL("UPDATE configuracion SET estado = 'false'");
                }
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private AlertDialog crearAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_area,null);
        builder.setView(view);
        builder.create();
        builder.setCancelable(false);
        editText = (EditText)view.findViewById(R.id.editText2);
        editText.setText(edThis.getText().toString());
        builder.setPositiveButton("entrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edThis.setText(editText.getText().toString());
            }
        });


        return builder.show();
    }

    private void cargarConfiguracion() {
        try {
            SQLiteDatabase sqLiteDatabase = connection.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT estado FROM configuracion", null);
            cursor.moveToNext();
            if (cursor.getString(0).equals("true")){
                aSwitch.setChecked(true);
            } else {
                aSwitch.setChecked(false);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
