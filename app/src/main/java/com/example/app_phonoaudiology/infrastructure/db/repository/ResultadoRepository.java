package com.example.app_phonoaudiology.infrastructure.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import com.example.app_phonoaudiology.infrastructure.db.SoundDatabase;
import com.example.app_phonoaudiology.infrastructure.db.dao.ResultadoDao;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoErroresEntityDB;

import java.util.List;

public class ResultadoRepository {

    private ResultadoDao resultadoDao;
    private LiveData<List<ResultadoEntityDB>> resultados;
    private LiveData<List<ErrorEntityDB>> errores;
    private LiveData<List<ResultadoErroresEntityDB>> resultadoConErrores;
    private ResultadoEntityDB resultado;

    public ResultadoRepository(Application application) {
        SoundDatabase db = SoundDatabase.getInstanceSoundDatabase(application);
        resultadoDao = db.resultadoDao();
        resultados = db.resultadoDao().getAllResultados();
    }

    public void eliminarResultado(ResultadoEntityDB resultado) {
        new eliminarResultadoAsynkTask(resultadoDao).execute(resultado);
    }

    public LiveData<List<ResultadoEntityDB>> getResultados() {
        return resultados;
    }

    public LiveData<ResultadoEntityDB> getResultado(String uuid) {
        return resultadoDao.getResultado(uuid);
    }

    public void agregarResultado(ResultadoEntityDB resultado) {
        new agregarResultadoAsynkTask(resultadoDao).execute(resultado);
    }

    public void agregarError(ErrorEntityDB error) {
        new agregarErrorAsynkTask(resultadoDao).execute(error);
    }

    public LiveData<List<ResultadoErroresEntityDB>> getResultadoErrores() {
        return  resultadoDao.getAllResultadosConErrores();
    }

    public LiveData<List<ErrorEntityDB>> getErrores() {
        return resultadoDao.getAllErrores();
    }

    public static class agregarResultadoAsynkTask extends AsyncTask<ResultadoEntityDB, Void, Void> {
        private ResultadoDao resultadoDao;
        private agregarResultadoAsynkTask(ResultadoDao resultadoDao) {
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(ResultadoEntityDB... resultadoDbEntities) {
            resultadoDao.insert(resultadoDbEntities[0]);
            return null;
        }
    }

    public static class eliminarResultadoAsynkTask extends AsyncTask<ResultadoEntityDB, Void, Void> {
        private ResultadoDao resultadoDao;
        private eliminarResultadoAsynkTask(ResultadoDao resultadoDao) {
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(ResultadoEntityDB... resultadoDbEntities) {
            resultadoDao.eliminarResultado(resultadoDbEntities[0]);
            return null;
        }
    }

    public static class agregarErrorAsynkTask extends AsyncTask<ErrorEntityDB, Void, Void> {
        private ResultadoDao resultadoDao;
        private agregarErrorAsynkTask(ResultadoDao resultadoDao) {
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(ErrorEntityDB... errorEntityDBS) {
            resultadoDao.insert(errorEntityDBS[0]);
            return null;
        }
    }

}
