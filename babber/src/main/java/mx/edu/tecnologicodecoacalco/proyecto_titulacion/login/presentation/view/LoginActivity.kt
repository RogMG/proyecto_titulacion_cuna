package mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.view

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.viewModels
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.R
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.ActivityDashboard
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.databinding.ActivityMainBinding
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.viewmodel.LoginActivityViewModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils.SharedPreferencesData
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.viewpager.AdvicesActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_EMAIL = "EMAIL_USER_KEY"
    }

    private lateinit var userEmail: String
    private lateinit var userPassword: String

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val sharedPreferences by lazy {
        SharedPreferencesData()
    }

    val loginActivitityViewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val user = Firebase.auth.currentUser
        if (user != null) {
            ActivityDashboard.launch(this)
            finish()
        }
        startBackgroundVideo()
        binding.loginButton.setOnClickListener {
            getUserLogin()
        }

        binding.registerButton.setOnClickListener {
            RegisterActivity.launch(this)
        }

        loginActivitityViewModel.loginLiveData.observe(this, {
            if(it.isSuccesful){
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                AdvicesActivity.launch(this)
            }else{
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        )
    }

    private fun getUserLogin() {
        if (binding.emailTextLogin.text.isEmpty() || binding.passwordTextLogin.text.isEmpty()) {
            Toast.makeText(this, "Porfavor, llena los campos", Toast.LENGTH_LONG).show()
        } else {
            userEmail = binding.emailTextLogin.text.toString()
            userPassword = binding.passwordTextLogin.text.toString()
            loginActivitityViewModel.getLoginAuth(
                userEmail,
                userPassword,
                this
            )
        }
    }

    override fun onResume() {
        super.onResume()
        startBackgroundVideo()
    }

    private fun startBackgroundVideo(){
        val videoview = binding.videoViewLogin
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.baby_sleeping_video)
        videoview.setVideoURI(uri)
        videoview.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })
        videoview.start()
    }



}