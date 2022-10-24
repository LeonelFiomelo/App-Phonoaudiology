package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.application.adapters.SpinnersAdapter;
import com.example.app_phonoaudiology.databinding.FragmentAgregarSonidoBinding;
import com.example.app_phonoaudiology.domain.entities.SonidoEntity;
import com.example.app_phonoaudiology.domain.repository.constants.Constantes;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.AgregarSonidoViewModel;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AgregarSonidoFragment extends Fragment {

    private FragmentAgregarSonidoBinding binding;
    private AgregarSonidoViewModel viewModel;
    private NavController navController;
    private SonidoEntity sonidoEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AgregarSonidoViewModel.class);
        viewModel.onCreate(requireActivity().getApplication());
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAgregarSonidoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("InflateParams")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        sonidoEntity = new SonidoEntity();
        binding.btnGrabarSonido.setEnabled(false);
        binding.btnGuardarSonido.setEnabled(false);

        // AGREGAMOS UN LISTENER AL CAMBIO DE TEXTO
        binding.tvNombreDelSonidoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sonidoEntity.setNombre(s.toString());
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.btnGrabarSonido.setEnabled(true);
                } else {
                    binding.btnGrabarSonido.setEnabled(false);
                }
            }
        });

        // CONFIGURAMOS EL SPINNER
        binding.sCategoriaSonido.setAdapter(SpinnersAdapter.getCategoriaSonidoAdapter(getContext()));
        binding.sCategoriaSonido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                sonidoEntity.setCategoria(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // AGREGAMOS EL LISTENER AL BOTON DE GRABAR
        binding.btnGrabarSonido.setOnClickListener(v -> {
            if (viewModel.CheckPermissions(requireActivity().getApplicationContext())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setView(R.layout.alertdialog_grabar_sonido);
                AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

                        ImageButton btn_grabar = alertDialog.findViewById(R.id.btn_grabarGrabacion);
                        ImageButton btn_play = alertDialog.findViewById(R.id.btn_reproducirGrabacion);
                        Button btn_cancelar = alertDialog.findViewById(R.id.btn_cancelarGrabacion);
                        Button btn_aceptar = alertDialog.findViewById(R.id.btn_aceptarGrabacion);

                        btn_grabar.setOnClickListener(v -> {
                            if (viewModel.modoGrabar()) {

                                viewModel.iniciarGrabacion(requireContext(), sonidoEntity);

                                btn_grabar.setImageResource(R.drawable.ic_mic_on);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_on_background);
                                Toast.makeText(requireContext(), "Grabando...", Toast.LENGTH_SHORT).show();

                            } else {

                                viewModel.finalizarGrabacion(requireContext(), sonidoEntity);

                                btn_grabar.setImageResource(R.drawable.ic_mic_off);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_off_background);
                                Toast.makeText(requireContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();

                            }
                        });

                        btn_play.setOnClickListener( v -> {

                            viewModel.escucharGrabacion();

                            btn_play.setBackgroundResource(R.drawable.ic_button_grabacion_on_background);
                            btn_play.setImageResource(R.drawable.ic_pause);

                        });

                        btn_cancelar.setOnClickListener( v -> {
                            viewModel.cancelarGrabacion(requireActivity(), sonidoEntity);
                            alertDialog.cancel();
                        });

                        btn_aceptar.setOnClickListener( v -> {
                            binding.btnGuardarSonido.setEnabled(true);
                            viewModel.aceptarGrabacion(sonidoEntity);
                            alertDialog.cancel();
                        });

                    }
                });
                alertDialog.show();
            } else {
                viewModel.RequestPermissions(requireActivity());
            }
        });

        // AGREGAMOS EL LISTENER AL BOTON DE GUARDAR
        binding.btnGuardarSonido.setOnClickListener( v -> {

            if (viewModel.validarDatos(sonidoEntity)) {
                viewModel.guardarSonido(sonidoEntity);
                binding.tvNombreDelSonidoInput.setText("");
                binding.btnGuardarSonido.setEnabled(false);
                sonidoEntity = new SonidoEntity();
                Toast.makeText(requireContext(), "Sonido guardado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
            }

        });

        // NAVEGACION DE LA BARRA
        binding.tbAgregarSonido.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File directory = requireContext().getFilesDir();
                File nuevaCarpeta = new File(directory, "sonido");
                File archivo = new File(nuevaCarpeta.getPath() + "/" + sonidoEntity.getNombre() + ".mp3");
                archivo.delete();
                navController.popBackStack();
            }
        });

    }

}