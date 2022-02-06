package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.adapter.MonitorAdapter
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel.MonitorFragmentViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityMainBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentConsejo3Binding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentMonitorBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.viewmodel.LoginActivityViewModel


class MonitorFragment : Fragment() {

    private var _binding: FragmentMonitorBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val monitorAdapter by lazy {
        MonitorAdapter()
    }

    val monitorFragmentViewModel: MonitorFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonitorBinding.inflate(inflater, container, false)
        val view = binding.root
        monitorFragmentViewModel.getBabyInfo()
        binding.monitorRecyclerView.adapter = monitorAdapter
        binding.monitorRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        monitorFragmentViewModel.babyModel.observe(requireActivity(), {
            monitorAdapter.sendMonitorData( it )
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}