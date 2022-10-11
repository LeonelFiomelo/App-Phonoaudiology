package com.example.app_phonoaudiology.application.controllers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;

import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.io.IOException;

public class SoundController implements MediaPlayer.OnCompletionListener {

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.reset();
    }

    public SoundController() {
    }

    // REPRODUCE UNA PALABRA
    public void iniciarSonido(Context context, SoundEntity sound) {
        AssetFileDescriptor afd = null;
        MediaPlayer mp = new MediaPlayer();
        try {
            afd = context.getAssets().openFd(sound.getRuta_sonido());
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
        try {
            Thread.sleep(mp.getDuration());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mp.release();
    }

    // REPRODUCE UNA PALABRA CON RUIDO DE FONDO
    public void iniciarSonidoConRuido(Context context, SoundEntity sound, String rutaRuido) {
        AssetFileDescriptor afdOpcion = null;
        AssetFileDescriptor afdRuido = null;
        MediaPlayer mpOpcion = new MediaPlayer();
        MediaPlayer mpRuido = new MediaPlayer();
        try{
            afdOpcion = context.getAssets().openFd(sound.getRuta_sonido());
            mpOpcion.setDataSource(afdOpcion.getFileDescriptor(), afdOpcion.getStartOffset(), afdOpcion.getLength());
            mpOpcion.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            afdRuido = context.getAssets().openFd(rutaRuido);
            mpRuido.setDataSource(afdRuido.getFileDescriptor(), afdRuido.getStartOffset(), afdRuido.getLength());
            mpRuido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpOpcion.start();
        mpRuido.start();
        try {
            Thread.sleep(mpOpcion.getDuration() + 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mpRuido.stop();
        mpOpcion.release();
        mpRuido.release();
    }

    // REPRODUCE UNA ORACION
    public void iniciarOraciones(Context context, SoundEntity sound, @Nullable SoundEntity conectorInicial, @Nullable SoundEntity conectorFinal) {
        AssetFileDescriptor afdSonido = null;
        AssetFileDescriptor afdConectorInicial = null;
        AssetFileDescriptor afdConectorFinal = null;
        MediaPlayer mpSonido = new MediaPlayer();
        MediaPlayer mpConectorInicial = new MediaPlayer();
        MediaPlayer mpConectorFinal = new MediaPlayer();

        // SONIDO
        try {
            afdSonido = context.getAssets().openFd(sound.getRuta_sonido());
            mpSonido.setDataSource(afdSonido.getFileDescriptor(), afdSonido.getStartOffset(), afdSonido.getLength());
            mpSonido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CONECTOR INICIAL
        if ((conectorInicial != null && conectorFinal == null) || (conectorInicial != null && conectorFinal != null)) {
            try {
                afdConectorInicial = context.getAssets().openFd(conectorInicial.getRuta_sonido());
                mpConectorInicial.setDataSource(afdConectorInicial.getFileDescriptor(), afdConectorInicial.getStartOffset(), afdConectorInicial.getLength());
                mpConectorInicial.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // CONECTOR FINAL
        if ((conectorInicial == null && conectorFinal != null) || (conectorInicial != null && conectorFinal != null)) {
            try {
                afdConectorFinal = context.getAssets().openFd(conectorFinal.getRuta_sonido());
                mpConectorFinal.setDataSource(afdConectorFinal.getFileDescriptor(), afdConectorFinal.getStartOffset(), afdConectorFinal.getLength());
                mpConectorFinal.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DIFERENTES FORMAS DE REPRODUCIR EL SONIDO Y LOS CONECTORES
        // FORMA UNO
        if (conectorInicial != null && conectorFinal != null) {
            mpConectorInicial.start();
            try {
                Thread.sleep(mpConectorInicial.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpConectorFinal.start();
            try {
                Thread.sleep(mpConectorFinal.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorInicial.stop();
            mpConectorFinal.stop();
            mpSonido.release();
            mpConectorInicial.release();
            mpConectorFinal.release();
        }
        // FORMA DOS
        if (conectorInicial != null && conectorFinal == null) {
            mpConectorInicial.start();
            try {
                Thread.sleep(mpConectorInicial.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorInicial.stop();
            mpSonido.release();
            mpConectorInicial.release();
        }
        // FORMA TRES
        if (conectorInicial == null && conectorFinal != null) {
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpConectorFinal.start();
            try {
                Thread.sleep(mpConectorFinal.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorFinal.stop();
            mpSonido.release();
            mpConectorFinal.release();
        }
    }

    // REPRODUCE UNA ORACION CON RUIDO DE FONDO
    public void iniciarOracionesConRuido(Context context, SoundEntity sound, @Nullable SoundEntity conectorInicial, @Nullable SoundEntity conectorFinal, String rutaDelRuido) {
        AssetFileDescriptor afdSonido = null;
        AssetFileDescriptor afdConectorInicial = null;
        AssetFileDescriptor afdConectorFinal = null;
        AssetFileDescriptor afdRuido = null;
        MediaPlayer mpSonido = new MediaPlayer();
        MediaPlayer mpConectorInicial = new MediaPlayer();
        MediaPlayer mpConectorFinal = new MediaPlayer();
        MediaPlayer mpRuido = new MediaPlayer();

        // SONIDO
        try {
            afdSonido = context.getAssets().openFd(sound.getRuta_sonido());
            mpSonido.setDataSource(afdSonido.getFileDescriptor(), afdSonido.getStartOffset(), afdSonido.getLength());
            mpSonido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CONECTOR INICIAL
        if ((conectorInicial != null && conectorFinal == null) || (conectorInicial != null && conectorFinal != null)) {
            try {
                afdConectorInicial = context.getAssets().openFd(conectorInicial.getRuta_sonido());
                mpConectorInicial.setDataSource(afdConectorInicial.getFileDescriptor(), afdConectorInicial.getStartOffset(), afdConectorInicial.getLength());
                mpConectorInicial.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // CONECTOR FINAL
        if ((conectorInicial == null && conectorFinal != null) || (conectorInicial != null && conectorFinal != null)) {
            try {
                afdConectorFinal = context.getAssets().openFd(conectorFinal.getRuta_sonido());
                mpConectorFinal.setDataSource(afdConectorFinal.getFileDescriptor(), afdConectorFinal.getStartOffset(), afdConectorFinal.getLength());
                mpConectorFinal.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // RUIDO
        try {
            afdRuido = context.getAssets().openFd(rutaDelRuido);
            mpRuido.setDataSource(afdRuido.getFileDescriptor(), afdRuido.getStartOffset(), afdRuido.getLength());
            mpRuido.prepare();
        } catch(IOException e) {
            e.printStackTrace();
        }

        // DIFERENTES FORMAS DE REPRODUCIR EL SONIDO Y LOS CONECTORES
        // FORMA UNO
        if (conectorInicial != null && conectorFinal != null) {
            mpRuido.start();
            mpConectorInicial.start();
            try {
                Thread.sleep(mpConectorInicial.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpConectorFinal.start();
            try {
                Thread.sleep(mpConectorFinal.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorInicial.stop();
            mpConectorFinal.stop();
            mpRuido.stop();
            mpSonido.release();
            mpConectorInicial.release();
            mpConectorFinal.release();
            mpRuido.release();
        }
        // FORMA DOS
        if (conectorInicial != null && conectorFinal == null) {
            mpRuido.start();
            mpConectorInicial.start();
            try {
                Thread.sleep(mpConectorInicial.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorInicial.stop();
            mpRuido.stop();
            mpSonido.release();
            mpConectorInicial.release();
            mpRuido.release();
        }
        // FORMA TRES
        if (conectorInicial == null && conectorFinal != null) {
            mpRuido.start();
            mpSonido.start();
            try {
                Thread.sleep(mpSonido.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpConectorFinal.start();
            try {
                Thread.sleep(mpConectorFinal.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mpSonido.stop();
            mpConectorFinal.stop();
            mpRuido.stop();
            mpSonido.release();
            mpConectorFinal.release();
            mpRuido.release();
        }
    }

}
