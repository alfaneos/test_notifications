package kz.alfaneos.multimoduleproject.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationCancelReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        val manager = (context?.getSystemService(Context.NOTIFICATION_SERVICE)) as? NotificationManager
        manager?.cancel(0)
    }
}