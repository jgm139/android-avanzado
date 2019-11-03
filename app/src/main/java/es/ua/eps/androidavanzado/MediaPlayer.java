package es.ua.eps.androidavanzado;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MediaPlayer extends Service {
    private final IBinder iBinder = new MyBinder();
    private MediaServiceTask mSTask;
    private boolean flag_pause = false;
    private int timer = 1;

    //NOTIFICATION VARIABLES
    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    private final static String NOTIFICATION_CHANNEL_ID = "MEDIA_PLAYER";
    int NOTIFICATION_ID = 101; // >= 1!

    @Override
    public void onCreate() {
        super.onCreate();
        setupChannel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupChannel() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Media Player Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Canal para el reproductor de música");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void launchNotification() {
        Intent intent = new Intent(this, Reproductor.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,0);

        int icon;
        String state;

        if (flag_pause) {
            icon = android.R.drawable.ic_media_pause;
            state = "pause";
        } else {
            icon = android.R.drawable.ic_media_play;
            state = "play";
        }

        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Reproductor de música")
                .setContentText("La canción está en " + state + " " + timer)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    public void play() {
        mSTask = new MediaServiceTask();

        if(mSTask.getStatus() != AsyncTask.Status.RUNNING) {
            flag_pause = false;
            mSTask.execute();
        }
    }

    public void pause() {
        flag_pause = true;
        launchNotification();
        mSTask.cancel(true);
    }

    public void go_back() {
        timer -= timer > 5 ? 5 : 0;
    }

    public void forward() {
        timer += 5;
    }

    public void stop() {
        timer = 61;
        mSTask.cancel(true);
        stopForeground(true);
        stopSelf();
    }

    protected class MyBinder extends Binder {

        MediaPlayer getService() {
            return MediaPlayer.this;
        }
    }

    private class MediaServiceTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for(; timer<=60 && !flag_pause; timer++) {
                publishProgress(timer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(), "Song " + values[0], Toast.LENGTH_SHORT).show();
            launchNotification();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            stopSelf();
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }
}
