package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.List;

public class AdministrarSonidosFragment extends Fragment {

    private FragmentAdministrarSonidosBinding binding;
    private AdministrarSonidosViewModel viewModel;
    private NavController navController;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private ItemTouchHelper itemTouchHelper;

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        binding.tbAdministrarSonidos.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.popBackStack();
            }
        });

        binding.fabAgregarSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.AgregarSonidoFragment);
            }
        });

        viewModel.getSonidos().observe((LifecycleOwner) requireContext(), sounds -> {
            List<SoundEntity> lista = sounds;
            SonidosAdapter sonidosAdapter = new SonidosAdapter(lista);
            for (int i=0; i<lista.size(); i++) {
                System.out.println(lista.get(i).getNombre_sonido());
            }
            viewModel.setSwipe(requireActivity(), binding.rvAdministrarSonidos, sonidosAdapter, lista, getView());
            binding.rvAdministrarSonidos.setAdapter(sonidosAdapter);
        });

    }
}