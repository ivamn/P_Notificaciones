package com.example.p_notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final String CANAL = "CANAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crearCanal() {
        NotificationManager manager = getSystemService(NotificationManager.class);
        NotificationChannel canal = new NotificationChannel(CANAL, "Notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(canal);
    }
}
