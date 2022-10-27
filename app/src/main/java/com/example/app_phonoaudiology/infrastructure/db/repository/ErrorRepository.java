package com.example.app_phonoaudiology.infrastructure.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.app_phonoaudiology.infrastructure.db.SoundDatabase;
import com.example.app_phonoaudiology.infrastructure.db.dao.ErrorDao;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;

import java.util.List;

public class ErrorRepository {

    private ErrorDao errorDao;

    public ErrorRepository(Application application) {
        SoundDatabase db = SoundDatabase.getInstanceSoundDatabase(application);
        this.errorDao = db.errorDao();
    }

    public LiveData<List<ErrorEntityDB>> getErrores(String uuid) {
        return errorDao.getErrores(uuid);
    }

    public void agregarErrores(List<ErrorEntityDB> listaDeErrores) {
        new agregarErroresAsynkTask(errorDao).execute(listaDeErrores);
    }

    public static class agregarErroresAsynkTask extends AsyncTask<List<ErrorEntityDB>, Void, Void> {
        private ErrorDao errorDao;
        private agregarErroresAsynkTask(ErrorDao errorDao) {
            this.errorDao = errorDao;
        }
        @Override
        protected Void doInBackground(List<ErrorEntityDB>... lists) {
            errorDao.insertErrores(lists[0]);
            return null;
        }
    }

}
