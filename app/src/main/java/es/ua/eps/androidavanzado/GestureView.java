package es.ua.eps.androidavanzado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

public class GestureView extends View {
    private final int DEFAULT_SIZE = 100;
    private int colour = Color.RED;
    private boolean redColour = true;
    private float x, y;
    private int width, height;
    private RectF rectangle;
    private GestureDetectorCompat detectorCompat;

    public GestureView(Context context) {
        super(context);
        init();
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.width = getWidth();
        this.height = getHeight();
        this.x = width/2;
        this.y = height/2;

        ListenerGestures lg = new ListenerGestures();
        detectorCompat = new GestureDetectorCompat(getContext(), lg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(50);
        colour = redColour ?  Color.RED : Color.BLUE;
        paint.setColor(colour);

        rectangle = new RectF(x, y, x + DEFAULT_SIZE, y + DEFAULT_SIZE);

        canvas.drawRect(rectangle, paint);
        this.invalidate();
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
        return detectorCompat.onTouchEvent(event);
    }

    public class ListenerGestures extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            float nx = e.getX();
            float ny = e.getY();

            return nx >= x && nx <= x + DEFAULT_SIZE && ny >= y && ny <= y + DEFAULT_SIZE;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            x = e2.getX();
            y = e2.getY();

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            redColour = !redColour;

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
