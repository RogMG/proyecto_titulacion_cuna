package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityMainBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.data.model.LoginModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val loginActivitityViewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailTextLogin.text.toString()
            val password = binding.passwordTextLogin.text.toString()
            val response = loginActivitityViewModel.getLoginAuth(
                email,
                password,
                this
            )
            isLoginSuccess(response)
        }

        binding.registerButton.setOnClickListener {
            RegisterActivity.launch(this)
        }
    }

    fun isLoginSuccess(response: LoginModel){
        if(response.isSuccesful){
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
        }
    }

}