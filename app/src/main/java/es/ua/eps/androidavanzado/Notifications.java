package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notifications extends AppCompatActivity {
    private Button button_toast;
    private Button button_snackbar;
    private Button button_dialog;
    private Button button_state_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        button_toast = findViewById(R.id.button_toast);
        button_snackbar = findViewById(R.id.button_snackbar);
        button_dialog = findViewById(R.id.button_dialog);
        button_state_bar = findViewById(R.id.button_state_bar);

        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifications.this, Notifications_Toast.class);
                startActivity(intent);
            }
        });

        button_snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifications.this, Notifications_Snackbar.class);
                startActivity(intent);
            }
        });

        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifications.this, Selection_Dialog.class);
                startActivity(intent);
            }
        });

        button_state_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifications.this, Notifications_StateBar.class);
                startActivity(intent);
            }
        });
    }
}
