package com.example.app_phonoaudiology.application.usecases;

import android.content.Context;

import com.example.app_phonoaudiology.application.controllers.SoundController;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

public class TouchSonidoItem {

    private SoundController soundController;

    public TouchSonidoItem() {
        soundController = new SoundController();
    }

    public void reproducirSonido(Context context, SoundEntity sonido) {
        soundController.iniciarSonido(context, sonido);
    }

}
