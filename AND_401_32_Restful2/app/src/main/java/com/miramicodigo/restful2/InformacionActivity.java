package com.miramicodigo.restful2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InformacionActivity extends AppCompatActivity {

    // 18.

    private TextView tvNombre, tvEdad, tvCorreo, tvGenero, tvMensaje;

    private ImageView ivGenero;

    private Button btnDocentes, btnMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        verSession();

        // 19.
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        tvGenero = (TextView) findViewById(R.id.tvGenero);
        tvMensaje = (TextView) findViewById(R.id.tvMensaje);

        ivGenero = (ImageView) findViewById(R.id.ivGenero);

        btnDocentes = (Button) findViewById(R.id.btnDocentes);
        btnMaterias = (Button) findViewById(R.id.btnMaterias);

        btnDocentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cargando parametros
                Intent intent = new Intent(getApplicationContext(), DocentesActivity.class);

                startActivity(intent);
            }
        });

        btnMaterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cargando parametros
                Intent intent = new Intent(getApplicationContext(), MateriasActivity.class);

                startActivity(intent);
            }
        });

        // 20.
        // recupernado objeto que trae parametros
        Bundle bundle = getIntent().getExtras();

        System.out.println("Edad: " + bundle.getString("ed"));

        tvNombre.setText(bundle.getString("nom") + " " + bundle.getString("ape"));
        tvEdad.setText(bundle.getString("ed") + " años");
        tvCorreo.setText(bundle.getString("cor"));


        String genero = bundle.getString("gen");

        tvGenero.setText(genero);

        if("Masculino".equals(genero)){
            tvMensaje.setText("Bienvenido");

            ivGenero.setImageResource(R.drawable.man);
        }

        if("Femenino".equals(genero)){
            tvMensaje.setText("Bienvenida");
            ivGenero.setImageResource(R.drawable.woman);

        }

        ///////////////////////////////
        ///////////////////////////


        //}


    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("STOP::::::::::::::::::::::::");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("DESTROY::::::::::::::::::::::::");
        finish();
    }

    public void verSession(){
        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        //String restoredText = prefs.getString("text", null);
        //if (restoredText != null) {
        String session = prefs.getString("session", null);
        String login = prefs.getString("login", null);

        System.out.println(login + " == " + session);

        if(session == null || login == null){
            finish();
        }

    }

}
