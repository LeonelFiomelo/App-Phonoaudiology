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
import java.util.List;

public class SwipeSonidoItem {

    private ItemTouchHelper.SimpleCallback simpleCallback;
    private ItemTouchHelper itemTouchHelper;
    private Activity activity;
    private RecyclerView recyclerView;
    private SonidosAdapter sonidosAdapter;
    private List<SoundEntity> lista;
    private SoundRepository soundRepository;

    public SwipeSonidoItem(Activity activity, RecyclerView recyclerView, SonidosAdapter sonidosAdapter, List<SoundEntity> lista) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.sonidosAdapter = sonidosAdapter;
        this.lista = lista;
        this.soundRepository = new SoundRepository(activity.getApplication());
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
                                soundRepository.eliminarSonido(lista.get(viewHolder.getBindingAdapterPosition()));
                                File archivo = new File(
                                        activity.getApplication().getFilesDir() + "/sonido/" + lista.get(viewHolder.getBindingAdapterPosition()).getNombre_sonido() + ".mp3"
                                );
                                archivo.delete();
                                sonidosAdapter.notifyItemRemoved(viewHolder.getAbsoluteAdapterPosition());
                                Snackbar.make(view, "Sonido eliminado", Snackbar.LENGTH_INDEFINITE).setDuration(2000).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("Cancelar");
                                sonidosAdapter.notifyItemRangeChanged(viewHolder.getAbsoluteAdapterPosition(),  sonidosAdapter.getItemCount());
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
