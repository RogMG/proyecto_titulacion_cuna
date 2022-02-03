package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityRegisterBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel.RegisterActivityViewModel

class RegisterActivity : AppCompatActivity() {

    companion object{
        fun launch(context: Activity){
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val registerActivityViewModel: RegisterActivityViewModel by viewModels()
    
    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cancelButtonRegister.setOnClickListener{
            finish()
        }

        binding.registerButtonRegister.setOnClickListener {
            registerUser()
        }

        registerActivityViewModel.registerLiveData.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })

    }

    fun registerUser(){
        registerActivityViewModel.setRegisterUser(
            name = "",
            dadLastName = "",
            momLastName = "",
            phone = "",
            email = "rogelio@example.com",
            password = "rogelio",
            context = this
        )
//        registerActivityViewModel.setRegisterUser(
//            name = binding.nameTextRegister.text.toString(),
//            dadLastName = binding.dadTextRegister.text.toString(),
//            momLastName = binding.momTextRegister.text.toString(),
//            phone = binding.phoneTextRegister.text.toString(),
//            email = binding.emailTextRegister.text.toString(),
//            password = binding.passwordTextRegister.text.toString(),
//            context = this
//        )
    }

}