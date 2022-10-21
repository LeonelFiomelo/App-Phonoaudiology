package com.example.app_phonoaudiology.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.application.usecases.TouchOptionButton;
import com.example.app_phonoaudiology.application.adapters.viewHolder.OptionViewHolder;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionViewHolder> {

    private List<SoundEntity> listaDeOpciones;
    private SoundEntity opcionCorrecta;
    private TouchOptionButton touchOptionButton;

    // CONSTRUCTOR
    public OptionsAdapter(List<SoundEntity> listaDeOpciones, @Nullable SoundEntity opcionCorrecta, @Nullable TouchOptionButton touchOptionButton) {
        this.listaDeOpciones = listaDeOpciones;
        this.opcionCorrecta = opcionCorrecta;
        this.touchOptionButton = touchOptionButton;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_option, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        holder.render(listaDeOpciones.get(position).getNombre_sonido());
        holder.setOnClickListener(holder.getBtnOption(), opcionCorrecta, touchOptionButton);
    }

    @Override
    public int getItemCount() {
        return listaDeOpciones.size();
    }

}
