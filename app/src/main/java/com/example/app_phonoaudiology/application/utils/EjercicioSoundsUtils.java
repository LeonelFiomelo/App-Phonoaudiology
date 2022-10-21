package com.example.app_phonoaudiology.application.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import com.example.app_phonoaudiology.R;

public class EjercicioSoundsUtils {

//    Al tocar un botón de respuesta sonará un ruido que variará de acuerdo a si la respuesta fue o no correcta
    public static void announceAnswerSound(Context context, boolean isCorrectAnswer) {
        Uri soundUri = null;
        MediaPlayer mediaPlayer = null;
        if (isCorrectAnswer) {
            mediaPlayer = MediaPlayer.create(context, R.raw.correct_answer);
        }
//            soundUri = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.correct_answer);
         else{
            mediaPlayer = MediaPlayer.create(context, R.raw.incorrect_answer);
        }
//            soundUri = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.incorrect_answer);
//        mediaPlayer = MediaPlayer.create(view.getContext(), soundUri);
        mediaPlayer.start();
    }
}
