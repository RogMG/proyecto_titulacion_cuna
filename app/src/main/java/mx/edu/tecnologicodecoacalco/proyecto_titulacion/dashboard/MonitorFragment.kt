package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentConsejo3Binding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentMonitorBinding


class MonitorFragment : Fragment() {

    private var _binding: FragmentMonitorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonitorBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}