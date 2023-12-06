package labortime.app.Formularios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.common.reflect.Type;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import labortime.app.Datos.Formulario;
import labortime.app.R;

public class formularios extends AppCompatActivity {
    private Button btnsaveFormulario;
    private EditText etFecha;
    private EditText etHora;
    private EditText etTurno;
    private EditText etInicio;
    private EditText etTermino;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularios);

        etFecha = (EditText) findViewById(R.id.etFecha);
        etHora = (EditText) findViewById(R.id.etHora);
        etTurno = (EditText) findViewById(R.id.etTurno);
        etInicio = (EditText) findViewById(R.id.etInicio);
        etTermino = (EditText) findViewById(R.id.etTermino);
        btnsaveFormulario = (Button) findViewById(R.id.btnsaveFormulario);

        btnsaveFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fecha = etFecha.getText().toString();
                String hora = etHora.getText().toString();
                String turno = etTurno.getText().toString();
                String inicio = etInicio.getText().toString();
                String termino = etTermino.getText().toString();
                guardarFormulario();
                Toast.makeText(formularios.this, "Formulario guardado exitosamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(formularios.this, generarformularios.class);
                startActivity(i);
            }
        });
    }

    private void guardarFormulario() {
        // Obtener la referencia a SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("ListaFormularios", MODE_PRIVATE);

        // Obtener la lista actual de formularios (si existe)
        String listaFormulariosJson = sharedPreferences.getString("listaFormularios", null);

        // Convertir la lista actual de formularios de JSON a una lista de objetos (si existe)
        List<Formulario> listaFormularios = new ArrayList<>();
        if (listaFormulariosJson != null) {
            Type listaFormulariosType = new TypeToken<List<Formulario>>() {}.getType();
            listaFormularios = new Gson().fromJson(listaFormulariosJson, listaFormulariosType);
        }
        // Crear un nuevo formulario
        Formulario nuevoFormulario = new Formulario(etFecha, etHora, etTurno, etInicio, etTermino);

        // Agregar el nuevo formulario a la lista
        listaFormularios.add(nuevoFormulario);

        // Convertir la lista de formularios a JSON
        String nuevaListaFormulariosJson = new Gson().toJson(listaFormularios);

        // Guardar la nueva lista de formularios en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("listaFormularios", nuevaListaFormulariosJson);
        editor.apply();
    }
}

