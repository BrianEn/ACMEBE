package com.mis.acmebe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.mis.acmebe.FirestoreService;
import com.mis.acmebe.R;
import com.mis.acmebe.entity.Opinion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpinionAdapter extends RecyclerView.Adapter<com.mis.acmebe.adapter.OpinionAdapter.ViewHolder> implements EventListener<QuerySnapshot> {
        private final List<Opinion> opinionList;
        private com.mis.acmebe.adapter.OpinionAdapter.DataChangedListener mDataChangedListener;
        private com.mis.acmebe.adapter.OpinionAdapter.ItemErrorListener mErrorListener;
        public final ListenerRegistration listenerRegistration;

        public OpinionAdapter(){
            this.opinionList=new ArrayList<>();
            String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
            listenerRegistration = FirestoreService.getServiceInstance().getOpinions((com.google.firebase.firestore.EventListener<QuerySnapshot>) this);
        }

        @NonNull
        @Override

        public com.mis.acmebe.adapter.OpinionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_opinion_single, parent,false);
            return new com.mis.acmebe.adapter.OpinionAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull com.mis.acmebe.adapter.OpinionAdapter.ViewHolder holder, int position){
            holder.textViewUsuario.setText(opinionList.get(position).getUsuario());
            holder.textViewOpinion.setText(opinionList.get(position).getComentario());
        }
        @Override
        public int getItemCount(){return opinionList.size(); }

        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e){
            if (e!=null)
                mErrorListener.onItemError(e);

            opinionList.clear();
            if (queryDocumentSnapshots!=null)
                opinionList.addAll(queryDocumentSnapshots.toObjects(Opinion.class));
            notifyDataSetChanged();
            mDataChangedListener.onDataChanged();
        }
        static class ViewHolder extends RecyclerView.ViewHolder{
            final TextView textViewUsuario,textViewOpinion;
            final LinearLayout linearLayoutSingleOpinion;
            ViewHolder(View itemView) {
                super(itemView);
                textViewUsuario= itemView.findViewById(R.id.textViewUsuario);
                textViewOpinion= itemView.findViewById(R.id.textViewOpinion);
                linearLayoutSingleOpinion=itemView.findViewById(R.id.linearLayoutSingleOpinion);
            }
        }
        public void setErrorListener(com.mis.acmebe.adapter.OpinionAdapter.ItemErrorListener itemErrorListener){
            mErrorListener = itemErrorListener;
        }
        public interface ItemErrorListener {
            void onItemError(FirebaseFirestoreException error);
        }
        public void setDataChangedListener(com.mis.acmebe.adapter.OpinionAdapter.DataChangedListener dataChangedListener){
            mDataChangedListener = dataChangedListener;
        }
        public interface DataChangedListener {
            void onDataChanged();
        }



}
