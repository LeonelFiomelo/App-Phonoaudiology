package com.example.app_phonoaudiology;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_phonoaudiology.databinding.ActivityHomeBinding;

public class HomeActivity extends Fragment {

    public HomeActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        // Botones
        Button btnComenzarEntrenamiento = view.findViewById(R.id.btn_comenzarEntrenamiento);
        Button btnComenzarEvaluacion = view.findViewById(R.id.btn_comenzarEvaluacion);

        // Navegación a la Configuración - Boton ComenzarEntrenamiento
        btnComenzarEntrenamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.ConfiguracionFragment);
            }
        });

        // Navegación a la configuración - Boton ComenzarEvaluación
        btnComenzarEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.ConfiguracionFragment);
            }
        });
    }
}