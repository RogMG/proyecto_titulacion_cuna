package mx.edu.tecnologicodecoacalco.babberlink.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class StartFirebaseAtBoot: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, FirebaseBackgroundService::class.java))
    }

}