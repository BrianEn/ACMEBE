package com.mis.acmebe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.mis.acmebe.adapter.VueloAdapter;
import com.mis.acmebe.entity.Vuelo;

public class FiltroActivity extends AppCompatActivity {
RecyclerView recyclerViewVuelosFiltrados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);


    }
    private void cargarFiltro(){
        recyclerViewVuelosFiltrados=findViewById(R.id.recyclerViewVuelosFiltrados);

        recyclerViewVuelosFiltrados.setLayoutManager(new GridLayoutManager(this,1));
    }

    public void filtro(View view) {
        cargarFiltro();
    }
}