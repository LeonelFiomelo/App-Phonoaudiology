// Generated by view binder compiler. Do not edit!
package com.example.app_phonoaudiology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.app_phonoaudiology.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemResultadoBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView tvCategoriaItem;

  @NonNull
  public final TextView tvCategoriaOutputItem;

  @NonNull
  public final TextView tvEjercicioItem;

  @NonNull
  public final TextView tvEjercicioOutputItem;

  @NonNull
  public final TextView tvFechaItem;

  @NonNull
  public final TextView tvFechaOutputItem;

  @NonNull
  public final TextView tvPuntuacionItem;

  @NonNull
  public final TextView tvPuntuacionOutputItem;

  @NonNull
  public final TextView tvRuidoItem;

  @NonNull
  public final TextView tvRuidoOutputItem;

  @NonNull
  public final TextView tvSubcategoriaItem;

  @NonNull
  public final TextView tvSubcategoriaOutputItem;

  private ItemResultadoBinding(@NonNull CardView rootView, @NonNull TextView tvCategoriaItem,
      @NonNull TextView tvCategoriaOutputItem, @NonNull TextView tvEjercicioItem,
      @NonNull TextView tvEjercicioOutputItem, @NonNull TextView tvFechaItem,
      @NonNull TextView tvFechaOutputItem, @NonNull TextView tvPuntuacionItem,
      @NonNull TextView tvPuntuacionOutputItem, @NonNull TextView tvRuidoItem,
      @NonNull TextView tvRuidoOutputItem, @NonNull TextView tvSubcategoriaItem,
      @NonNull TextView tvSubcategoriaOutputItem) {
    this.rootView = rootView;
    this.tvCategoriaItem = tvCategoriaItem;
    this.tvCategoriaOutputItem = tvCategoriaOutputItem;
    this.tvEjercicioItem = tvEjercicioItem;
    this.tvEjercicioOutputItem = tvEjercicioOutputItem;
    this.tvFechaItem = tvFechaItem;
    this.tvFechaOutputItem = tvFechaOutputItem;
    this.tvPuntuacionItem = tvPuntuacionItem;
    this.tvPuntuacionOutputItem = tvPuntuacionOutputItem;
    this.tvRuidoItem = tvRuidoItem;
    this.tvRuidoOutputItem = tvRuidoOutputItem;
    this.tvSubcategoriaItem = tvSubcategoriaItem;
    this.tvSubcategoriaOutputItem = tvSubcategoriaOutputItem;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemResultadoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemResultadoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_resultado, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemResultadoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tv_categoria_item;
      TextView tvCategoriaItem = ViewBindings.findChildViewById(rootView, id);
      if (tvCategoriaItem == null) {
        break missingId;
      }

      id = R.id.tv_categoriaOutput_item;
      TextView tvCategoriaOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvCategoriaOutputItem == null) {
        break missingId;
      }

      id = R.id.tv_ejercicio_item;
      TextView tvEjercicioItem = ViewBindings.findChildViewById(rootView, id);
      if (tvEjercicioItem == null) {
        break missingId;
      }

      id = R.id.tv_ejercicioOutput_item;
      TextView tvEjercicioOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvEjercicioOutputItem == null) {
        break missingId;
      }

      id = R.id.tv_fecha_item;
      TextView tvFechaItem = ViewBindings.findChildViewById(rootView, id);
      if (tvFechaItem == null) {
        break missingId;
      }

      id = R.id.tv_fechaOutput_item;
      TextView tvFechaOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvFechaOutputItem == null) {
        break missingId;
      }

      id = R.id.tv_puntuacion_item;
      TextView tvPuntuacionItem = ViewBindings.findChildViewById(rootView, id);
      if (tvPuntuacionItem == null) {
        break missingId;
      }

      id = R.id.tv_puntuacionOutput_item;
      TextView tvPuntuacionOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvPuntuacionOutputItem == null) {
        break missingId;
      }

      id = R.id.tv_ruido_item;
      TextView tvRuidoItem = ViewBindings.findChildViewById(rootView, id);
      if (tvRuidoItem == null) {
        break missingId;
      }

      id = R.id.tv_ruidoOutput_item;
      TextView tvRuidoOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvRuidoOutputItem == null) {
        break missingId;
      }

      id = R.id.tv_subcategoria_item;
      TextView tvSubcategoriaItem = ViewBindings.findChildViewById(rootView, id);
      if (tvSubcategoriaItem == null) {
        break missingId;
      }

      id = R.id.tv_subcategoriaOutput_item;
      TextView tvSubcategoriaOutputItem = ViewBindings.findChildViewById(rootView, id);
      if (tvSubcategoriaOutputItem == null) {
        break missingId;
      }

      return new ItemResultadoBinding((CardView) rootView, tvCategoriaItem, tvCategoriaOutputItem,
          tvEjercicioItem, tvEjercicioOutputItem, tvFechaItem, tvFechaOutputItem, tvPuntuacionItem,
          tvPuntuacionOutputItem, tvRuidoItem, tvRuidoOutputItem, tvSubcategoriaItem,
          tvSubcategoriaOutputItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
