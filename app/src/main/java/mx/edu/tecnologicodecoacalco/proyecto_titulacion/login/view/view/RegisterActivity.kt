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

    }

    fun registerUser(){
//        if(
//            binding.nameTextRegister.text.isEmpty() ||
//            binding.dadTextRegister.text.isEmpty() ||
//            binding.momTextRegister.text.isEmpty() ||
//            binding.phoneTextRegister.text.isEmpty() ||
//            binding.emailTextRegister.text.isEmpty() ||
//            binding.passwordTextRegister.text.isEmpty() ||
//            binding.confirmPasswordTextRegister.text.isEmpty()
//        ){
//            Toast.makeText(this, "Los passwords no coinciden", Toast.LENGTH_SHORT ).show()
//        }else{
//            if(binding.passwordTextRegister.text == binding.confirmPasswordTextRegister.text){
//                val response = registerActivityViewModel.setRegisterUser(
//                    binding.nameTextRegister.text.toString(),
//                    binding.dadTextRegister.text.toString(),
//                    binding.momTextRegister.text.toString(),
//                    binding.phoneTextRegister.text.toString(),
//                    binding.emailTextRegister.text.toString(),
//                    binding.passwordTextRegister.text.toString(),
//                    this
//                )
//                if(response.isSuccesful){
//                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
//                }else{
//                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
//                }
//
//            }else{
//                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT ).show()
//            }
//        }


        val response = registerActivityViewModel.setRegisterUser(
            name = binding.nameTextRegister.text.toString(),
            dadLastName = binding.dadTextRegister.text.toString(),
            momLastName = binding.momTextRegister.text.toString(),
            phone = binding.phoneTextRegister.text.toString(),
            email = binding.emailTextRegister.text.toString(),
            password = binding.passwordTextRegister.text.toString(),
            context = this
        )
        if(response.isSuccesful){
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
        }




    }

}