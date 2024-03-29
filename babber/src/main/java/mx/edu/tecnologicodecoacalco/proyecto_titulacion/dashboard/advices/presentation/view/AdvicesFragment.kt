package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.adapter.AdvicesAdapter
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.viewmodel.AdvicesFragmentViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.FragmentAdvicesBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils.GenericDialog


class AdvicesFragment : Fragment() {

    private var _binding: FragmentAdvicesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var advicesAdapter: AdvicesAdapter

    val advicesFragmentViewModel: AdvicesFragmentViewModel by viewModels()

    private val dialog by lazy{
        GenericDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            dialog.dialogBuilderCloseSession(requireContext()) { requireActivity().finish() }.show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvicesBinding.inflate(inflater, container, false)
        val view = binding.root
        advicesFragmentViewModel.getAdvicesData()
        advicesAdapter = AdvicesAdapter(context = requireContext())
        binding.advicesRecyclerView.adapter = advicesAdapter
        binding.advicesRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        advicesFragmentViewModel.advicesData.observe(requireActivity(), {
            advicesAdapter.addItems(it)
        })
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}