package com.mis.acmebe.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.mis.acmebe.FirestoreService;
import com.mis.acmebe.R;
import com.mis.acmebe.VueloSingleActivity;
import com.mis.acmebe.entity.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class VueloFavoritoAdapter extends RecyclerView.Adapter<VueloFavoritoAdapter.ViewHolder> implements EventListener<QuerySnapshot> {
    private final List<Vuelo> vueloList;
    private DataChangedListener mDataChangedListener;
    private ItemErrorListener mErrorListener;
    public final ListenerRegistration listenerRegistration;
    Context context;
    public VueloFavoritoAdapter(){
        this.vueloList=new ArrayList<>();
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        listenerRegistration = FirestoreService.getServiceInstance().getTravelsFiltered((com.google.firebase.firestore.EventListener<QuerySnapshot>) this);
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_vuelo, parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.textViewOrigen.setText(vueloList.get(position).getOrigen());
        holder.textViewDestino.setText(vueloList.get(position).getDestino());
        holder.textViewEscala.setText(vueloList.get(position).getEscalas().toString());
        holder.textViewPrecio.setText("" + vueloList.get(position).getPrecio());
        holder.textViewFechaLlegada.setText(vueloList.get(position).getFechallegada());
        holder.textViewFechaSalida.setText(vueloList.get(position).getFechasalida());
        holder.imageViewViaje.setOnClickListener(view -> {
            context.startActivity(new Intent(context, VueloSingleActivity.class));
        });
        Glide.with(context).load(vueloList.get(position).getImagen()).into(holder.imageViewViaje
        );
    }
    @Override
    public int getItemCount(){return vueloList.size(); }

    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e){
        if (e!=null)
            mErrorListener.onItemError(e);

        vueloList.clear();

        if (queryDocumentSnapshots!=null)
            queryDocumentSnapshots.getDocuments().stream().map(elem -> {
                return elem.toObject(Vuelo.class);
            }).forEach(elem -> vueloList.add(elem));


        notifyDataSetChanged();
        mDataChangedListener.onDataChanged();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textViewDestino,textViewOrigen,textViewPrecio,textViewEscala,textViewFechaSalida,textViewFechaLlegada;
        final ImageView imageViewViaje;
        final LinearLayout linearLayoutSingleVuelo;
        ViewHolder(View itemView) {
            super(itemView);
            imageViewViaje= itemView.findViewById(R.id.imageViewViaje);
            textViewDestino= itemView.findViewById(R.id.textViewDestino);
            textViewOrigen= itemView.findViewById(R.id.textViewOrigen);
            textViewPrecio= itemView.findViewById(R.id.textViewPrecio);
            textViewEscala= itemView.findViewById(R.id.textViewEscala);
            textViewFechaSalida= itemView.findViewById(R.id.textViewFechaSalida);
            textViewFechaLlegada= itemView.findViewById(R.id.textViewFechaLlegada);
            linearLayoutSingleVuelo=itemView.findViewById(R.id.linearLayoutSingleVuelo);
        }


    }
    public void setErrorListener(ItemErrorListener itemErrorListener){
        mErrorListener = itemErrorListener;
    }
    public interface ItemErrorListener {
        void onItemError(FirebaseFirestoreException error);
    }
    public void setDataChangedListener(DataChangedListener dataChangedListener){
        mDataChangedListener = dataChangedListener;
    }
    public interface DataChangedListener {
        void onDataChanged();
    }
}