package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.app_phonoaudiology.application.adapters.ResultadosAdapter;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;

import java.util.List;

public class ResultadosHistoricosViewModel {

    private ResultadosAdapter resultadosAdapter;
    private ResultadoRepository resultadoRepository;

    public ResultadosHistoricosViewModel() {
    }

    public void onCreate(Application application) {
        resultadoRepository = new ResultadoRepository(application);
    }

    public LiveData<List<ResultadoEntityDB>> getResultados() {
        return resultadoRepository.getResultados();
    }

    public ResultadosAdapter getResultadosAdapter(List<ResultadoEntityDB> listaDeResultado) {
        resultadosAdapter = new ResultadosAdapter(listaDeResultado);
        return resultadosAdapter;
    }

}
