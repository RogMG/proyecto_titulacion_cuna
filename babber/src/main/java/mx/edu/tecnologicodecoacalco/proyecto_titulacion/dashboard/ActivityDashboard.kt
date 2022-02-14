package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.presentation.view.AdvicesFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.AppConectionFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.presentation.view.BabyRegisterFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view.MonitorFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.presentation.view.SettingsFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityDashboardBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils.FirebaseBackgroundService




class ActivityDashboard : AppCompatActivity() {

    companion object {
        fun launch(context: Activity) {
            val intent = Intent(context, ActivityDashboard::class.java)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragmentView(MonitorFragment())
        binding.bottomNavigationBar.background = null
        binding.bottomNavigationBar.menu.getItem(2).isEnabled = false
        binding.bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.monitor_fragment -> {
                    replaceFragmentView(MonitorFragment())
                    true
                }
                R.id.app_conection_fragment -> {
                    replaceFragmentView(AppConectionFragment())
                    true
                }
                R.id.advices_fragment -> {
                    replaceFragmentView(AdvicesFragment())
                    true
                }
                R.id.settings_fragment -> {
                    replaceFragmentView(SettingsFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.fab.setOnClickListener {
            replaceFragmentView(BabyRegisterFragment())
        }

    }



    fun replaceFragmentView(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.containerViewsFragment.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setReorderingAllowed(false)
        if(fragmentManager.backStackEntryCount > 5) {
            fragmentManager.popBackStack()
        }
        fragmentTransaction.commit()
    }




}