// Generated by view binder compiler. Do not edit!
package com.example.app_phonoaudiology.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class HeaderNavigationMenuBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgLogo;

  @NonNull
  public final ImageView imgLogoBackground;

  @NonNull
  public final TextView tvEscuchar;

  private HeaderNavigationMenuBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView imgLogo, @NonNull ImageView imgLogoBackground,
      @NonNull TextView tvEscuchar) {
    this.rootView = rootView;
    this.imgLogo = imgLogo;
    this.imgLogoBackground = imgLogoBackground;
    this.tvEscuchar = tvEscuchar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HeaderNavigationMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HeaderNavigationMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.header_navigation_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HeaderNavigationMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_logo;
      ImageView imgLogo = ViewBindings.findChildViewById(rootView, id);
      if (imgLogo == null) {
        break missingId;
      }

      id = R.id.img_logoBackground;
      ImageView imgLogoBackground = ViewBindings.findChildViewById(rootView, id);
      if (imgLogoBackground == null) {
        break missingId;
      }

      id = R.id.tv_escuchar;
      TextView tvEscuchar = ViewBindings.findChildViewById(rootView, id);
      if (tvEscuchar == null) {
        break missingId;
      }

      return new HeaderNavigationMenuBinding((ConstraintLayout) rootView, imgLogo,
          imgLogoBackground, tvEscuchar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
