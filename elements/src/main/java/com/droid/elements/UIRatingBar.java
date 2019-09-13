package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

class UIRatingBar extends LinearLayout {

    Context mContext;
    private int starCount;
    private float starSize;
    private int starColor;
    private float spacing;
    private int filledDrawable, emptyDrawable;


    public UIRatingBar(Context context) {
        super(context);
        invalidateComponent();
    }

    public UIRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
        invalidateComponent();
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UIRatingBar);
        starCount = ta.getInteger(R.styleable.UIRatingBar_starCount, 5);
        starSize = ta.getDimension(R.styleable.UIRatingBar_starSize, DroidFunctions.dpToPx(20));
        starColor = ta.getColor(R.styleable.UIRatingBar_starColor, -1);
        spacing = ta.getDimension(R.styleable.UIRatingBar_spacing, DroidFunctions.dpToPx(5));
        filledDrawable = ta.getResourceId(R.styleable.UIRatingBar_filledStar, -1);
        emptyDrawable = ta.getResourceId(R.styleable.UIRatingBar_emptyStar, -1);
        ta.recycle();
    }

    private void invalidateComponent() {

    }
}
