package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.ResultadosAdapter;
import com.example.app_phonoaudiology.application.adapters.SonidosAdapter;
import com.example.app_phonoaudiology.application.usecases.SwipeResultadoItem;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;

import java.util.List;

public class ResultadosHistoricosViewModel extends ViewModel {

    private ResultadosAdapter resultadosAdapter;
    private ResultadoRepository resultadoRepository;
    private SwipeResultadoItem swipeResultadoItem;

    public ResultadosHistoricosViewModel() {
    }

    public void onCreate(Application application) {
        resultadoRepository = new ResultadoRepository(application);
    }

    public void onStart(List<ResultadoEntityDB> listaDeResultados, NavController navController) {
        resultadosAdapter = new ResultadosAdapter(listaDeResultados, navController);
    }

    public LiveData<List<ResultadoEntityDB>> getResultado() {
        return resultadoRepository.getResultados();
    }

    public ResultadosAdapter getResultadosAdapter() {
        return resultadosAdapter;
    }

    public void setSwipe(Activity activity, View view, RecyclerView recyclerView, List<ResultadoEntityDB> listaDeResultados) {
        swipeResultadoItem = new SwipeResultadoItem(activity, recyclerView, resultadosAdapter, listaDeResultados, resultadoRepository);
        swipeResultadoItem.setSwipe(view);
    }

}
