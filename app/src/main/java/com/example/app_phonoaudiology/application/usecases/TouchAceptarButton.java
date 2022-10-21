package com.example.app_phonoaudiology.application.usecases;

import android.widget.EditText;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.google.android.material.button.MaterialButton;

public abstract class TouchAceptarButton {

    public void guardarInformacionDelIntento(EditText editText, SoundEntity opcionCorrecta) {
    }

    public void corroborarTexto(EditText editText) {
    }

}
