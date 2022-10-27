package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.io.File;

public class HomeViewModel extends ViewModel {

    private SoundRepository soundRepository;

    public void onCreate(Application application) {
        // VARIABLES NECESARIAS EN EL ONCREATE
        soundRepository = new SoundRepository(application);
    }

    public void startDataBase(LifecycleOwner lifecycleOwner) {

        // ESTE METODO HACE UNa LLAMADA A LA BASE DE DATOS PARA QUE
        // INICIALICE ANTES QUE EL USUARIO NECESITE UTILIZARLA

        soundRepository.getAllSounds().observe(lifecycleOwner, sonidos -> {

        });

    }

    public void checkCarpetaSonido(Context context) {

        // ESTE METODO COMPUEBA SI LA CARPETA SONIDOS ESTA CREADA, DE NO ESTARLO, LA CREA

        Boolean carpetaSonidos = false;

        String[] carpetas = context.getFilesDir().getAbsoluteFile().list();

        for (int i=0; i<carpetas.length; i++) {
            if (carpetas[i].contains("sonidos")) {
                carpetaSonidos = true;
            } else {
                carpetaSonidos = false;
            }
        }

        if (!carpetaSonidos) {
            File directorio = context.getFilesDir();
            File carpeta = new File(directorio, "sonidos");
            carpeta.mkdirs();
        }

    }

}
