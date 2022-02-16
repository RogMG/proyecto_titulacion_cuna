package mx.edu.tecnologicodecoacalco.babberlink.utils

import android.app.Activity
import android.content.Context

class SharedPreferencesData {

    fun saveData(activity: Activity, key: String , data: String ) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(key, data)
            apply()
        }
    }

    fun getData(activity: Activity, key: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = ""
        val highScore = sharedPref.getString(key, defaultValue)
    }




}