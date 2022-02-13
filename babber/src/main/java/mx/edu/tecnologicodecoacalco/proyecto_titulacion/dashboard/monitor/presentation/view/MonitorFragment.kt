package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.adapter.MonitorAdapter
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.viewmodel.MonitorFragmentViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityMainBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentConsejo3Binding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentMonitorBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.viewmodel.LoginActivityViewModel


class MonitorFragment : Fragment() {

    private var _binding: FragmentMonitorBinding? = null

    private val binding get() = _binding!!

    private lateinit var monitorAdapter: MonitorAdapter

    val monitorFragmentViewModel: MonitorFragmentViewModel by viewModels()

    private lateinit var userEmail: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonitorBinding.inflate(inflater, container, false)
        val view = binding.root
        userEmail = "rogelio@example.com"
        setupMonitorInView()
        // Firebase.auth.currentUser?.email?.let { monitorFragmentViewModel.getBabyInfo(it) }
        monitorFragmentViewModel.getBabyInfo(userEmail)

        monitorFragmentViewModel.babyData.observe(requireActivity(), {
            monitorAdapter.sendBabyData(it)
            monitorFragmentViewModel.getLpmBaby(userEmail)
        })

        monitorFragmentViewModel.babyMonitorData.observe(requireActivity(), {
            monitorAdapter.setId(it.id)
            monitorFragmentViewModel.computeBabyData(it.model, userEmail)
        })

        monitorFragmentViewModel.babyMonitor.observe(requireActivity(), {
            monitorAdapter.sendLpmData(it)
        })

        monitorFragmentViewModel.babyUpdatedData.observe(requireActivity(), {
            monitorAdapter.replaceBabyData(it)
        })

        return view
    }

    fun setupMonitorInView(){
        monitorAdapter = MonitorAdapter(context = requireActivity())
        binding.monitorRecyclerView.adapter = monitorAdapter
        binding.monitorRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

    }

    override fun onResume() {
        super.onResume()
        monitorFragmentViewModel.getUpdateBabyInfo(userEmail)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}