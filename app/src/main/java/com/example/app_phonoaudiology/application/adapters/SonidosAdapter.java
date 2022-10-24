package com.example.app_phonoaudiology.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.viewHolder.SonidosViewHolder;
import com.example.app_phonoaudiology.application.usecases.TouchSonidoItem;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.util.List;

public class SonidosAdapter extends RecyclerView.Adapter<SonidosViewHolder> {

    private List<SoundEntity> listaDeSonidos;
    private Context context;

    public SonidosAdapter(Context context, List<SoundEntity> listaDeSonidos) {
        this.listaDeSonidos = listaDeSonidos;
        this.context = context;
    }

    @NonNull
    @Override
    public SonidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_nombre_sonido, parent, false);
        return new SonidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SonidosViewHolder holder, int i) {
        holder.render(listaDeSonidos.get(i).getNombre_sonido());
        holder.setTouchSonidoItemListener(context, listaDeSonidos.get(i));
    }

    @Override
    public int getItemCount() {
        return listaDeSonidos.size();
    }
}
