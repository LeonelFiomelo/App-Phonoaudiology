package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import android.widget.FrameLayout;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.ActivityMainBinding;
import com.example.app_phonoaudiology.databinding.FragmentHomeBinding;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.HomeViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private Bundle bundle;
    private SoundRepository soundRepository;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toogle;

    private Boolean carpetaSonido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        bundle = new Bundle();
        viewModel.getSoundRepository(requireActivity().getApplication()).getAllSounds().observe((LifecycleOwner) requireContext(), sounds -> {

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        toogle = new ActionBarDrawerToggle(
                requireActivity(),
                binding.drawerLayout,
                binding.toolbar,
                R.string.open,
                R.string.close
        );
        binding.drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        binding.nvHome.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.mi_consultarResultados):
                        System.out.println("Consultar resultados");
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        navController.navigate(R.id.ResultadosHistoricosFragment);
                        break;
                    case (R.id.mi_administrarSonidos):
                        System.out.println("Administrar sonidos");
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        navController.navigate(R.id.AdministrarSonidosFragment);
                        break;
                }
                return true;
            }
        });

        // NAVEGAMOS A LA CONFIGURACION - BOTON COMENZAR ENTRENAMIENTO
        binding.btnComenzarEntrenamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("botonSeleccionado", "entrenamiento");
                navController.navigate(R.id.ConfiguracionFragment, bundle);
            }
        });

        // NAVEGAMOS A LA CONFIGURACION - BOTON COMENZAR EVALUACIÓN
        binding.btnComenzarEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("botonSeleccionado", "evaluacion");
                navController.navigate(R.id.ConfiguracionFragment, bundle);
            }
        });

        String[] carpetas = requireContext().getFilesDir().getAbsoluteFile().list();

        for (int i=0; i<carpetas.length; i++) {
            if (carpetas[i].contains("sonido")) {
                carpetaSonido = true;
            } else {
                carpetaSonido = false;
            }
        }

        if (!carpetaSonido) {
            File directory = requireContext().getFilesDir();
            File nuevaCarpeta = new File(directory, "sonido");
            nuevaCarpeta.mkdirs();
        } else {
            System.out.println("CARPETA DE SONIDO YA CREADA");
        }

//        System.out.println(requireContext().getFilesDir());

//        System.out.println("PATH: " + rutas.contains("sonido"));
        System.out.println("ABSOLUTE PATH: " + requireContext().getFilesDir().getAbsoluteFile());

//        File directory = requireContext().getFilesDir();
//        File file = new File(directory, "sonido");
//        System.out.println("DIRECTORIO: " + directory);
//        System.out.println("DIRECTORIO DOS: " + file);

    }

}