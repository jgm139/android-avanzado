package es.ua.eps.androidavanzado;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class EdicionBorrable extends LinearLayout {
    private EditText editText;
    private Button button;

    public EdicionBorrable(Context context) {
        super(context);
        init();
    }

    public EdicionBorrable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EdicionBorrable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.edicionborrable, this, true);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
