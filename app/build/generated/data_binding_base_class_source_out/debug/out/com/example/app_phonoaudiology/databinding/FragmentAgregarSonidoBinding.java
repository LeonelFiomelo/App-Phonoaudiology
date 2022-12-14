// Generated by view binder compiler. Do not edit!
package com.example.app_phonoaudiology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.app_phonoaudiology.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAgregarSonidoBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button btnGrabarSonidoAgregarSonido;

  @NonNull
  public final Button btnGuardarSonidoAgregarSonido;

  @NonNull
  public final EditText editTxtNombreSonidoAgregarSonido;

  @NonNull
  public final Spinner spinnerCategoriaSonidoAgregarSonido;

  @NonNull
  public final Toolbar toolbarAgregarSonido;

  @NonNull
  public final TextView txtCategoriaSonidoAgregarSonido;

  @NonNull
  public final TextView txtNombreSonidoAgregarSonido;

  private FragmentAgregarSonidoBinding(@NonNull FrameLayout rootView,
      @NonNull Button btnGrabarSonidoAgregarSonido, @NonNull Button btnGuardarSonidoAgregarSonido,
      @NonNull EditText editTxtNombreSonidoAgregarSonido,
      @NonNull Spinner spinnerCategoriaSonidoAgregarSonido, @NonNull Toolbar toolbarAgregarSonido,
      @NonNull TextView txtCategoriaSonidoAgregarSonido,
      @NonNull TextView txtNombreSonidoAgregarSonido) {
    this.rootView = rootView;
    this.btnGrabarSonidoAgregarSonido = btnGrabarSonidoAgregarSonido;
    this.btnGuardarSonidoAgregarSonido = btnGuardarSonidoAgregarSonido;
    this.editTxtNombreSonidoAgregarSonido = editTxtNombreSonidoAgregarSonido;
    this.spinnerCategoriaSonidoAgregarSonido = spinnerCategoriaSonidoAgregarSonido;
    this.toolbarAgregarSonido = toolbarAgregarSonido;
    this.txtCategoriaSonidoAgregarSonido = txtCategoriaSonidoAgregarSonido;
    this.txtNombreSonidoAgregarSonido = txtNombreSonidoAgregarSonido;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAgregarSonidoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAgregarSonidoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_agregar_sonido, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAgregarSonidoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_grabarSonido_agregarSonido;
      Button btnGrabarSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (btnGrabarSonidoAgregarSonido == null) {
        break missingId;
      }

      id = R.id.btn_guardarSonido_agregarSonido;
      Button btnGuardarSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (btnGuardarSonidoAgregarSonido == null) {
        break missingId;
      }

      id = R.id.editTxt_nombreSonido_agregarSonido;
      EditText editTxtNombreSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (editTxtNombreSonidoAgregarSonido == null) {
        break missingId;
      }

      id = R.id.spinner_categoriaSonido_agregarSonido;
      Spinner spinnerCategoriaSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (spinnerCategoriaSonidoAgregarSonido == null) {
        break missingId;
      }

      id = R.id.toolbar_agregarSonido;
      Toolbar toolbarAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAgregarSonido == null) {
        break missingId;
      }

      id = R.id.txt_categoriaSonido_agregarSonido;
      TextView txtCategoriaSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (txtCategoriaSonidoAgregarSonido == null) {
        break missingId;
      }

      id = R.id.txt_nombreSonido_agregarSonido;
      TextView txtNombreSonidoAgregarSonido = ViewBindings.findChildViewById(rootView, id);
      if (txtNombreSonidoAgregarSonido == null) {
        break missingId;
      }

      return new FragmentAgregarSonidoBinding((FrameLayout) rootView, btnGrabarSonidoAgregarSonido,
          btnGuardarSonidoAgregarSonido, editTxtNombreSonidoAgregarSonido,
          spinnerCategoriaSonidoAgregarSonido, toolbarAgregarSonido,
          txtCategoriaSonidoAgregarSonido, txtNombreSonidoAgregarSonido);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
