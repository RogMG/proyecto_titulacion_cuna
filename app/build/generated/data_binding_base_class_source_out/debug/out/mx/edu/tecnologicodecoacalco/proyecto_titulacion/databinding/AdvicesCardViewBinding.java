// Generated by view binder compiler. Do not edit!
package mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R;

public final class AdvicesCardViewBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView articleImageView;

  @NonNull
  public final TextView descriptionTextView;

  @NonNull
  public final TextView titleTextView;

  private AdvicesCardViewBinding(@NonNull CardView rootView, @NonNull ImageView articleImageView,
      @NonNull TextView descriptionTextView, @NonNull TextView titleTextView) {
    this.rootView = rootView;
    this.articleImageView = articleImageView;
    this.descriptionTextView = descriptionTextView;
    this.titleTextView = titleTextView;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static AdvicesCardViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AdvicesCardViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.advices_card_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AdvicesCardViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.article_image_view;
      ImageView articleImageView = ViewBindings.findChildViewById(rootView, id);
      if (articleImageView == null) {
        break missingId;
      }

      id = R.id.description_text_view;
      TextView descriptionTextView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTextView == null) {
        break missingId;
      }

      id = R.id.title_text_view;
      TextView titleTextView = ViewBindings.findChildViewById(rootView, id);
      if (titleTextView == null) {
        break missingId;
      }

      return new AdvicesCardViewBinding((CardView) rootView, articleImageView, descriptionTextView,
          titleTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
