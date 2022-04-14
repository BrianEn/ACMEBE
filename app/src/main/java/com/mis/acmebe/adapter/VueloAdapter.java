package com.mis.acmebe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mis.acmebe.MyApplication;
import com.mis.acmebe.R;
import com.mis.acmebe.VueloSingleActivity;
import com.mis.acmebe.VuelosActivity;
import com.mis.acmebe.entity.Vuelo;

import java.util.List;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class VueloAdapter extends RecyclerView.Adapter<VueloAdapter.ViewHolder>{
    private List<Vuelo> vueloList;
    SharedPreferences preferences;
    Editor editor;

    public VueloAdapter(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View vueloView=layoutInflater.inflate(R.layout.activity_item_vuelo,parent,false);

        return new ViewHolder(vueloView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vuelo vuelo=vueloList.get(position);
        holder.textView1.setText(vuelo.getOrigen());
        holder.textView2.setText(vuelo.getDestino());
        holder.textView3.setText(vuelo.getPrecio());
        holder.textView4.setText(vuelo.getEscala());
        holder.textView5.setText(vuelo.getSalida());
        holder.textView6.setText(vuelo.getLlegada());
        holder.imageView.setImageResource(vuelo.getRecursoImageView());
        Context context = holder.textView1.getContext();

        preferences = context.getSharedPreferences("preferencias_usuario", Context.MODE_PRIVATE);
        editor = preferences.edit();
        holder.linearLayoutSingleVuelo.setOnClickListener(View-> {
            editor.putString("origen", vuelo.getOrigen());
            editor.putString("destino", vuelo.getDestino());
            editor.putString("precio", vuelo.getPrecio());
            editor.putString("escala", vuelo.getEscala());
            editor.putString("salida", vuelo.getSalida());
            editor.putString("llegada", vuelo.getLlegada());
            editor.putString("imagen", String.valueOf(vuelo.getRecursoImageView()));
            editor.commit();

            context.startActivity(new Intent(context, VueloSingleActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return vueloList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView textView1, textView2,textView3,textView4,textView5,textView6;
         ImageView imageView;
         LinearLayout linearLayoutSingleVuelo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageViewViaje);
            textView1= itemView.findViewById(R.id.textViewDestino);
            textView2= itemView.findViewById(R.id.textViewOrigen);
            textView3= itemView.findViewById(R.id.textViewPrecio);
            textView4= itemView.findViewById(R.id.textViewEscala);
            textView5= itemView.findViewById(R.id.textViewFechaSalida);
            textView6= itemView.findViewById(R.id.textViewFechaLlegada);
            linearLayoutSingleVuelo=itemView.findViewById(R.id.linearLayoutSingleVuelo);
        }
    }
}
