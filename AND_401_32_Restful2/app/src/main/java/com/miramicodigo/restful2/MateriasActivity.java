package com.miramicodigo.restful2;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MateriasActivity extends AppCompatActivity {

    private ArrayList<Materia> datos;
    private RecyclerView rvDatos;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);



        activity = this;

        llenarMaterias();

        rvDatos = (RecyclerView) findViewById(R.id.rvMaterias);

        //layoutManager = new GridLayoutManager(this, 2);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvDatos.setLayoutManager(layoutManager);

        adapter = new RVMateriaAdapter(activity, datos);
        rvDatos.setAdapter(adapter);

        // registrando
        registerForContextMenu(rvDatos);

    }

    public void llenarMaterias() {
        datos = new ArrayList<Materia>();
        Resources resources = getResources();
        String[] arrayNomMaterias = resources.getStringArray(R.array.matterName);
        String[] arrayCodMaterias = resources.getStringArray(R.array.matterInitial);
        String[] arrayTipoMaterias = resources.getStringArray(R.array.matterType);

        String tipoMateria = null;
        int imagen = 0;

        for(int i=0; i < arrayNomMaterias.length; i++) {

            tipoMateria = arrayTipoMaterias[i];

            if(tipoMateria.equals("talk")){
                imagen = R.drawable.class_talk;
            }
            if(tipoMateria.equals("lab")){
                imagen = R.drawable.class_lab;
            }

            Materia materia = new Materia(arrayCodMaterias[i], arrayNomMaterias[i], imagen);

            datos.add(materia);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuContextUno:
                Toast.makeText(getApplicationContext(), "Click en menu contextual 1", Toast.LENGTH_LONG ).show();
                return true;
            case R.id.menuContextDos:
                Toast.makeText(getApplicationContext(), "Click en menu contextual 2", Toast.LENGTH_LONG ).show();
                return true;
            case R.id.menuContextTres:
                Toast.makeText(getApplicationContext(), "Click en menu contextual 3", Toast.LENGTH_LONG ).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}
