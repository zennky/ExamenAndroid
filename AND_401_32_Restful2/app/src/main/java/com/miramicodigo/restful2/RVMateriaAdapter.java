package com.miramicodigo.restful2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Android-User07 on 15/12/2017.
 */

public class RVMateriaAdapter extends RecyclerView.Adapter<RVMateriaAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Materia> items;

    public RVMateriaAdapter(Activity activity, ArrayList<Materia> data) {
        this.context = activity;
        this.items = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Typeface tf_black = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_black.ttf");
        //Typeface tf_thin = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_thin.ttf");

       // holder.tvTitulo.setTypeface(tf_black);
        //holder.tvSubtitulo.setTypeface(tf_thin);

        Materia temp = items.get(position);
        holder.tvTitulo.setText(temp.getNombreMateria());
        holder.tvSubtitulo.setText(temp.getCodMateria());
        holder.ivImagen.setImageResource(temp.getImagenMaateria());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImagen;
        public TextView tvTitulo;
        public TextView tvSubtitulo;

        public ViewHolder(final View itemView) {
            super(itemView);
            ivImagen = (ImageView) itemView.findViewById(R.id.ivImagen);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = (TextView) itemView.findViewById(R.id.tvSubtitulo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();

                    String nombreMateria = tvTitulo.getText().toString();
                    String codMateria = tvSubtitulo.getText().toString();
                    String mensaje = "Materia: " + nombreMateria + "\nSiglas: " + codMateria;

                    Toast.makeText(context, mensaje,Toast.LENGTH_SHORT).show();

                    /*Intent intent = new Intent(
                            context, DetalleActivity.class);
                    intent.putExtra("poke", items.get(posicion));
                    */
                    //context.startActivity(intent);

                }
            });


        }
    }

}
