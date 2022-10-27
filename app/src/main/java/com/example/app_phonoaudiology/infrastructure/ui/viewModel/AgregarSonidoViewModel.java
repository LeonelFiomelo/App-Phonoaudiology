package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.usecases.TouchGrabarButton;
import com.example.app_phonoaudiology.domain.entities.SonidoEntity;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.io.File;
import java.io.IOException;

public class AgregarSonidoViewModel extends ViewModel {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private SoundRepository soundRepository;
    private File ruta;
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    public AgregarSonidoViewModel() {

    }

    public void onCreate(Application application) {
        soundRepository = new SoundRepository(application);
    }

    public Boolean modoGrabar() {
        if (mediaRecorder == null) {
            return true;
        } else {
            return false;
        }
    }

    public void iniciarGrabacion(Context context, SonidoEntity sonidoEntity) {

        File directory = context.getFilesDir();
        File carpeta = new File(directory, "sonido");

        ruta = new File(carpeta.getPath(), sonidoEntity.getNombre() + ".mp3");

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(ruta.getPath());

        try {
            mediaRecorder.prepare();
        } catch(IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();

    }

    public void finalizarGrabacion(Context context, SonidoEntity sonidoEntity) {

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

    }

    public void finalizarGrabacionForzada(Context context, SonidoEntity sonidoEntity) {
        if (modoGrabar()) {
            finalizarGrabacion(context, sonidoEntity);
        }
    }

    public void escucharGrabacion() {

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(ruta.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer = null;

    }

    public void pausarReproduccion() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public Boolean checkReproduccion() {
        if (mediaPlayer != null) {
            return true;
        } else {
            return false;
        }
    }

    public void aceptarGrabacion(SonidoEntity sonidoEntity) {
        sonidoEntity.setRuta(ruta.getPath());
    }

    public void cancelarGrabacion(Activity activity, SonidoEntity sonidoEntity) {
        if (ruta != null) {
            ruta.delete();
        }
    }

    public void guardarSonido(SonidoEntity sonidoEntity) {
        SoundEntity soundEntity = new SoundEntity(
                sonidoEntity.getNombre(),
                sonidoEntity.getCategoria(),
                sonidoEntity.getRuta(),
                Constantes.NO_PERSONALIZADO,
                true
        );
        soundRepository.agregarSonido(soundEntity);
    }

    public Boolean validarDatos(SonidoEntity sonidoEntity) {
        if (sonidoEntity.getNombre() != null && sonidoEntity.getCategoria() != null && sonidoEntity.getRuta() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean CheckPermissions(Context context) {
        // Este metodo es usado para verificar los permisos
//        int result = ContextCompat.checkSelfPermission(context, "READ_EXTERNAL_STORAGE");
//        int result1 = ContextCompat.checkSelfPermission(context, "RECORD_AUDIO");
//        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
//        return result1 == PackageManager.PERMISSION_GRANTED;
        return true;
    }

    public void RequestPermissions(Activity activity) {
        // Este metodo es usado para pedir los permisos para grabación y almacenamiento de audio
//        ActivityCompat.requestPermissions(activity, new String[]{"RECORD_AUDIO", "WRITE_EXTERNAL_STORAGE"}, REQUEST_AUDIO_PERMISSION_CODE);
//        ActivityCompat.requestPermissions(activity, new String[]{"RECORD_AUDIO"}, REQUEST_AUDIO_PERMISSION_CODE);
        System.out.println("NO TIENE LOS PERMISOS");
    }

}
