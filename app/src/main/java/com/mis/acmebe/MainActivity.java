package com.mis.acmebe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mis.acmebe.entity.Enlace;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gridView=findViewById(R.id.gridView);
        gridView.setNumColumns(1);
        gridView.setAdapter(new EnlaceAdapter(Enlace.generaEnlace(),this));
    }
}

class EnlaceAdapter extends BaseAdapter{

    List<Enlace> enlaces;
    Context context;

    public EnlaceAdapter(List<Enlace> enlaces, Context context) {
        this.enlaces = enlaces;
        this.context = context;
    }

    @Override
    public int getCount() {
        return enlaces.size();
    }

    @Override
    public Object getItem(int i) {
        return enlaces.get(i);
    }

    @Override
    public long getItemId(int i) {
        return enlaces.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Enlace enlace=enlaces.get(i);
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.activity_item_menu,viewGroup,false);
        }
        CardView cardView=view.findViewById(R.id.cardView);
        TextView textView=view.findViewById(R.id.textViewMenu);
        ImageView imageView=view.findViewById(R.id.imageViewMenu);
        textView.setText(enlace.getDescripcion());
        imageView.setImageResource(enlace.getRecursoImageView());
        cardView.setOnClickListener(view1 -> context.startActivity(new Intent(context,enlace.getClase())));
        return view;

    }
}