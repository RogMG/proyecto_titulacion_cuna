package mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.ActivityDashboard
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentConsejo3Binding

class Consejo3 : Fragment() {

    private var _binding: FragmentConsejo3Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConsejo3Binding.inflate(inflater, container, false)
        val view = binding.root

        binding.nextButtonAdviceThree.setOnClickListener {
            ActivityDashboard.launch(requireActivity())
            requireActivity().finish()

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}