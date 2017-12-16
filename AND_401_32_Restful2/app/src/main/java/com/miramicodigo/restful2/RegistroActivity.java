package com.miramicodigo.restful2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombres;
    private EditText etApellidos;
    private EditText etCorreo;
    private EditText etCi;
    private EditText etUsuario;
    private EditText etPassword;

    private Button btnRegistrar, btnCancelar;
    private ProgressBar pbRegistro;

    // 27.
    private static String URL = "http://androidbolivia.com/tekhnev2/addUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // 21.
        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etCi = (EditText) findViewById(R.id.etCI);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnRegistrar = (Button) findViewById(R.id.btnAceptar);
        btnCancelar= (Button) findViewById(R.id.btnCancelar);

        pbRegistro = (ProgressBar) findViewById(R.id.pbRegistro);

        // 22.
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 24.
                registra();

            }
        });

        // 23
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 23.1
                finish();
            }
        });

    }

    /**
     * Metodo que realizar el enviao POST par el registro de un usuario
     */
    public void registra(){

        // 24.
        final String nombre = etNombres.getText().toString();
        final String apellidos = etApellidos.getText().toString();
        final String ci = etCi.getText().toString();
        final String correo = etCorreo.getText().toString();
        final String usuario = etUsuario.getText().toString();
        final String password = etPassword.getText().toString();

       //  25.
        // progress bar
        pbRegistro.setIndeterminate(true);
        pbRegistro.setVisibility(View.VISIBLE);

        //26.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 28.
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() { // evento de la respuesta el request
                    @Override
                    public void onResponse(String response) {

                        //  28.2
                        try {

                            // ocultando barra de progreso
                            pbRegistro.setIndeterminate(false);
                            pbRegistro.setVisibility(View.INVISIBLE);

                            // enviand a tratar mensaje Json
                            responseOk(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {  // error de la respuesta
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // ocultando barra de progreso
                        pbRegistro.setIndeterminate(false);
                        pbRegistro.setVisibility(View.INVISIBLE);

                        System.out.println(error.getMessage());

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                // 28. 1
                // seteando parametros a enviar
                // los key son los mimso que tienen el formulario  del servico de autenticacion
                params.put("nombres", nombre);
                params.put("apellidos", apellidos);
                params.put("carnet", ci);
                params.put("correo", correo);
                params.put("usuario", usuario);
                params.put("contrasenia", password);

                return params;
            }
        };

        // agregamos a la cola de peticiones
        requestQueue.add(stringRequest);

    }

    /**
     * Tratando objeto json
     *
     * @param response
     * @throws JSONException
     */
    public void responseOk(String response) throws JSONException{

        // 29.
        JSONObject jsonObject = new JSONObject(response);

        //30.
        String estadoRegistro = jsonObject.getString("status_add");

        // 31.
        if(estadoRegistro.equals("1")){

            Toast.makeText(getApplicationContext(), "Registro Correcto", Toast.LENGTH_LONG);

            // 32.
            // cerrando pantalla actual
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "Error de Registro", Toast.LENGTH_LONG);
        }

    }
}
