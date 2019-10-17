package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notifications_StateBar extends AppCompatActivity {
    private static final int NOTIF_ALERTA_ID = 100;
    private static final String CHANNEL_ID = "default";
    private Button button_init;
    private Button button_stop;
    private Context context;
    private int count;

    private String ns = Context.NOTIFICATION_SERVICE;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notBuilder;
    private NotificationChannel mChannel;
    private int notificationID = NOTIF_ALERTA_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_state_bar);

        button_init = findViewById(R.id.button_init);
        button_stop = findViewById(R.id.button_stop);

        context = getApplicationContext();

        Intent intent = new Intent(context, Notifications_StateBar.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        count = 0;

        notificationManager = (NotificationManager) getSystemService(ns);

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, "default", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
        }


        button_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                notBuilder = new NotificationCompat.Builder( context, CHANNEL_ID)
                        .setTicker("Tareas")
                        .setContentText("Tareas iniciadas: " + count)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentIntent(pendingIntent);

                notificationManager.notify(notificationID, notBuilder.build());
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;

                if(count > 0) {
                    notBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setTicker("Tareas")
                            .setContentText("Tareas iniciadas: " + count)
                            .setSmallIcon(android.R.drawable.stat_sys_warning)
                            .setContentIntent(pendingIntent);

                    notificationManager.notify(notificationID, notBuilder.build());

                } else {
                    notificationManager.cancel(notificationID);
                }
            }
        });
    }
}
