package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.hardwareconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentConsejo3Binding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentHardwareConectBinding

class HardwareConectionFragment : Fragment() {

    private var _binding: FragmentHardwareConectBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardwareConectBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}