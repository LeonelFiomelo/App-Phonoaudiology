package com.example.app_phonoaudiology.application.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;

public class SonidosViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;

    public SonidosViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_nombreSonido);
    }

    public void render(String nombre) {
        textView.setText(nombre);
    }


}
