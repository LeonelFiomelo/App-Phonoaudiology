// Generated by view binder compiler. Do not edit!
package com.example.app_phonoaudiology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.app_phonoaudiology.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEjercicioOpcionesBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageButton btnPlay;

  @NonNull
  public final ImageButton btnSetting;

  @NonNull
  public final ImageView imgCorrectas;

  @NonNull
  public final ImageView imgIncorrectas;

  @NonNull
  public final RecyclerView recyclerViewEjercicio;

  @NonNull
  public final Toolbar tbEjercicioOpciones;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView tvCorrectas;

  @NonNull
  public final TextView tvIncorrectas;

  private FragmentEjercicioOpcionesBinding(@NonNull FrameLayout rootView,
      @NonNull ImageButton btnPlay, @NonNull ImageButton btnSetting,
      @NonNull ImageView imgCorrectas, @NonNull ImageView imgIncorrectas,
      @NonNull RecyclerView recyclerViewEjercicio, @NonNull Toolbar tbEjercicioOpciones,
      @NonNull TextView textView, @NonNull TextView tvCorrectas, @NonNull TextView tvIncorrectas) {
    this.rootView = rootView;
    this.btnPlay = btnPlay;
    this.btnSetting = btnSetting;
    this.imgCorrectas = imgCorrectas;
    this.imgIncorrectas = imgIncorrectas;
    this.recyclerViewEjercicio = recyclerViewEjercicio;
    this.tbEjercicioOpciones = tbEjercicioOpciones;
    this.textView = textView;
    this.tvCorrectas = tvCorrectas;
    this.tvIncorrectas = tvIncorrectas;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEjercicioOpcionesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEjercicioOpcionesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_ejercicio_opciones, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEjercicioOpcionesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_play;
      ImageButton btnPlay = ViewBindings.findChildViewById(rootView, id);
      if (btnPlay == null) {
        break missingId;
      }

      id = R.id.btn_setting;
      ImageButton btnSetting = ViewBindings.findChildViewById(rootView, id);
      if (btnSetting == null) {
        break missingId;
      }

      id = R.id.img_correctas;
      ImageView imgCorrectas = ViewBindings.findChildViewById(rootView, id);
      if (imgCorrectas == null) {
        break missingId;
      }

      id = R.id.img_incorrectas;
      ImageView imgIncorrectas = ViewBindings.findChildViewById(rootView, id);
      if (imgIncorrectas == null) {
        break missingId;
      }

      id = R.id.recyclerView_ejercicio;
      RecyclerView recyclerViewEjercicio = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewEjercicio == null) {
        break missingId;
      }

      id = R.id.tb_ejercicioOpciones;
      Toolbar tbEjercicioOpciones = ViewBindings.findChildViewById(rootView, id);
      if (tbEjercicioOpciones == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.tv_correctas;
      TextView tvCorrectas = ViewBindings.findChildViewById(rootView, id);
      if (tvCorrectas == null) {
        break missingId;
      }

      id = R.id.tv_incorrectas;
      TextView tvIncorrectas = ViewBindings.findChildViewById(rootView, id);
      if (tvIncorrectas == null) {
        break missingId;
      }

      return new FragmentEjercicioOpcionesBinding((FrameLayout) rootView, btnPlay, btnSetting,
          imgCorrectas, imgIncorrectas, recyclerViewEjercicio, tbEjercicioOpciones, textView,
          tvCorrectas, tvIncorrectas);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
