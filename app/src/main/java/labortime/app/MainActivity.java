package labortime.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import labortime.app.BBDD.UsuarioDAO;
import labortime.app.Datos.Usuario;
import labortime.app.Formularios.generarformularios;


public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Button btn1;
    private ProgressBar pb1;
    private EditText etRut;
    private EditText etContraseña;
    private UsuarioDAO usuarioDAO;
    private static final String BROKER_URL = "mqtt://androidtestsiqq.cloud.shiftr.io:1883";
    private static final String CLIENT_ID = "Labor Time";
    private MqttHandler mqttHandler;
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();
    }
    private void publishMessage(String topic, String message){
        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic,message);
    }

    private void subscribeToTopic(String topic){
        Toast.makeText(this, "Subscribing to topic"+ topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);
    }

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = new UsuarioDAO(this);
        handler = new Handler();
        btn1 = (Button) findViewById(R.id.btn1);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        etRut = (EditText) findViewById(R.id.etRut);
        etContraseña = (EditText) findViewById(R.id.etContraseña);

        //inicializarFirebase();
        showProgressBar();
        hideProgressBar();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para manejar el inicio de sesión
                try {
                    iniciarSesion();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(MainActivity.this, generarformularios.class);
                startActivity(i);
            }
        });

        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,CLIENT_ID, getApplicationContext());
        subscribeToTopic("Tema1");
        publishMessage("Tema1", "hola");
    }

    private void showProgressBar() {
        pb1.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        pb1.setVisibility(View.INVISIBLE);
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databasereference = firebaseDatabase.getReference();

        String rut = null;
        String Contraseña = null;
        Usuario u = new Usuario(rut, Contraseña);
        u.setRut(rut);
        u.setContraseña(Contraseña);

    }

    private void iniciarSesion() {
        try {
            String rut = etRut.getText().toString();
            String password = etContraseña.getText().toString();

            // Verificar si las credenciales son correctas
            if (usuarioDAO.verificarUsuario(rut, password)) {
                // Inicio de sesión exitoso, redirigir a la siguiente actividad
                // Puedes reemplazar la siguiente línea con el código para iniciar la siguiente actividad
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            } else {
                // Las credenciales son incorrectas, mostrar un mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}