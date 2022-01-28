package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
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
        setContentView(R.layout.activity_dashboard)

        binding.bottomNavigationBar.background = null;
        binding.bottomNavigationBar.menu.getItem(2).isEnabled = false;


    }
}