package com.mis.acmebe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.mis.acmebe.adapter.VueloAdapter;
import com.mis.acmebe.entity.Vuelo;

import java.util.ArrayList;

public class VuelosActivity extends AppCompatActivity {
    private VueloAdapter vueloAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos);

        vueloAdapter = new VueloAdapter();
        RecyclerView incoming_recycler_view = findViewById(R.id.recyclerViewVuelos);
        incoming_recycler_view.setLayoutManager((new LinearLayoutManager(getApplicationContext())));
        incoming_recycler_view.setAdapter(vueloAdapter);

        vueloAdapter.setDataChangedListener(() -> {
            if (vueloAdapter.getItemCount()>0){
                incoming_recycler_view.setVisibility(View.VISIBLE);
            } else {
                incoming_recycler_view.setVisibility(View.GONE);
            }
                });
        vueloAdapter.setErrorListener (error ->{
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
        if (vueloAdapter != null && vueloAdapter.listenerRegistration != null)
            vueloAdapter.listenerRegistration.remove();
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