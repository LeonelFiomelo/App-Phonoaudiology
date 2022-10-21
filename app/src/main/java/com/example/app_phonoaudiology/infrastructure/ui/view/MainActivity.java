package com.example.app_phonoaudiology.infrastructure.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.View;

import com.example.app_phonoaudiology.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    public boolean onNavigateUp() {
        return navController.navigateUp();
    }
}