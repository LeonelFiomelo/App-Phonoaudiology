package com.example.app_phonoaudiology.infrastructure.ui.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;

public class HomeViewModel extends ViewModel {

    private SoundRepository soundRepository;

    public SoundRepository getSoundRepository(Application application) {
        soundRepository = new SoundRepository(application);
        return  soundRepository;
    }

}
