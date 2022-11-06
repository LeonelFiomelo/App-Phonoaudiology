package com.example.app_phonoaudiology.infrastructure.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.app_phonoaudiology.R;
import com.example.app_phonoaudiology.databinding.FragmentResultadoDetalleBinding;
import com.example.app_phonoaudiology.infrastructure.ui.viewModel.ResultadoDetalleViewModel;

import java.util.Objects;

public class ResultadoDetalleFragment extends Fragment {

    private FragmentResultadoDetalleBinding binding;
    private ResultadoDetalleViewModel viewModel;

    private TextView txtRuido, txtIntensidad, txtPuntuacionO, txtFechaO, txtCategoriaO,
            txtSubcategoriaO, txtEjercicioO, txtPalabraClave, txtPalabraClaveO, txtRuidoO,
            txtIntensidadO;
    private TableLayout tabla;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ResultadoDetalleViewModel.class);
        viewModel.onCreate(requireActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadoDetalleBinding.inflate(inflater, container, false);

        txtRuido = binding.txtRuidoResultadoDetalle;
        txtIntensidad = binding.txtIntensidadResultadoDetalle;
        txtPuntuacionO = binding.txtPuntuacionOResultadoDetalle;
        txtFechaO = binding.txtFechaOResultadoDetalle;
        txtCategoriaO  = binding.txtCategoriaOResultadoDetalle;
        txtSubcategoriaO = binding.txtSubcategoriaOResultadoDetalle;
        txtEjercicioO = binding.txtEjercicioOResultadoDetalle;
        txtPalabraClave = binding.txtPalabraClaveResultadoDetalle;
        txtPalabraClaveO = binding.txtPalabraClaveOResultadoDetalle;
        txtRuidoO = binding.txtRuidoOResultadoDetalle;
        txtIntensidadO = binding.txtIntensidadOResultadoDetalle;
        tabla = binding.tableErroresResultadoDetalle;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        final String uuid = getArguments().getString("uuid");

        // OCULTAMOS EL RUIDO Y LA INTENSIDAD
        txtPalabraClave.setVisibility(View.GONE);
        txtPalabraClaveO.setVisibility(View.GONE);
        txtRuido.setVisibility(View.GONE);
        txtRuidoO.setVisibility(View.GONE);
        txtIntensidad.setVisibility(View.GONE);
        txtIntensidadO.setVisibility(View.GONE);

        viewModel.getResultado(uuid).observe(getViewLifecycleOwner(), resultado -> {

            // OBTENEMOS EL RESULTADO Y RELLENAMOS LOS TEXVIEW
            String p = resultado.getCorrectas() + "/" + resultado.getIntentos();
            txtPuntuacionO.setText(p);
            txtFechaO.setText(resultado.getFecha());
            txtCategoriaO.setText(resultado.getCategoria());
            txtSubcategoriaO.setText(resultado.getSubcategoria());
            txtEjercicioO.setText(resultado.getEjercicio());

            // VERIFICAMOS SI LA CATEGORÍA ES ORACIONES
            if (resultado.getCategoria().equals("Oraciones")) {
                txtPalabraClave.setVisibility(View.VISIBLE);
                txtPalabraClaveO.setVisibility(View.VISIBLE);
                txtPalabraClaveO.setText(resultado.getPalabraClave());
            }

            // VERIFICAMOS SI TIENE RUIDO Y MOSTRAMOS LOS DATOS
            if (resultado.getRuido()) {
                txtRuido.setVisibility(View.VISIBLE);
                txtRuidoO.setVisibility(View.VISIBLE);
                txtIntensidad.setVisibility(View.VISIBLE);
                txtIntensidadO.setVisibility(View.VISIBLE);
                txtRuidoO.setText(resultado.getTipoRuido());
                txtIntensidadO.setText(String.valueOf(resultado.getIntensidad()));
            }

        });

        viewModel.getErrores(uuid).observe(getViewLifecycleOwner(), errores -> {

            // OBTENEMOS LOS ERRORES Y RELLENAMOS LA TABLA
            for (int i=0; i<errores.size(); i++) {

                View row = LayoutInflater.from(getContext()).inflate(R.layout.item_error, null, false);
                TextView estimulo = row.findViewById(R.id.tv_estimuloOutput);
                TextView primeraRespuesta = row.findViewById(R.id.tv_primeraRespuestaOutput);
                TextView segundaRespuesta = row.findViewById(R.id.tv_segundaRespuestaOutput);

                estimulo.setText(errores.get(i).getEstimulo());

                if (viewModel.checkRespuesta(errores.get(i).getEstimulo(), errores.get(i).getPrimeraRespuesta())) {
                    String respuesta = errores.get(i).getPrimeraRespuesta() + " ✔";
                    primeraRespuesta.setText(respuesta);
                } else {
                    String respuesta = errores.get(i).getPrimeraRespuesta() + " ✘";
                    primeraRespuesta.setText(respuesta);
                }

                if (errores.get(i).getSegundaRespuesta() != null) {
                    if (viewModel.checkRespuesta(errores.get(i).getEstimulo(), errores.get(i).getSegundaRespuesta())) {
                        String respuesta = errores.get(i).getSegundaRespuesta() + " ✔";
                        segundaRespuesta.setText(respuesta);
                    } else {
                        String respuesta = errores.get(i).getSegundaRespuesta() + " ✘";
                        segundaRespuesta.setText(respuesta);
                    }
                } else {
                    segundaRespuesta.setText("-");
                }

                tabla.addView(row);
            }

        });

        binding.tbResultadoDetalle.setNavigationOnClickListener( v -> {
            navController.popBackStack();
        });

    }
}