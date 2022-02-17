package mx.edu.tecnologicodecoacalco.babberlink.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import mx.edu.tecnologicodecoacalco.babberlink.R
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.appconection.presentation.view.AppConectionFragment
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.monitor.presentation.view.MonitorFragment
import mx.edu.tecnologicodecoacalco.babberlink.dashboard.settings.presentation.view.SettingsFragment
import mx.edu.tecnologicodecoacalco.babberlink.databinding.ActivityDashboardBinding


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
        binding.bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.monitor_fragment -> {
                    replaceFragmentView(MonitorFragment())
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
            replaceFragmentView(AppConectionFragment())
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