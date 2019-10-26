package es.ua.eps.androidavanzado;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class TimerService extends Service {
    TimerServiceTask tSS;
    private int timer = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        tSS = new TimerServiceTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if(tSS.getStatus() != AsyncTask.Status.RUNNING) {
            //Log.d("DebugApp", "Comenzamos la tarea en background con el timer a " + valueOf(timer));
            tSS.execute();
        }

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Cuando se detiene el servicio, se cancela la tarea del hilo
        //Log.d("DebugApp", "Finalizamos la tarea en background");
        timer = 11;
        tSS.cancel(true);
    }

    private class TimerServiceTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for(; timer<=10; timer++) {
                Log.d("DebugApp", "Ejecutando background con timer " + valueOf(timer));
                publishProgress(timer);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(), "Timer " + values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void result) {
            stopSelf();
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }
}
