package com.miramicodigo.restful2;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DocentesActivity extends AppCompatActivity {

    private ArrayList<Pokemon> datos;
    private RecyclerView rvDatos;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docentes);

        System.out.println("Construyendo Activity");

        activity = this;

        llenarPokemones();

        rvDatos = (RecyclerView) findViewById(R.id.rvContenido);

        layoutManager = new GridLayoutManager(this, 2);
        //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvDatos.setLayoutManager(layoutManager);

        adapter = new RVAdapter(activity, datos);
        rvDatos.setAdapter(adapter);

    }

    public void llenarPokemones() {
        datos = new ArrayList<Pokemon>();
        Resources resources = getResources();
        String[] arrayNombres = resources.getStringArray(R.array.teacherFullname);
        String[] arrayTipos = resources.getStringArray(R.array.teacherCareer);
        String[] arrayGenero = resources.getStringArray(R.array.teacherGender);

        String genero = null;
        int imagen = 0;

        for(int i=0; i < arrayNombres.length; i++) {

            genero = arrayGenero[i];

            if(genero.equals("masculino")){
                imagen = R.drawable.teacher_man;
            }
            if(genero.equals("femenino")){
                imagen = R.drawable.teacher_woman;
            }

            Pokemon poke = new Pokemon(
                    arrayNombres[i],
                    arrayTipos[i],
                    imagen
            );
            datos.add(poke);
        }
    }

}
