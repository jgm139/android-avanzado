package es.ua.eps.androidavanzado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

public class TextViewCitas extends AppCompatTextView implements View.OnClickListener {
    private String[] quotes = getResources().getStringArray(R.array.quotes);
    private Random r = new Random();
    private int randomNumber;

    public TextViewCitas(Context context) {
        super(context);
        init();
    }

    public TextViewCitas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewCitas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        randomNumber = r.nextInt(quotes.length);
        this.setText(quotes[randomNumber]);
        this.setTypeface(this.getTypeface(), Typeface.ITALIC);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        randomNumber = r.nextInt(quotes.length);
        this.setText(quotes[randomNumber]);
    }
}
