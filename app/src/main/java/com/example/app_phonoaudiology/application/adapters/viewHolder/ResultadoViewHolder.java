package com.example.app_phonoaudiology.application.adapters.viewHolder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;

public class ResultadoViewHolder extends RecyclerView.ViewHolder {

    private TextView fecha, categoria, subcategoria, ejercicio, ruido, puntuacion;

    public ResultadoViewHolder(@NonNull View itemView) {
        super(itemView);
        fecha = (TextView) itemView.findViewById(R.id.tv_fechaOutput_item);
        categoria = (TextView) itemView.findViewById(R.id.tv_categoriaOutput_item);
        subcategoria = (TextView) itemView.findViewById(R.id.tv_subcategoriaOutput_item);
        ejercicio = (TextView) itemView.findViewById(R.id.tv_ejercicioOutput_item);
        ruido = (TextView) itemView.findViewById(R.id.tv_ruidoOutput_item);
        puntuacion = (TextView) itemView.findViewById(R.id.tv_puntuacionOutput_item);
    }


    public void render(ResultadoEntityDB resultado) {
        String p = resultado.getCorrectas() + "/" + resultado.getIntentos();
        fecha.setText(resultado.getFecha());
        categoria.setText(resultado.getCategoria());
        subcategoria.setText(resultado.getSubcategoria());
        ejercicio.setText(resultado.getEjercicio());
        ruido.setText(resultado.getTipoRuido());
        puntuacion.setText(p);
    }


}
