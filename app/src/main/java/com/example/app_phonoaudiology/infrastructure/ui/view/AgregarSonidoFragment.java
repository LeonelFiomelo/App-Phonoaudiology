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
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
    private SonidoEntity sonidoEntity;
    private Boolean actualizar;

    private Toolbar toolbar;
    private EditText editTxtNombre;
    private Spinner spinnerCategoria;
    private Button btnGrabar, btnGuardar;

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

        toolbar = binding.toolbarAgregarSonido;
        editTxtNombre = binding.editTxtNombreSonidoAgregarSonido;
        spinnerCategoria = binding.spinnerCategoriaSonidoAgregarSonido;
        btnGrabar = binding.btnGrabarSonidoAgregarSonido;
        btnGuardar = binding.btnGuardarSonidoAgregarSonido;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        sonidoEntity = new SonidoEntity();
        btnGrabar.setEnabled(false);
        btnGuardar.setEnabled(false);
        actualizar = false;

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            // SE LE AGREGA UN LISTENER AL BOTON DE LA TOOLBAR PARA VOLVER AL FRAGMENT ANTERIOR
            @Override
            public void onClick(View v) {
                File directory = requireContext().getFilesDir();
                File nuevaCarpeta = new File(directory, "sonido");
                File archivo = new File(nuevaCarpeta.getPath() + "/" + sonidoEntity.getNombre() + ".mp3");
                archivo.delete();
                navController.navigate(R.id.AdministrarSonidosFragment);
            }
        });

        editTxtNombre.addTextChangedListener(new TextWatcher() {
            // SE LE AGREGA UN LISTENER AL INGRESO DE TEXTO EN EL EDIT TEXT
            // GUARDA EL TEXTO INGRESADO
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
                    btnGrabar.setEnabled(true);
                } else {
                    btnGrabar.setEnabled(false);
                }
            }
        });

        spinnerCategoria.setAdapter(SpinnersAdapter.getCategoriaSonidoAdapter(getContext()));
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // SE LE AGREGA UN LISTENER A LA SELECCION DE LOS ITEMS DEL SPINNER
            // GUARDA LA SELECCION
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                sonidoEntity.setCategoria(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGrabar.setOnClickListener(v -> {
            // SE LE AGREGA UN LISTENER AL BOTON DE GRABAR
            // SE VA A PODER GRABAR AUDIO SI SE TIENEN LOS PERMISOS CORRESPONDIENTES
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

                        btn_aceptar.setEnabled(false);
                        btn_play.setEnabled(false);

                        btn_grabar.setOnClickListener(v -> {
                            if (viewModel.modoGrabar()) {

                                viewModel.iniciarGrabacion(requireContext(), sonidoEntity);

                                btn_grabar.setImageResource(R.drawable.ic_mic_on);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_on_background);
                                Toast.makeText(requireContext(), "Grabando...", Toast.LENGTH_SHORT).show();

                            } else {

                                viewModel.finalizarGrabacion(requireContext(), sonidoEntity);

                                btn_play.setEnabled(true);
                                btn_aceptar.setEnabled(true);
                                btn_grabar.setImageResource(R.drawable.ic_mic_off);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_off_background);
                                Toast.makeText(requireContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();

                            }
                        });

                        btn_play.setOnClickListener( v -> {

                            if (!viewModel.modoGrabar()) {
                                viewModel.finalizarGrabacion(requireContext(), sonidoEntity);
                                btn_grabar.setImageResource(R.drawable.ic_mic_off);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_off_background);
                                Toast.makeText(requireContext(), "Grabación interrumpida", Toast.LENGTH_SHORT).show();
                            } else {
                                if (viewModel.checkReproduccion()) {
                                    viewModel.pausarReproduccion();
                                    Toast.makeText(requireContext(), "Reproduccion pausada", Toast.LENGTH_SHORT).show();
                                } else {
                                    viewModel.escucharGrabacion();
                                    Toast.makeText(requireContext(), "Reproduciendo sonido...", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

                        btn_cancelar.setOnClickListener( v -> {

                            if (!viewModel.modoGrabar()) {
                                viewModel.finalizarGrabacion(requireContext(), sonidoEntity);
                                btn_grabar.setImageResource(R.drawable.ic_mic_off);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_off_background);
                                Toast.makeText(requireContext(), "Grabación interrumpida", Toast.LENGTH_SHORT).show();
                            }

                            viewModel.cancelarGrabacion(requireActivity(), sonidoEntity);

                            alertDialog.cancel();

                        });

                        btn_aceptar.setOnClickListener( v -> {

                            if (!viewModel.modoGrabar()) {
                                viewModel.finalizarGrabacion(requireContext(), sonidoEntity);
                                btn_grabar.setImageResource(R.drawable.ic_mic_off);
                                btn_grabar.setBackgroundResource(R.drawable.ic_button_grabacion_off_background);
                                Toast.makeText(requireContext(), "Grabación interrumpida", Toast.LENGTH_SHORT).show();
                            } else {
                                btnGuardar.setEnabled(true);
                                viewModel.aceptarGrabacion(sonidoEntity);
                                alertDialog.cancel();
                            }

                        });

                    }
                });
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (!viewModel.modoGrabar()) {
                            viewModel.finalizarGrabacion(requireContext(), sonidoEntity);
                            Toast.makeText(requireContext(), "Grabación interrumpida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
            } else {
                viewModel.RequestPermissions(requireActivity());
            }
        });

        btnGuardar.setOnClickListener( v -> {
            // SE LE AGREGA UN LISTENER AL BOTON DE GUARDAR
            // GUARDA EL SONIDO GRABADO EN LA BASE DE DATOS
            if (viewModel.validarDatos(sonidoEntity)) {
                viewModel.guardarSonido(sonidoEntity);
                editTxtNombre.setText("");
                spinnerCategoria.setSelection(0);
                btnGuardar.setEnabled(false);
                sonidoEntity = new SonidoEntity();
                Toast.makeText(requireContext(), "Sonido guardado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
            }

        });

    }

}