package mx.edu.tecnologicodecoacalco.babberlink.utils

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import mx.edu.tecnologicodecoacalco.babberlink.login.presentation.view.LoginActivity

class FirebaseBackgroundService: FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val dataMap: Map<String, String> = remoteMessage.data
        val title = dataMap["title"]
        val body = dataMap["body"]
        showNotification(title, body)
    }

    fun showNotification(title: String? = "", body: String?  = ""){
        createNotificationChannel()
        val alarmSound: Uri = Settings.System.DEFAULT_RINGTONE_URI
        val vibratePattern = longArrayOf(0, 100, 200, 300)
        val icon: Int = R.drawable.alert_dark_frame
        val context: Context = baseContext
        val notificationIntent = Intent(context, LoginActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(baseContext, "546")
            .setContentText(body)
            .setContentTitle(title)
            .setSmallIcon(icon)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(contentIntent)
            .setSound(alarmSound, AudioManager.STREAM_ALARM)
            .setVibrate(vibratePattern)
        NotificationManagerCompat.from(baseContext).notify(0, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NotificationAlertChannel"
            val descriptionText = "Permite mostrar alertas cuando los bebés estan en peligro"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("546", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


}


