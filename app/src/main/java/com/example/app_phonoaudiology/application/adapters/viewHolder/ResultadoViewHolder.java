package com.example.app_phonoaudiology.application.adapters.viewHolder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;

public class ResultadoViewHolder extends RecyclerView.ViewHolder {

    private TextView fecha, categoria, subcategoria, ejercicio, ruido, ruidoOutput, puntuacion;
    private ConstraintLayout constraintLayout;

    public ResultadoViewHolder(@NonNull View itemView) {
        super(itemView);
        fecha = (TextView) itemView.findViewById(R.id.tv_fechaOutput_item);
        categoria = (TextView) itemView.findViewById(R.id.tv_categoriaOutput_item);
        subcategoria = (TextView) itemView.findViewById(R.id.tv_subcategoriaOutput_item);
        ejercicio = (TextView) itemView.findViewById(R.id.tv_ejercicioOutput_item);
        ruido = (TextView) itemView.findViewById(R.id.tv_ruido_item);
        ruidoOutput = (TextView) itemView.findViewById(R.id.tv_ruidoOutput_item);
        puntuacion = (TextView) itemView.findViewById(R.id.tv_puntuacionOutput_item);
        constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cl_resultadoItem);
    }


    public void render(ResultadoEntityDB resultado) {
        String p = resultado.getCorrectas() + "/" + resultado.getIntentos();
        puntuacion.setText(p);
        fecha.setText(resultado.getFecha());
        categoria.setText(resultado.getCategoria());
        subcategoria.setText(resultado.getSubcategoria());
        ejercicio.setText(resultado.getEjercicio());
        if (resultado.getTipoRuido() != null) {
            ruidoOutput.setText(resultado.getTipoRuido());
        } else {
            ruido.setVisibility(View.GONE);
            ruidoOutput.setVisibility(View.GONE);
        }
    }


    public void setListener(NavController navController, ResultadoEntityDB resultado) {
        constraintLayout.setOnClickListener( v -> {
            Bundle bundle = new Bundle();
            bundle.putString("uuid", resultado.getUuid());
            navController.navigate(R.id.ResultadoDetalleFragment, bundle);
        });
    }


}
