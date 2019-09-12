package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class UIDrawingBoard extends View {

    Context mContext;

    float brushSize;
    int sketchColor;

    private static final float STROKE_WIDTH = 5f;
    private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
    private Paint paint = new Paint();
    private Path path = new Path();
    private float lastTouchX;
    private float lastTouchY;
    private final RectF drawingRect = new RectF();

    public UIDrawingBoard(Context context) {
        super(context);
        mContext = context;
        init(null);
    }

    public UIDrawingBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.UIDrawingBoard);
        brushSize = ta.getDimension(R.styleable.UIDrawingBoard_brushSize, DroidFunctions.dpToPx(1));
        sketchColor = ta.getColor(R.styleable.UIDrawingBoard_sketchColor, Color.BLACK);

        ta.recycle();

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(sketchColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(brushSize);
    }

    public Bitmap captureBoard() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        measure(MeasureSpec.makeMeasureSpec(dm.widthPixels,
                MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(dm.heightPixels,
                        MeasureSpec.EXACTLY));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        Bitmap returnedBitmap =
                Bitmap.createBitmap(getMeasuredWidth(),
                        getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(returnedBitmap);
        draw(c);

        return returnedBitmap;
    }

    public void clearBoard() {
        path.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                lastTouchX = eventX;
                lastTouchY = eventY;
                return true;

            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:

                resetdrawingRect(eventX, eventY);
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    expanddrawingRect(historicalX, historicalY);
                    path.lineTo(historicalX, historicalY);
                }
                path.lineTo(eventX, eventY);
                break;

            default:
                return false;
        }

        invalidate((int) (drawingRect.left - HALF_STROKE_WIDTH), (int) (drawingRect.top - HALF_STROKE_WIDTH),
                (int) (drawingRect.right + HALF_STROKE_WIDTH), (int) (drawingRect.bottom + HALF_STROKE_WIDTH));

        lastTouchX = eventX;
        lastTouchY = eventY;

        return true;
    }

    private void expanddrawingRect(float historicalX, float historicalY) {
        if (historicalX < drawingRect.left) {
            drawingRect.left = historicalX;
        } else if (historicalX > drawingRect.right) {
            drawingRect.right = historicalX;
        }

        if (historicalY < drawingRect.top) {
            drawingRect.top = historicalY;
        } else if (historicalY > drawingRect.bottom) {
            drawingRect.bottom = historicalY;
        }
    }

    private void resetdrawingRect(float eventX, float eventY) {
        drawingRect.left = Math.min(lastTouchX, eventX);
        drawingRect.right = Math.max(lastTouchX, eventX);
        drawingRect.top = Math.min(lastTouchY, eventY);
        drawingRect.bottom = Math.max(lastTouchY, eventY);
    }
}
