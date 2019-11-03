package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Services extends AppCompatActivity {
    private Button button_init;
    private Button button_stop;
    private Button button_reproductor;
    private CheckBox finish_with_activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        context = getApplicationContext();

        button_init = findViewById(R.id.button_init);
        button_stop = findViewById(R.id.button_stop);
        button_reproductor = findViewById(R.id.button_reproductor);
        finish_with_activity = findViewById(R.id.finish_with_activity);

        button_reproductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, Reproductor.class);
                startActivity(intent);
            }
        });

        button_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(context, TimerService.class));
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(context, TimerService.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Si se sale de la actividad Services, se para el servicio contador
        if(finish_with_activity.isChecked()) {
            stopService(new Intent(context, TimerService.class));
        }
    }
}
