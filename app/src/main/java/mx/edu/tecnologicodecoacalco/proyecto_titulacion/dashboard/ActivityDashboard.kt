package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityDashboardBinding

class ActivityDashboard : AppCompatActivity() {

    companion object {
        fun launch(context: Activity){
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
            binding.cvAddBaby.setOnClickListener {
                val intent: Intent = Intent(this, ActivityBabyRegister::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Agregar Bebe", Toast.LENGTH_SHORT)
                    .show()
            }
        binding.cvConectApp.setOnClickListener() {
                val intent: Intent = Intent(this, ActivityAppConection::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Conectar App", Toast.LENGTH_SHORT)
                    .show()
            }
        binding.cvConectHardware.setOnClickListener() {
                val intent: Intent = Intent(this, ActivityHardwareConection::class.java)
                startActivity(intent)
                Toast.makeText(
                    applicationContext,
                    "Bienvenido a Conectar Hardware",
                    Toast.LENGTH_SHORT
                ).show()
            }
        binding.cvSettings.setOnClickListener() {
                val intent: Intent = Intent(this, ActivitySettings::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Ajustes", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
