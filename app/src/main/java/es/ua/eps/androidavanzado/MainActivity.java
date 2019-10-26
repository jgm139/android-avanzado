package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_drawables;
    private Button button_notifications;
    private Button button_styles;
    private Button button_per_components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_drawables = findViewById(R.id.button_drawables);
        button_notifications = findViewById(R.id.button_notifications);
        button_styles = findViewById(R.id.button_styles);
        button_per_components = findViewById(R.id.button_per_components);
        button_drawables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Drawables.class);
                startActivity(intent);
            }
        });

        button_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Notifications.class);
                startActivity(intent);
            }
        });

        button_styles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sPrincipal.class);
                startActivity(intent);
            }
        });

        button_per_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, pComponents.class);
                startActivity(intent);
            }
        });

    }
}
