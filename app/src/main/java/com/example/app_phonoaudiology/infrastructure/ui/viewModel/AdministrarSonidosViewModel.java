package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.SonidosAdapter;
import com.example.app_phonoaudiology.application.usecases.SwipeSonidoItem;
import com.example.app_phonoaudiology.application.usecases.TouchSonidoItem;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.util.List;

public class AdministrarSonidosViewModel extends ViewModel {

    private SoundRepository soundRepository;
    private SwipeSonidoItem swipeSonidoItem;
    private SonidosAdapter sonidosAdapter;

    public AdministrarSonidosViewModel(){

    }

    public void onCreate(Application application) {
        soundRepository = new SoundRepository(application);
    }

    public void onStart(Context context, List<SoundEntity> listaDeSonidos) {
        sonidosAdapter = new SonidosAdapter(context, listaDeSonidos);
    }

    public LiveData<List<SoundEntity>> getSonidos() {
        return soundRepository.getAllSounds();
    }

    public SonidosAdapter getSonidosAdapter() {
        return sonidosAdapter;
    }

    public void setSwipe(Activity activity, View view, RecyclerView recyclerView, List<SoundEntity> listaDeSonidos) {
        swipeSonidoItem = new SwipeSonidoItem(activity, recyclerView, sonidosAdapter, listaDeSonidos, soundRepository);
        swipeSonidoItem.setSwipe(view);
    }

}
