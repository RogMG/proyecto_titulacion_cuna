package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyMonitorDTO
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.adapter.MonitorAdapter
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.viewmodel.MonitorFragmentViewModel
import mx.edu.tecnologicodecoacalco.babberlink.databinding.FragmentMonitorBinding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

import io.reactivex.rxjava3.schedulers.Schedulers
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.domain.model.BabyDTO
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialog
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialogFragment


class MonitorFragment : Fragment() {

    val monitorFragmentViewModel: MonitorFragmentViewModel by viewModels()

    private var _binding: FragmentMonitorBinding? = null

    private val binding get() = _binding!!

    private lateinit var monitorAdapter: MonitorAdapter

    private var userEmail = ""

    private var babyData = mutableListOf<BabyDTO>()

    private val disposable by lazy {
        CompositeDisposable()
    }

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
        _binding = FragmentMonitorBinding.inflate(inflater, container, false)
        val view = binding.root
        Firebase.auth.currentUser?.email?.let { userEmail = it }
        setupMonitorInView()

        return view
    }

    fun setupMonitorInView(){
        monitorAdapter = MonitorAdapter()
        binding.monitorRecyclerView.adapter = monitorAdapter
        binding.monitorRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    fun checkBabyData(lpmList: MutableList<BabyMonitorDTO>){
        disposable.add(
            Observable.fromArray(lpmList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    it.printStackTrace()
                }
                .subscribe {
                    it.forEachIndexed { index, babyMonitorDTO ->
                        if(babyMonitorDTO.monitor.toInt() < 80 && babyData[index].estaEncendido.toBoolean() ){
                            val genericDialog = parentFragmentManager.findFragmentByTag(
                                GenericDialogFragment.TAG)
                            if (genericDialog == null) {
                                GenericDialogFragment
                                    .newInstance(
                                        "Alerta",
                                        "El bebe ${babyData[index].nombre} tiene bajo el ritmo cardiaco",
                                        "Aceptar",
                                        "",
                                        true
                                    )
                                    .show(
                                        parentFragmentManager, GenericDialogFragment.TAG)
                            }
                        }
                    }
                    it.clear()
                }
        )
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun onResume() {
        super.onResume()
        // monitorFragmentViewModel.getBabyInfoUpdate(userEmail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable.dispose()
    }



}