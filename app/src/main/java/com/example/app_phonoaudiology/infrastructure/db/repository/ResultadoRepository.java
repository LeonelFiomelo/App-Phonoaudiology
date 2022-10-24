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

    public ResultadoRepository(Application application) {
        SoundDatabase db = SoundDatabase.getInstanceSoundDatabase(application);
        resultadoDao = db.resultadoDao();
        resultados = db.resultadoDao().getAllResultados();
    }

    @WorkerThread
    public void insert(ResultadoEntityDB resultado) {
        resultadoDao.insert(resultado);
    }

    @WorkerThread
    public void insert(ErrorEntityDB error) {
        resultadoDao.insert(error);
    }

    @WorkerThread
    public void update(ResultadoEntityDB resultado) {
        resultadoDao.update(resultado);
    }

    @WorkerThread
    public void update(ErrorEntityDB error) {
        resultadoDao.update(error);
    }

    @WorkerThread
    public void deleteAllResultados() {
        resultadoDao.deleteAllResultados();
    }

    public LiveData<List<ResultadoEntityDB>> getResultados() {
        return resultados;
    }

    public void agregarResultado(ResultadoEntityDB resultado) {
        new agregarResultadoAsynkTask(resultadoDao).execute(resultado);
    }

    public void agregarError(ErrorEntityDB error) {
        new agregarErrorAsynkTask(resultadoDao).execute(error);
    }

//    public void eliminarResultado(ResultadoEntityDB resultado) {
//        new eliminarResultadoAsynkTask(resultadoDao).execute(resultado);
//    }

    public static class agregarResultadoAsynkTask extends AsyncTask<ResultadoEntityDB, Void, Void> {
        private ResultadoDao resultadoDao;
        private agregarResultadoAsynkTask(ResultadoDao resultadoDao) {
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(ResultadoEntityDB... resultadoDbEntities) {
            resultadoDao.insert(resultadoDbEntities[0]);
            System.out.println("ID DEL RESULTADO EN LA BASE DE DATOS: " + resultadoDbEntities[0].getId());
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

//    public static class eliminarResultadoAsynkTask extends AsyncTask<ResultadoEntityDB, Void, Void>{
//        private ResultadoDao resultadoDao;
//        private eliminarResultadoAsynkTask(ResultadoDao resultadoDao) {
//            this.resultadoDao = resultadoDao;
//        }
//        @Override
//        protected Void doInBackground(ResultadoEntityDB... resultadoDbEntities) {
//            resultadoDao.d(resultadoDbEntities[0]);
//            return null;
//        }
//    }

}
