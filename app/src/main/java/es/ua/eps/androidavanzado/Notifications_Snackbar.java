package es.ua.eps.androidavanzado;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Notifications_Snackbar extends AppCompatActivity {
    private Button button_push_task;
    private EditText editText;
    private TextView list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_snackbar);

        context = getApplicationContext();

        button_push_task = findViewById(R.id.button_push_task);
        editText = findViewById(R.id.editTask);
        list = findViewById(R.id.list_task);

        button_push_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg;
                String text = editText.getText().toString();

                if (text.equals("")) {
                    msg = "Escribe un texto";
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                } else {
                    msg = text;
                    list.append(text+"\n");

                    Snackbar.make(v, "Tarea añadida", Snackbar.LENGTH_SHORT).setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String currentValue = list.getText().toString();
                            currentValue = currentValue.substring(0, currentValue.lastIndexOf("\n"));
                            String newValue = currentValue.substring(0, currentValue.lastIndexOf("\n")+1);

                            list.setText(newValue);
                        }
                    }).show();

                }

                editText.setText("");
            }
        });
    }
}
