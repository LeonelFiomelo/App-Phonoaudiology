package com.example.app_phonoaudiology.application.usecases;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.app_phonoaudiology.application.controllers.SoundController;
import com.example.app_phonoaudiology.application.utils.EjercicioAlertsUtils;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;

public class TouchSettingsButton {

    public TouchSettingsButton() {

    }

    public void showSettings(@NonNull View view, ConfiguracionEntity configuracionEntity) {
        EjercicioAlertsUtils.displayNoiseSettingsAlert(view, configuracionEntity);
    }


}
