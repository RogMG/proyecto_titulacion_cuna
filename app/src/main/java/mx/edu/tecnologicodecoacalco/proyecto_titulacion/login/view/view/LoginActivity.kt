package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityMainBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.view.viewmodel.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var userEmail: String
    private lateinit var userPassword: String

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val loginActivitityViewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            getUserData()
            loginActivitityViewModel.getLoginAuth(
                userEmail,
                userPassword,
                this
            )
        }

        binding.registerButton.setOnClickListener {
            RegisterActivity.launch(this)
        }

        loginActivitityViewModel.loginLiveData.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
        )


    }

    fun getUserData(){
        userEmail = binding.emailTextLogin.text.toString()
        userPassword = binding.passwordTextLogin.text.toString()
    }


}