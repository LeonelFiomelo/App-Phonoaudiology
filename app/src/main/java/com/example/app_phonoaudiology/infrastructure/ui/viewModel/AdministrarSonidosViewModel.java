package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Activity;
import android.app.Application;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.SonidosAdapter;
import com.example.app_phonoaudiology.application.usecases.SwipeSonidosItem;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

import java.util.List;

public class AdministrarSonidosViewModel extends ViewModel {

    private SoundRepository soundRepository;
    private SwipeSonidosItem swipeSonidosItem;

    public AdministrarSonidosViewModel(){

    }

    public void onCreate(Application application) {
        soundRepository = new SoundRepository(application);
    }

    public LiveData<List<SoundEntity>> getSonidos() {
        return soundRepository.getAllSounds();
    }

    public void setSwipe(Activity activity, RecyclerView recyclerView, SonidosAdapter sonidosAdapter, List<SoundEntity> lista, View view) {
        swipeSonidosItem = new SwipeSonidosItem(activity, recyclerView, sonidosAdapter, lista);
        swipeSonidosItem.setSwipe(view);
    }
}
