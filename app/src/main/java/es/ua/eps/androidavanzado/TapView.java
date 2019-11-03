package es.ua.eps.androidavanzado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TapView extends View {
    private float x, y;
    private int width, height;
    private final int DEFAULT_SIZE = 100;
    private RectF rectangle;

    public TapView(Context context) {
        super(context);
        init();
    }

    public TapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.width = getWidth();
        this.height = getHeight();
        this.x = width/2;
        this.y = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("DebugApp", "Drawing");

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(50);
        paint.setColor(Color.RED);

        rectangle = new RectF(x, y, x + DEFAULT_SIZE, y + DEFAULT_SIZE);

        canvas.drawRect(rectangle, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        width = DEFAULT_SIZE;
        height = DEFAULT_SIZE;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                if (width > widthSize) {
                    width = widthSize;
                }
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                if (height > heightSize) {
                    height = heightSize;
                }
                break;
        }

        this.setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float nx = event.getX();
                float ny = event.getY();

                if (nx >= this.x && nx <= this.x + DEFAULT_SIZE && ny >= this.y && ny <= this.y + DEFAULT_SIZE) {
                    this.x = nx;
                    this.y = ny;

                    this.invalidate();
                    return true;
                } else {
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                this.x = event.getX();
                this.y = event.getY();
                this.invalidate();
                break;
        }

        return true;
    }
}
