package mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.view

import android.net.Uri
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
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.adapter.MonitorAdapter
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.viewmodel.MonitorFragmentViewModel
import mx.edu.tecnologicodecoacalco.babberlink.databinding.FragmentMonitorBinding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

import io.reactivex.rxjava3.schedulers.Schedulers
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.domain.model.UserModelDTO
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialog
import mx.edu.tecnologicodecoacalco.babberlink.utils.GenericDialogFragment


class MonitorFragment : Fragment() {

    val monitorFragmentViewModel: MonitorFragmentViewModel by viewModels()

    private var _binding: FragmentMonitorBinding? = null

    private val binding get() = _binding!!

    private lateinit var monitorAdapter: MonitorAdapter

    private var userEmail = ""

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

        monitorFragmentViewModel.getTokensData(userEmail)

        monitorFragmentViewModel.getTokensResponse.observe(requireActivity(), {
            for(token in it.dispositivosVinculados){
                monitorFragmentViewModel.getUserData(token)
            }
        })
        monitorFragmentViewModel.userImageResponse.observe(requireActivity(), {
            monitorAdapter.receiveimages(it)
        })
        monitorFragmentViewModel.userDataResponse.observe(requireActivity(), {
            val userModelDTO = UserModelDTO(
                nombre = it.first().nombre,
                apellidoMaterno = it.first().apellidoMaterno,
                apellidoPaterno = it.first().apellidoPaterno,
                celular = it.first().celular,
                correo = it.first().correo,
                userToken = it.first().userToken
            )
            monitorAdapter.recieveUserData(userModelDTO)
            monitorFragmentViewModel.getUserImage(it.first().imageId)
        })

        return view
    }

    fun setupMonitorInView(){
        monitorAdapter = MonitorAdapter(context = requireActivity())
        binding.monitorRecyclerView.adapter = monitorAdapter
        binding.monitorRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }


    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable.dispose()
    }



}
