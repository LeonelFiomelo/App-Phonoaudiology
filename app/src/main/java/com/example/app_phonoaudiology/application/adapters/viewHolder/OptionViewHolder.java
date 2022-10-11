package com.example.app_phonoaudiology.application.adapters.viewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.application.usecases.TouchOptionButton;
import com.google.android.material.button.MaterialButton;

public class OptionViewHolder extends RecyclerView.ViewHolder{

    private MaterialButton option;

    public OptionViewHolder(@NonNull View itemView) {
        super(itemView);
        option = (MaterialButton) itemView.findViewById(R.id.btn_option);
    }

    public MaterialButton getBtnOption() {
        return option;
    }

    public void render(String name) {
        option.setText(name);
    }

    public void setOnClickListener(MaterialButton opcionSeleccionada, SoundEntity opcionCorrecta, TouchOptionButton touchOptionButton) {
        option.setOnClickListener(button -> {
            touchOptionButton.corroborarSeleccion(opcionSeleccionada, opcionCorrecta);
        });
    }

}
