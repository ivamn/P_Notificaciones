package com.example.p_notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String CANAL = "CANAL";
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearCanal();

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Button b1 = findViewById(R.id.normal);
        Button b2 = findViewById(R.id.bigpicture);
        Button b3 = findViewById(R.id.bigtext);
        Button b4 = findViewById(R.id.botones);
        Button b5 = findViewById(R.id.inbox);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                notificacionNormal();
                break;
            case R.id.bigpicture:
                notificacionBigPicture();
                break;
            case R.id.bigtext:
                notificacionBigText();
                break;
            case R.id.botones:
                notificacionBotones();
                break;
            case R.id.inbox:
                notificacionInbox();
                break;
        }
    }

    public void notificacionNormal() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL);
        builder.setContentText("Texto de la notificación normal");
        builder.setContentTitle("Notificación normal");
        builder.setWhen(System.currentTimeMillis());
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.baseline_add_alert_black_48);
        builder.setLargeIcon(b);
        builder.setSmallIcon(R.mipmap.baseline_add_alert_black_48);
        builder.setAutoCancel(false);
        manager.notify(0, builder.build());

    }

    public void notificacionBigPicture() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL);
        NotificationCompat.BigPictureStyle notification = new NotificationCompat.BigPictureStyle(builder);
        notification.setBigContentTitle("Notificación BigPicture");
        notification.setSummaryText("Imagen grande");
        builder.setWhen(System.currentTimeMillis());
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.baseline_add_alert_black_48);
        notification.bigPicture(b);
        builder.setContentText("Content text de esta notificación");
        builder.setLargeIcon(b);
        builder.setSmallIcon(R.mipmap.baseline_add_alert_black_48);
        builder.setAutoCancel(false);
        manager.notify(3, notification.build());
    }

    public void notificacionInbox() {
        String[] lineas = new String[] {"Hola", "Buenas", "Que tal", "Como estamos", "Más texto", "Más texto todavía"};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL);
        NotificationCompat.InboxStyle notification = new NotificationCompat.InboxStyle(builder);
        notification.setBigContentTitle("Notificación Inbox");
        notification.setSummaryText("Summary");
        for (String linea : lineas) {
            notification.addLine(linea);
        }
        builder.setWhen(System.currentTimeMillis());
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.baseline_add_alert_black_48);
        builder.setContentText("Content text de esta notificación");
        builder.setLargeIcon(b);
        builder.setSmallIcon(R.mipmap.baseline_add_alert_black_48);
        builder.setAutoCancel(false);
        manager.notify(5, notification.build());
    }

    public void notificacionBotones() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL);
        Intent i = new Intent(this, MainActivity.class);
        builder.addAction(R.mipmap.baseline_notification_important_black_48, "Remove", PendingIntent.getActivity(MainActivity.this, 0, i, 0));
        builder.addAction(R.mipmap.baseline_notification_important_black_48, "Add", PendingIntent.getActivity(MainActivity.this, 0, i, 0));
        builder.setContentTitle("Notificación con botones");
        builder.setWhen(System.currentTimeMillis());
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.baseline_add_alert_black_48);
        builder.setContentText("Content text de esta notificación");
        builder.setLargeIcon(b);
        builder.setSmallIcon(R.mipmap.baseline_add_alert_black_48);
        builder.setAutoCancel(false);
        manager.notify(4, builder.build());
    }

    public void notificacionBigText() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL);
        NotificationCompat.BigTextStyle notification = new NotificationCompat.BigTextStyle(builder);
        notification.setBigContentTitle("Notificación BigText");
        notification.setSummaryText("Texto largo");
        notification.bigText("Texto de un largo extremadamente largo como podría esperar en una notificación de este tipo. Ciertamente el texto es grande para que pueda caber en una notificación normal, por eso lo pongo en este tipo de notificación.");
        builder.setWhen(System.currentTimeMillis());
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.baseline_add_alert_black_48);
        builder.setLargeIcon(b);
        builder.setSmallIcon(R.mipmap.baseline_add_alert_black_48);
        builder.setAutoCancel(false);
        manager.notify(1, notification.build());
    }

    public void crearCanal() {
        NotificationManager manager = getSystemService(NotificationManager.class);
        NotificationChannel canal = new NotificationChannel(CANAL, "Notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
        canal.setDescription("Canal predeterminado");
        manager.createNotificationChannel(canal);
    }
}
