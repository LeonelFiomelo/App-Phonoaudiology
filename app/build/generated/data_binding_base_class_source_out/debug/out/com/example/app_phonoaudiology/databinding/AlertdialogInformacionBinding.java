// Generated by view binder compiler. Do not edit!
package com.example.app_phonoaudiology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.app_phonoaudiology.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AlertdialogInformacionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnAceptarInformacion;

  @NonNull
  public final TextView txtTextoInformacion;

  @NonNull
  public final TextView txtTituloInformacion;

  private AlertdialogInformacionBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnAceptarInformacion, @NonNull TextView txtTextoInformacion,
      @NonNull TextView txtTituloInformacion) {
    this.rootView = rootView;
    this.btnAceptarInformacion = btnAceptarInformacion;
    this.txtTextoInformacion = txtTextoInformacion;
    this.txtTituloInformacion = txtTituloInformacion;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AlertdialogInformacionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AlertdialogInformacionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.alertdialog_informacion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AlertdialogInformacionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_aceptarInformacion;
      Button btnAceptarInformacion = ViewBindings.findChildViewById(rootView, id);
      if (btnAceptarInformacion == null) {
        break missingId;
      }

      id = R.id.txt_textoInformacion;
      TextView txtTextoInformacion = ViewBindings.findChildViewById(rootView, id);
      if (txtTextoInformacion == null) {
        break missingId;
      }

      id = R.id.txt_tituloInformacion;
      TextView txtTituloInformacion = ViewBindings.findChildViewById(rootView, id);
      if (txtTituloInformacion == null) {
        break missingId;
      }

      return new AlertdialogInformacionBinding((ConstraintLayout) rootView, btnAceptarInformacion,
          txtTextoInformacion, txtTituloInformacion);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
