package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.application.utils.GeneralUtils;
import com.example.app_phonoaudiology.infrastructure.db.entity.ErrorEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.repository.ErrorRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;

import java.util.List;

public class ResultadoViewModel extends ViewModel {

    private ResultadoRepository resultadoRepository;
    private ErrorRepository errorRepository;

    public ResultadoViewModel() {

    }

    public void onCreate(Application application) {
        resultadoRepository = new ResultadoRepository(application);
        errorRepository = new ErrorRepository(application);
    }

    public LiveData<ResultadoEntityDB> getResultado(String uuid) {
        return resultadoRepository.getResultado(uuid);
    }

    public LiveData<List<ErrorEntityDB>> getErrores(String uuid) {
        return errorRepository.getErrores(uuid);
    }

    public Boolean checkRespuesta(String opcionCorrecta, String respuesta) {
        return GeneralUtils.getCompararStrings(opcionCorrecta, respuesta) == 0;
    }

}
