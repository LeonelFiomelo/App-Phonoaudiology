package com.example.app_phonoaudiology.application.usecases;

import android.content.Context;

import com.example.app_phonoaudiology.application.controllers.SoundController;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

public class TouchPlayButton {

    private final SoundController soundController;

    public TouchPlayButton() {
        soundController = new SoundController();
    }

    public void reproducirSonido(Context context, SoundEntity opcionCorrecta) {
        soundController.iniciarSonido(context, opcionCorrecta);
    }

    public void reproducirSonidoConRuido(Context context, SoundEntity opcionCorrecta, String rutaDelRuido, float intensidad) {
        soundController.iniciarSonidoConRuido(context, opcionCorrecta, rutaDelRuido, intensidad);
    }

    public void reproducirOraciones(Context context, SoundEntity opcionCorrecta, SoundEntity conectorInicial, SoundEntity conectorFinal) {
        soundController.iniciarOraciones(context, opcionCorrecta, conectorInicial, conectorFinal);
    }

    public void reproducirOracionesConRuido(Context context, SoundEntity opcionCorrecta, SoundEntity conectorInicial, SoundEntity conectorFinal, String rutaDelRuido, float intensidad) {
        soundController.iniciarOracionesConRuido(context, opcionCorrecta, conectorInicial, conectorFinal, rutaDelRuido, intensidad);
    }

}
