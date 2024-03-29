package mx.edu.tecnologicodecoacalco.babberlink.login.presentation.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.tecnologicodecoacalco.babberlink.databinding.ActivityRegisterBinding
import mx.edu.tecnologicodecoacalco.babberlink.login.presentation.viewmodel.RegisterActivityViewModel

class RegisterActivity : AppCompatActivity() {

    companion object{
        fun launch(context: Activity){
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var name: EditText
    private lateinit var fatherLastName: EditText
    private lateinit var momLastName: EditText
    private lateinit var cellPhone: EditText
    private lateinit var eMail: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText


    private val registerActivityViewModel: RegisterActivityViewModel by viewModels()
    
    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        name = binding.nameTextRegister
        fatherLastName = binding.dadTextRegister
        momLastName = binding.momTextRegister
        cellPhone = binding.phoneTextRegister
        eMail = binding.emailTextRegister
        password = binding.passwordTextRegister
        confirmPassword = binding.confirmPasswordTextRegister

        binding.cancelButtonRegister.setOnClickListener{
            finish()
        }

        binding.registerButtonRegister.setOnClickListener {
            registerUser()
        }

        registerActivityViewModel.registerAuthLiveData.observe(this, {
            if(it){
                registerUserInfo()
            }
        })

        registerActivityViewModel.notificationResponse.observe(this, {
            if(it){
               Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show()
            }else{
               Toast.makeText(this, "No se pudo completar la operacion", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun areFieldsValid(): Boolean {
        return if(
            name.text.isNotEmpty() ||
            fatherLastName.text.isNotEmpty() ||
            momLastName.text.isNotEmpty() ||
            cellPhone.text.isNotEmpty() ||
            eMail.text.isNotEmpty() ||
            password.text.isNotEmpty() ||
            confirmPassword.text.isNotEmpty()
        ) {
            if(password.text.toString() == confirmPassword.text.toString()){
                true
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                false
            }
        }else{
            Toast.makeText(this, "Porfavor, llena todos los campos", Toast.LENGTH_SHORT).show()
            false
        }

    }

    fun registerUserInfo(){
        val email = eMail.text.toString()
        val data = hashMapOf<String,Any>(
            "nombre" to name.text.toString(),
            "apellidoPaterno" to fatherLastName.text.toString(),
            "apellidoMaterno" to momLastName.text.toString(),
            "celular" to cellPhone.text.toString(),
            "correo" to eMail.text.toString(),
            "imageId" to ""
        )
        registerActivityViewModel.setRegisterUserInfo(email, data)
        
    }

    fun registerUser(){
        if(areFieldsValid()){
            registerActivityViewModel.setRegisterUser(
                email = binding.emailTextRegister.text.toString(),
                password = binding.passwordTextRegister.text.toString()
            )
        }

    }

}