package mx.edu.tecnologicodecoacalco.babberlink.login.domain.datasource

import com.google.firebase.auth.FirebaseAuth

interface LoginDataSource {
    fun getLoginFromService(): FirebaseAuth
}