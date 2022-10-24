package com.example.app_phonoaudiology.application.adapters.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.usecases.TouchSonidoItem;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

public class SonidosViewHolder extends RecyclerView.ViewHolder{

    private TextView textView;
    private ConstraintLayout constraintLayout;
    private TouchSonidoItem touchSonidoItem;

    public SonidosViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_nombreSonido);
        constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cl_sonidoItem);
    }

    public void render(String nombre) {
        textView.setText(nombre);
    }

    public void setTouchSonidoItemListener(Context context, SoundEntity sonido) {
        constraintLayout.setOnClickListener( v -> {
            touchSonidoItem = new TouchSonidoItem();
            touchSonidoItem.reproducirSonido(context, sonido);
        });
        textView.setOnClickListener( v -> {
            touchSonidoItem = new TouchSonidoItem();
            touchSonidoItem.reproducirSonido(context, sonido);
        });
    }

}
