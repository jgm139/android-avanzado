package es.ua.eps.androidavanzado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Selection_Dialog extends AppCompatActivity{
    private Button button_size;
    private Button button_colour;
    private TextView textView;
    private ScrollView container;
    private final String[] options_colours = {"Blanco y negro", "Negro y blanco", "Negro y verde"};
    private final String[] options_size = {"Pequeño", "Normal", "Grande"};
    private int selected_colour;
    private int selected_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_dialog);

        button_size = findViewById(R.id.button_size);
        button_colour = findViewById(R.id.button_colour);
        textView = findViewById(R.id.textView);
        container = findViewById(R.id.layout_dialog_selection);

        button_colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Selection_Dialog.this);
                alertDialog.setTitle("Color");

                alertDialog.setSingleChoiceItems(options_colours, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        selected_colour = item;
                    }
                });

                alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch (options_colours[selected_colour]) {
                            case "Blanco y negro":
                                container.setBackgroundColor(Color.WHITE);
                                textView.setTextColor(Color.BLACK);
                                break;
                            case "Negro y blanco":
                                container.setBackgroundColor(Color.BLACK);
                                textView.setTextColor(Color.WHITE);
                                break;
                            case "Negro y verde":
                                container.setBackgroundColor(Color.BLACK);
                                textView.setTextColor(Color.GREEN);
                                break;
                        }
                    }
                });

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        //
                    }
                });

                alertDialog.show();
            }
        });

        button_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Selection_Dialog.this);
                alertDialog.setTitle("Tamaño");

                alertDialog.setSingleChoiceItems(options_size, -1, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int item) {
                       selected_size = item;
                   }
                });

                alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch (options_size[selected_size]) {
                            case "Pequeño":
                                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                                break;
                            case "Normal":
                                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                                break;
                            case "Grande":
                                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                                break;
                        }
                    }
                });

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        //
                    }
                });

                alertDialog.show();
            }
        });
    }
}
