package com.example.app_phonoaudiology.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.viewHolder.OptionViewHolder;
import com.example.app_phonoaudiology.application.adapters.viewHolder.ResultadoViewHolder;
import com.example.app_phonoaudiology.application.usecases.TouchResultadoButton;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadoViewHolder> {

    private List<ResultadoEntityDB> listaDeResultados;

    public ResultadosAdapter(List<ResultadoEntityDB> listaDeResultados) {
        this.listaDeResultados = listaDeResultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_resultado, parent, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int i) {
        holder.render(listaDeResultados.get(i));
    }

    @Override
    public int getItemCount() {
        return listaDeResultados.size();
    }
}
