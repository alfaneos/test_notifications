package kz.alfaneos.multimoduleproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import kz.alfaneos.multimoduleproject.notification.ITarget
import kz.alfaneos.multimoduleproject.notification.MessengerActivity
import kz.alfaneos.multimoduleproject.notification.NotificationCancelReceiver

class MainActivity : AppCompatActivity() {


    lateinit var downloadButton: View
    val channelId = "super_cahh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadButton = findViewById(R.id.downloadButton)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, "ReadTitle", NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel)

        downloadButton.setOnClickListener {

            val cancelIntent = Intent(this, NotificationCancelReceiver::class.java)
            cancelIntent.action = System.currentTimeMillis().toString()

            val intent = Intent(this, MessengerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.action = System.currentTimeMillis().toString()

            val openIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            val builder = NotificationCompat.Builder(this, "groupId")
                .setContentTitle("Title")
                .setSmallIcon(R.drawable.ic_website)
                .setGroup("groupId")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDeleteIntent(
                    PendingIntent.getBroadcast(
                        this,
                        1,
                        cancelIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                )
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
                .setContentIntent(openIntent)

            builder.setChannelId(channelId)

            Glide.with(this)
                .asBitmap()
                .load("https://s.kaspi.kz/api/resources/6/400/mob-jewelry-0-0-12-2019-rectangle.jpeg")
                .circleCrop()
                .into(object : ITarget(100, 100) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val contentLayout = RemoteViews(applicationContext.packageName, R.layout.push_small)
                        contentLayout.setImageViewBitmap(R.id.not_icon, resource)
                        contentLayout.setTextViewText(R.id.text_title, "title")
                        contentLayout.setTextViewText(R.id.text_description, "description")
                        builder.setCustomContentView(contentLayout)
                        manager.notify(515151, builder.build())

                    }
                })
            Glide.with(this)
                .asBitmap()
                .load("https://s.kaspi.kz/api/resources/6/400/mob-jewelry-0-0-12-2019-rectangle.jpeg")
                .into(object : ITarget(150, 400) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val contentLayout = RemoteViews(applicationContext.packageName, R.layout.push_expanded)
                        contentLayout.setImageViewBitmap(R.id.large_image, resource)
                        contentLayout.setTextViewText(R.id.text_title, "title")
                        contentLayout.setTextViewText(R.id.text_description, "description")
                        builder.setCustomBigContentView(contentLayout)
                        manager.notify(515151, builder.build())

                    }
                })

            manager.notify(515151, builder.build())


        }
    }


}