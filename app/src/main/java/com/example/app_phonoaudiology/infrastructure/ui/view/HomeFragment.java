package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.ActivityMainBinding;
import com.example.app_phonoaudiology.databinding.FragmentHomeBinding;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.HomeViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Button btnEntrenamiento, btnEvaluacion;
    private ImageButton btnInfoEntrenamiento, btnInfoEvaluacion;
    private ActionBarDrawerToggle toogle;

    private ResultadoRepository resultadoRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.onCreate(requireActivity().getApplication());
        viewModel.startDataBase((LifecycleOwner) getContext());
        viewModel.checkCarpetaSonido(requireContext());
        resultadoRepository = new ResultadoRepository(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        drawerLayout = binding.drawerLayoutHome;
        toolbar = binding.toolbarHome;
        navigationView = binding.navigationViewHome;
        btnEntrenamiento = binding.btnComenzarEntrenamiento;
        btnEvaluacion = binding.btnComenzarEvaluacion;
        btnInfoEntrenamiento = binding.btnInfoEntrenamiento;
        btnInfoEvaluacion = binding.btnInfoEvaluacion;


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        toogle = new ActionBarDrawerToggle(requireActivity(),drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // SE CONFIGURA LA SELECCION DE CADA ITEM DEL NAVIGATION VIEW
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.mi_consultarResultados):
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navController.navigate(R.id.ResultadosHistoricosFragment);
                        break;
                    case (R.id.mi_administrarSonidos):
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navController.navigate(R.id.AdministrarSonidosFragment);
                        break;
                }
                return true;
            }
        });

        btnEntrenamiento.setOnClickListener(new View.OnClickListener() {
            // SE LE AGREGA UN LISTENER AL HACER CLICK AL BOTON DE COMENZAR ENTRENAMIENTO
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("botonSeleccionado", "entrenamiento");
                navController.navigate(R.id.ConfiguracionFragment, bundle);
            }
        });

        btnEvaluacion.setOnClickListener(new View.OnClickListener() {
            // SE LE AGREGA UN LISTENER AL HACER CLICK AL BOTON DE COMENZAR EVALUACION
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("botonSeleccionado", "evaluacion");
                navController.navigate(R.id.ConfiguracionFragment, bundle);
            }
        });

        btnInfoEntrenamiento.setOnClickListener( v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(R.layout.alertdialog_informacion);
            AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener( alertDialogShow -> {
                TextView titulo = alertDialog.findViewById(R.id.txt_tituloInformacion);
                TextView texto = alertDialog.findViewById(R.id.txt_textoInformacion);
                Button aceptar = alertDialog.findViewById(R.id.btn_aceptarInformacion);

                titulo.setText("Ejercitación");
                texto.setText(R.string.consigna_ejercitacion);
                aceptar.setText("Aceptar");

                aceptar.setOnClickListener(v1 -> alertDialog.cancel());
            });
            alertDialog.show();
        });

        btnInfoEvaluacion.setOnClickListener( v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(R.layout.alertdialog_informacion);
            AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener( alertDialogShow -> {
                TextView titulo = alertDialog.findViewById(R.id.txt_tituloInformacion);
                TextView texto = alertDialog.findViewById(R.id.txt_textoInformacion);
                Button aceptar = alertDialog.findViewById(R.id.btn_aceptarInformacion);

                titulo.setText("Evaluación");
                texto.setText(R.string.consigna_evaluacion);
                aceptar.setText("Aceptar");

                aceptar.setOnClickListener(v1 -> alertDialog.cancel());
            });
            alertDialog.show();
        });

    }

}