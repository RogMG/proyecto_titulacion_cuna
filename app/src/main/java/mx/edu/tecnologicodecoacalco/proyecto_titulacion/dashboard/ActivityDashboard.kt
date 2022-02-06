package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.advices.AdvicesFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.appconection.AppConectionFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.babyregister.BabyRegisterFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.presentation.view.MonitorFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.settings.SettingsFragment
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityDashboardBinding

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
        binding.bottomNavigationBar.background = null
        binding.bottomNavigationBar.menu.getItem(2).isEnabled = false;
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
        if(fragmentManager.backStackEntryCount > 3) {
            fragmentManager.popBackStack()
        }
        fragmentTransaction.commit()
    }
}