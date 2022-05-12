package com.mis.acmebe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.mis.acmebe.adapter.VueloAdapter;
import com.mis.acmebe.adapter.VueloFavoritoAdapter;
import com.mis.acmebe.entity.Vuelo;

import java.util.ArrayList;

public class VuelosFavoritosActivity extends AppCompatActivity {
    private VueloFavoritoAdapter vueloFavoritoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos);

        vueloFavoritoAdapter = new VueloFavoritoAdapter();
        RecyclerView incoming_recycler_view = findViewById(R.id.recyclerViewVuelos);
        incoming_recycler_view.setLayoutManager((new LinearLayoutManager(getApplicationContext())));
        incoming_recycler_view.setAdapter(vueloFavoritoAdapter);


        vueloFavoritoAdapter.setDataChangedListener(() -> {
            if (vueloFavoritoAdapter.getItemCount()>0){
                incoming_recycler_view.setVisibility(View.VISIBLE);
            } else {
                incoming_recycler_view.setVisibility(View.GONE);
            }
        });
        vueloFavoritoAdapter.setErrorListener (error ->{
            incoming_recycler_view.setVisibility(View.GONE);
        });
        setupActionBar();
    }
    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    protected void onDestroy(){
        super.onDestroy();
        if (vueloFavoritoAdapter != null && vueloFavoritoAdapter.listenerRegistration != null)
            vueloFavoritoAdapter.listenerRegistration.remove();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void enlaceFiltro(View view) {
        setContentView(R.layout.activity_filtro);
    }
}