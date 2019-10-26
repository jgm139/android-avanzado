package es.ua.eps.androidavanzado;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Grafica extends View {
    private final int DEFAULT_SIZE = 300;
    private int mPercentage;
    private int width;
    private int height;

    public Grafica(Context context) {
        super(context);
        init(null);
    }

    public Grafica(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Grafica(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = this.getContext().obtainStyledAttributes(attrs, R.styleable.Grafica);
        mPercentage = typedArray.getInt(R.styleable.Grafica_percentage, 0);
    }

    public void setPercentage(int percentage) {
        mPercentage = percentage;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLUE);

        int pl = getPaddingLeft();
        int pr = getPaddingRight();
        int pt = getPaddingTop();
        int pb = getPaddingBottom();

        int usableWidth = width - (pl + pr);
        int usableHeight = height - (pt + pb);

        int radius = Math.min(usableWidth, usableHeight) / 2;
        int cx = pl + (usableWidth / 2);
        int cy = pt + (usableHeight / 2);

        canvas.drawCircle( cx, cy, radius, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        RectF oval = new RectF(cx - radius, cy - radius, cx + radius, cy + radius);
        float finalAngle = (360*mPercentage)/100;
        canvas.drawArc(oval, -90, finalAngle, true, paint);

        invalidate();
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

}
