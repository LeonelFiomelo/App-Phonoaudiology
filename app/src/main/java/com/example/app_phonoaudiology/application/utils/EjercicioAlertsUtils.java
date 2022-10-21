package com.example.app_phonoaudiology.application.utils;

import android.content.DialogInterface;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AlertDialog;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.controllers.SoundController;
import com.example.app_phonoaudiology.domain.entities.ConfiguracionEntity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class EjercicioAlertsUtils {

    public static void displayAlertMessage(View root, String titulo, String cuerpo) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(root.getContext())
                .setTitle(titulo)
                .setMessage(cuerpo)
                .setPositiveButton(R.string.btn_continuar, null);
        builder.show();
    }

    public static void displayNoiseSettingsAlert(View view, ConfiguracionEntity configuracionEntity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(R.layout.alertdialog_settings);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                float oldIntensity = configuracionEntity.getIntensidad();

                SeekBar sbNoise = alertDialog.findViewById(R.id.sb_ruido);

                sbNoise.setProgress((int) ((configuracionEntity.getIntensidad()*10)));

                sbNoise.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        configuracionEntity.setIntensidad(seekBar.getProgress() / 10f);
                    }
                });

                // BOTON DE CANCELAR
                alertDialog.findViewById(R.id.btn_cancelar).setOnClickListener(v -> {
                    configuracionEntity.setIntensidad(oldIntensity);
                    alertDialog.dismiss();
                });

                // BOTON DE GUARDAR
                alertDialog.findViewById(R.id.btn_guardar).setOnClickListener(
                        v -> alertDialog.dismiss()
                );

                // APRETAR FUERA DE LA ALERTA
                alertDialog.setOnCancelListener(dialog1 -> {
                    configuracionEntity.setIntensidad(oldIntensity);
                });
            }
        });
        alertDialog.show();
    }

    public static void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setDuration(1000).show();
    }

    public static void showSnackbar(View view, String text, int duration) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setDuration(duration).show();
    }


    public static int getRandomCorrectAnswerText() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.correct1);
        arrayList.add(R.string.correct2);
        arrayList.add(R.string.correct3);
        arrayList.add(R.string.correct4);
        arrayList.add(R.string.correct5);
        arrayList.add(R.string.correct6);
        arrayList.add(R.string.correct7);
        arrayList.add(R.string.correct8);
        Random rand = new Random();
        int index = rand.nextInt(arrayList.size());
        return arrayList.get(index);
    }

    public static int getRandomIncorrectAnswerText() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.incorrect1);
        arrayList.add(R.string.incorrect2);
        arrayList.add(R.string.incorrect3);
        Random rand = new Random();
        int index = rand.nextInt(arrayList.size());
        return arrayList.get(index);
    }
}
