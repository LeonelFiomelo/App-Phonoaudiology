package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.SonidosAdapter;
import com.example.app_phonoaudiology.databinding.FragmentAdministrarSonidosBinding;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.AdministrarSonidosViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdministrarSonidosFragment extends Fragment {

    private FragmentAdministrarSonidosBinding binding;
    private AdministrarSonidosViewModel viewModel;

    private Toolbar toolbar;
    private RecyclerView recyclerViewSonidos;
    private FloatingActionButton actionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AdministrarSonidosViewModel.class);
        viewModel.onCreate(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdministrarSonidosBinding.inflate(inflater, container, false);

        toolbar = binding.toolbarAdministrarSonidos;
        recyclerViewSonidos = binding.recyclerViewAdministrarSonidos;
        actionButton = binding.actionButtonAgregarSonido;

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE COLOCA UN LISTENER AL BOTON DE LA TOOLBAR PARA VOLVER AL FRAGMENT HOME
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.HomeFragment);
            }
        });

        viewModel.getSonidos().observe(getViewLifecycleOwner(), sounds -> {
            // SE OBTIENEN TODOS LOS SONIDOS DE LA BASE DE DATOS
            viewModel.onStart(getContext(), sounds);
            viewModel.setSwipe(getActivity(), getView(), recyclerViewSonidos, sounds);
            recyclerViewSonidos.setAdapter(viewModel.getSonidosAdapter());
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            // SE LE COLOCA UN LISTENER AL ACTION BUTTON PARA NAVEGAR AL SIGUIENTE FRAGMENT
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.AgregarSonidoFragment);
            }
        });

    }
}