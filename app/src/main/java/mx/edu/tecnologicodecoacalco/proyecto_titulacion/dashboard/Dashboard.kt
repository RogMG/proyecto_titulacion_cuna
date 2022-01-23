package mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R

class Dashboard : AppCompatActivity() {
    val addbaby: CardView = findViewById(R.id.cv_add_baby)
    val conectapp: CardView = findViewById(R.id.cv_conect_app)
    val conecthard: CardView = findViewById(R.id.cv_conect_hardware)
    val ajustes: CardView = findViewById(R.id.cv_settings)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


            addbaby.setOnClickListener() {
                val intent: Intent = Intent(this, BabyRegister::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Agregar Bebe", Toast.LENGTH_SHORT)
                    .show()
            }
            conectapp.setOnClickListener() {
                val intent: Intent = Intent(this, AppConect::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Conectar App", Toast.LENGTH_SHORT)
                    .show()
            }
            conecthard.setOnClickListener() {
                val intent: Intent = Intent(this, HardwareConect::class.java)
                startActivity(intent)
                Toast.makeText(
                    applicationContext,
                    "Bienvenido a Conectar Hardware",
                    Toast.LENGTH_SHORT
                ).show()
            }
            ajustes.setOnClickListener() {
                val intent: Intent = Intent(this, Settings::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Bienvenido a Ajustes", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
