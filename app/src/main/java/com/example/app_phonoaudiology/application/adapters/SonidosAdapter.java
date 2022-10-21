package com.example.app_phonoaudiology.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.viewHolder.SonidosViewHolder;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.List;

public class SonidosAdapter extends RecyclerView.Adapter<SonidosViewHolder> {

    private List<SoundEntity> listaDeSonidos;

    public SonidosAdapter(List<SoundEntity> listaDeSonidos) {
        this.listaDeSonidos = listaDeSonidos;
    }

    @NonNull
    @Override
    public SonidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_nombre_sonido, parent, false);
        return new SonidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SonidosViewHolder holder, int position) {
        holder.render(listaDeSonidos.get(position).getNombre_sonido());
    }

    @Override
    public int getItemCount() {
        return listaDeSonidos.size();
    }
}
