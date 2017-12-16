package com.miramicodigo.restful2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    // 1.
    private EditText etUser, etPassword;

    private TextView tvLink;

    // 2.
    private Button btnIngresar, btnRegistrar;
    // 3.
    private ProgressBar pbLogin;

    private Intent intent;

    private List<Persona> listaPersonas;

    // 5.
    // URL servicio que nos autenticara
    private static String URL = "http://androidbolivia.com/tekhnev2/login.php";

    // 9.
    private String username, password;

    // XX.
    //  sharepreferences
    //SharedPreferences sharedPreferences;
    //SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // para ocultar el tool Bar en moo desarrollo
        getSupportActionBar().hide();

        // 4.
        //
        etUser = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistro);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        tvLink = (TextView) findViewById(R.id.tvLink);

        tvLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Evento Texto");

                abrirPaginaWeb();

            }
        });

        // 6.
        btnRegistrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                // 6.1
                // enviando a otr activity
                finish();

            }
        });

        // 7.
        btnIngresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // 7.1
                // validando formulario
                valida();
            }
        });

        // agregand lista
        listaPersonas = iniciarUsuarios();



        // XX.1
        // iniciando preferencia
        Context context = getApplicationContext();

      /*  sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = sharedPreferences.edit();
*/

    }

    public void valida(){

        if(etUser.getText().toString().equals("") ||
                etPassword.getText().toString().equals("")){
            // 8.
            // enviand mensaje de vacio
            esVacio();
        } else {
            // 9.
            login();
        }

    }

    public void login() {

        // 10.
        username = etUser.getText().toString();
        password = etPassword.getText().toString();

        // 11.
        // mostrando progress bar
        pbLogin.setIndeterminate(true);
        pbLogin.setVisibility(View.VISIBLE);

        boolean usuarioExiste = false;

        Persona personaSeleccionada = null;

        // validand usuarios
        for(Persona persona : listaPersonas){

            if(persona.getLogin().equals(username)){
                if(persona.getPassword().equals(password)){
                    usuarioExiste = true;
                    personaSeleccionada = persona;
                    break;
                }
            }

        }

        pbLogin.setIndeterminate(false);
        pbLogin.setVisibility(View.INVISIBLE);

        if(usuarioExiste){

            responseOk(personaSeleccionada);

        } else {
            mostrarMensaje("Usuario o contraseña incorrecta, verifique.");
        }

    }


    public void responseOk(Persona persona) {

        SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
        editor.putString("login", persona.getLogin());
        editor.putString("session", "12345");
        editor.apply();

            // 17.
            // cargando parametros
            Intent intent = new Intent(getApplicationContext(), InformacionActivity.class);

            intent.putExtra("nom", persona.getNombre());
            intent.putExtra("ape", persona.getApellidos());
            intent.putExtra("ed", persona.getEdad().toString());
            intent.putExtra("cor", persona.getCorreo());
            intent.putExtra("gen", persona.getGenero());

            startActivity(intent);

//            String respuesta = aux.getString("nombres") + " - " + aux.getString("apellidos");

  //          Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();

        /*} else {
            System.out.println("Respuesta Erronea");
            Toast.makeText(getApplicationContext(), "Sus datos son incorrectos", Toast.LENGTH_LONG).show();
        }*/


    }



    public void esVacio() {
        Toast.makeText(getApplicationContext(),
                "Existen campos vacios",
                Toast.LENGTH_SHORT).show();
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(500);
    }

    public void mostrarMensaje(String mensaje){
        Toast.makeText(getApplicationContext(),
                mensaje,
                Toast.LENGTH_SHORT).show();
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(500);
    }

    public List<Persona> iniciarUsuarios(){

        List<Persona> lista = new ArrayList<Persona>();

        Persona persona = new Persona();

        persona.setCorreo("melissa@gmail.com");
        persona.setEdad(7);
        persona.setGenero("Femenino");
        persona.setLogin("melissa");
        persona.setNombre("Melissa");
        persona.setApellidos("Ibanez");
        persona.setPassword("melissa");

        lista.add(persona);

        Persona persona2 = new Persona();

        persona2.setCorreo("andre@gmail.com");
        persona2.setEdad(2);
        persona2.setGenero("Masculino");
        persona2.setLogin("andre");
        persona2.setNombre("Andre");
        persona2.setApellidos("Ibanez");
        persona2.setPassword("andre");

        lista.add(persona2);

        return lista;
    }

    public void abrirPaginaWeb() {

        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com.bo"));
        startActivity(intent);
    }


}

