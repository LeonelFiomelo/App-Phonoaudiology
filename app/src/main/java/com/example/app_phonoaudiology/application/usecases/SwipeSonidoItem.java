package com.example.app_phonoaudiology.application.usecases;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phonoaudiology.application.adapters.SonidosAdapter;
import com.example.app_phonoaudiology.infrastructure.db.entity.SoundEntity;
import com.example.app_phonoaudiology.infrastructure.db.repository.SoundRepository;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SwipeSonidoItem {

    private ItemTouchHelper.SimpleCallback simpleCallback;
    private ItemTouchHelper itemTouchHelper;
    private Activity activity;
    private RecyclerView recyclerView;
    private SonidosAdapter sonidosAdapter;
    private List<SoundEntity> listaDeSonidos;
    private SoundRepository soundRepository;

    public SwipeSonidoItem(Activity activity, RecyclerView recyclerView, SonidosAdapter sonidosAdapter, List<SoundEntity> listaDeSonidos, SoundRepository soundRepository) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.sonidosAdapter = sonidosAdapter;
        this.listaDeSonidos = listaDeSonidos;
        this.soundRepository = soundRepository;
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

                builder.setMessage("¿Quieres eliminar este sonido?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                soundRepository.eliminarSonido(listaDeSonidos.get(viewHolder.getLayoutPosition()));
                                File archivo = new File(
                                        activity.getApplication().getFilesDir() + "/sonido/" + listaDeSonidos.get(viewHolder.getLayoutPosition()).getNombre_sonido() + ".mp3"
                                );
                                archivo.delete();
                                sonidosAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                                Snackbar.make(view, "Sonido eliminado", Snackbar.LENGTH_INDEFINITE).setDuration(2000).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sonidosAdapter.notifyItemChanged(viewHolder.getBindingAdapterPosition());
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
