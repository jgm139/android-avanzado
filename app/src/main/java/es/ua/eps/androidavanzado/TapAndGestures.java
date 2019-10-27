package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TapAndGestures extends AppCompatActivity {
    private Button button_tap;
    private Button button_gestures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_and_gestures);

        button_tap = findViewById(R.id.button_tap);
        button_gestures = findViewById(R.id.button_gestures);

        button_tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TapAndGestures.this, TapActivity.class);
                startActivity(intent);
            }
        });

        /*button_gestures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TapAndGestures.this, Gestures.class);
                startActivity(intent);
            }
        });*/
    }
}
