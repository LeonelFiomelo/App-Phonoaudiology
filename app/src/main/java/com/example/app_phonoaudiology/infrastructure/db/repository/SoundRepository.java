package com.example.app_phonoaudiology.infrastructure.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.app_phonoaudiology.infrastructure.db.SoundDatabase;
import com.example.app_phonoaudiology.infrastructure.db.dao.SoundDao;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;

import java.io.IOException;
import java.util.List;

public class SoundRepository {

    private SoundDao soundDao;
    private LiveData<List<SoundEntity>> allSounds, diasSounds, numerosSounds, mesesSounds,
            coloresSounds, ruidosSounds, oracionesSounds;
    private LiveData<SoundEntity> necesitoSound, vasosGrandesSound, esHoySound, hoyEsSound,
            yLloviendoSound;

    public SoundRepository(Application application) {
        SoundDatabase db = SoundDatabase.getInstanceSoundDatabase(application);
        soundDao = db.soundDao();
        allSounds = soundDao.getSoundList();
        diasSounds = soundDao.getListOfDias();
        numerosSounds = soundDao.getListOfNumeros();
        mesesSounds = soundDao.getListOfMeses();
        coloresSounds = soundDao.getListOfColores();
        ruidosSounds = soundDao.getListOfRuido();
        oracionesSounds = soundDao.getListOfOraciones();
        necesitoSound = soundDao.getNecesitoConnector("necesito");
        vasosGrandesSound = soundDao.getNecesitoConnector("vasos grandes");
        esHoySound = soundDao.getNecesitoConnector("es hoy");
        hoyEsSound = soundDao.getNecesitoConnector("hoy es");
        yLloviendoSound = soundDao.getNecesitoConnector("y esta lloviendo");
    }

    public LiveData<SoundEntity> getNecesitoSound() {
        return necesitoSound;
    }
    public LiveData<SoundEntity> getVasosGrandes() {
        return vasosGrandesSound;
    }
    public LiveData<SoundEntity> getEsHoySound() {
        return esHoySound;
    }
    public LiveData<SoundEntity> getHoyEsSound() {
        return hoyEsSound;
    }
    public LiveData<SoundEntity> getyLLoviendoSound() {
        return yLloviendoSound;
    }
    public LiveData<List<SoundEntity>> getAllSounds() { return allSounds; }
    public LiveData<List<SoundEntity>> getDiasSounds() { return diasSounds; }
    public LiveData<List<SoundEntity>> getMesesSounds() {
        return mesesSounds;
    }
    public LiveData<List<SoundEntity>> getNumerosSounds() {
        return numerosSounds;
    }
    public LiveData<List<SoundEntity>> getColoresSounds() {
        return coloresSounds;
    }
    public LiveData<List<SoundEntity>> getOracionesSounds() {
        return oracionesSounds;
    }
    public LiveData<List<SoundEntity>> getRuidosSounds() {
        return ruidosSounds;
    }
    public LiveData<List<SoundEntity>> getRutaSonido(String nombreSonido) {
        return soundDao.getRutaSonido(nombreSonido);
    }
    public void borrarTodo() {
        soundDao.borrarTodos();
    }

    public void agregarSonido(SoundEntity sound) {
        new agregarSonidoAsynkTask(soundDao).execute(sound);
    }

    public void eliminarSonido(SoundEntity sound) {
        new eliminarSonidoAsynkTask(soundDao).execute(sound);
    }

    // CLASE QUE AGREGA UN SONIDO EN SEGUNDO PLANO
    private static class agregarSonidoAsynkTask extends AsyncTask<SoundEntity, Void, Void> {
        private SoundDao soundDao;
        private agregarSonidoAsynkTask(SoundDao soundDao) {
            this.soundDao = soundDao;
        }
        @Override
        protected Void doInBackground(SoundEntity... soundEntities) {
            soundDao.agregarSonido(soundEntities[0]);
            return null;
        }
    }

    // CLASE QUE ELIMINA UN SONIDO EN SEGUNDO PLANO
    private static class eliminarSonidoAsynkTask extends AsyncTask<SoundEntity, Void, Void> {
        private SoundDao soundDao;
        private eliminarSonidoAsynkTask(SoundDao soundDao) {
            this.soundDao = soundDao;
        }
        @Override
        protected Void doInBackground(SoundEntity... soundEntities) {
            try {
                soundDao.eliminarSonido(soundEntities[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
