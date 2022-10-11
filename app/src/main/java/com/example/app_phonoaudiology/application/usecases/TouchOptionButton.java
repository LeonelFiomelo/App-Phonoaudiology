package com.example.app_phonoaudiology.application.usecases;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.google.android.material.button.MaterialButton;

public interface TouchOptionButton {

    void corroborarSeleccion(MaterialButton opcionSeleccionada, SoundEntity opcionCorrecta);

}
