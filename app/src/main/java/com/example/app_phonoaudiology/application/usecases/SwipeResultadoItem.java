package com.example.app_phonoaudiology.application.usecases;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.ResultadosAdapter;
import com.example.app_phonoaudiology.infrastructure.db.entity.ResultadoEntityDB;
import com.example.app_phonoaudiology.infrastructure.db.repository.ResultadoRepository;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.List;

public class SwipeResultadoItem {

    private List<ResultadoEntityDB> listaDeResultados;
    private ItemTouchHelper.SimpleCallback simpleCallback;
    private ItemTouchHelper itemTouchHelper;
    private Activity activity;
    private RecyclerView recyclerView;
    private ResultadosAdapter resultadosAdapter;
    private ResultadoRepository resultadoRepository;

    public SwipeResultadoItem(Activity activity, RecyclerView recyclerView, ResultadosAdapter resultadosAdapter, List<ResultadoEntityDB> listaDeResultados, ResultadoRepository resultadoRepository) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.resultadosAdapter = resultadosAdapter;
        this.resultadoRepository = resultadoRepository;
        this.listaDeResultados = listaDeResultados;
    }

    public void setSwipe(View view) {
        this.simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setMessage("¿Quieres eliminar este resultado?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("ACEPTAR");
                                resultadoRepository.eliminarResultado(listaDeResultados.get(viewHolder.getAbsoluteAdapterPosition()));
                                resultadosAdapter.notifyItemRemoved(viewHolder.getAbsoluteAdapterPosition());
                                Snackbar.make(view, "Resultado eliminado", Snackbar.LENGTH_INDEFINITE).setDuration(2000).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("CANCELAR");
                                resultadosAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                            }
                        });
                builder.create();
                builder.show();
            }
        };
        this.itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
