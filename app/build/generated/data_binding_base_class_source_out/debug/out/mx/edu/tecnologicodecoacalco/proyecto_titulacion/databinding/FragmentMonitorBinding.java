// Generated by view binder compiler. Do not edit!
package mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R;

public final class FragmentMonitorBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView monitorRecyclerView;

  private FragmentMonitorBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView monitorRecyclerView) {
    this.rootView = rootView;
    this.monitorRecyclerView = monitorRecyclerView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMonitorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMonitorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_monitor, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMonitorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.monitor_recycler_view;
      RecyclerView monitorRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (monitorRecyclerView == null) {
        break missingId;
      }

      return new FragmentMonitorBinding((LinearLayout) rootView, monitorRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
