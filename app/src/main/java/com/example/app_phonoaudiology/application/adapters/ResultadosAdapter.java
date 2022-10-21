package com.example.app_phonoaudiology.application.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.viewHolder.OptionViewHolder;
import com.example.app_phonoaudiology.application.usecases.TouchResultadoButton;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<OptionViewHolder> {

    public ResultadosAdapter(List<ResultadoEntityDB> listaDeResultados, TouchResultadoButton touchResultadoButton) {

    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate();
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
