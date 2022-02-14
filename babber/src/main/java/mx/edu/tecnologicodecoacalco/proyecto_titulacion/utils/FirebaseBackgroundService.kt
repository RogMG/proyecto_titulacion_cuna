package mx.edu.tecnologicodecoacalco.proyecto_titulacion.utils

import android.content.Intent
import android.os.IBinder
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyIdModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyModel
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.monitor.domain.model.BabyMonitorDTO

import android.R
import android.app.*

import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.initialize
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.dashboard.ActivityDashboard
import mx.edu.tecnologicodecoacalco.proyecto_titulacion.login.presentation.view.LoginActivity
import java.util.concurrent.TimeUnit


class FirebaseBackgroundService: Service() {

    companion object {
        const val CHANNEL_ID = "546"
    }

    private lateinit var firebase: FirebaseFirestore

    private var idCounter = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        firebase = Firebase.firestore
        createNotificationChannel()
        getLpmBabyFromService("rogelio@example.com")
    }

    fun getServices(email: String): CollectionReference {
       return firebase.collection("users")
            .document(email)
            .collection("babies")
    }

    fun getLpmBabyFromService(email: String) {
        val data = getServices(email)
        var nombre = ""
        var monitor = ""
        data.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null ) {
                idCounter = 0
                for(documents in snapshot){
                    documents.getString("monitor")?.let{
                        monitor = it
                    }
                    documents.getString("nombre")?.let{
                        nombre = it
                    }
                    areInDanger(monitor, nombre, idCounter)
                    idCounter += 1
                }
            }
        }
    }

    fun areInDanger(monitor: String, name: String, id: Int){
        if(monitor.toInt() < 65) {
            val icon: Int = R.drawable.alert_dark_frame
            val context: Context = applicationContext
            val notificationIntent = Intent(context, ActivityDashboard::class.java)
            val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)
            var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle("Tu bebé esta en peligro!")
                .setContentText("Revisa inmediatamente a el bebe ${name}")
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)

            NotificationManagerCompat.from(this).notify(id, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NotificationAlerChanel"
            val descriptionText = "Channel dedicated to show alerts when babies are in danger"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseApp.getApps(this).forEach {
            it.delete()
        }
    }



}


