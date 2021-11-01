package com.example.notification_app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var ed: EditText
    lateinit var but: Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    var anynumber = 121

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        ed = findViewById(R.id.edn)
        but = findViewById(R.id.button)

        but.setOnClickListener {
            noty(nm)
            ed.text.clear()
        }

    }
    fun noty(nm: NotificationManager) {
        var builder: Notification.Builder
        if (ed.text.isNotEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(
                    channelId,
                    description,
                    NotificationManager.IMPORTANCE_HIGH
                )
                nm.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
//                .setContentIntent(pendingIntent)
                    .setContentTitle(ed.text)
//                    .setContentText("Hello")
            } else {
                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources,
                            R.drawable.ic_launcher_background
                        )
                    )
//                .setContentIntent(pendingIntent)
                    .setContentTitle(ed.text)
//                    .setContentText("Hello")
            }
            nm.notify(anynumber, builder.build())
        }
    }
}