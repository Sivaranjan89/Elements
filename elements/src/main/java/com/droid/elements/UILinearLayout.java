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

public class UILinearLayout extends LinearLayout {

    Context mContext;
    private int strokeColor = Color.BLACK;
    private float strokeWidth = 0;
    private float cornerRadius = 0;
    private int bgColor = Color.TRANSPARENT;


    public UILinearLayout(Context context) {
        super(context);
        invalidateComponent();
    }

    public UILinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
        invalidateComponent();
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UILinearLayout);
        strokeColor = ta.getColor(R.styleable.UILinearLayout_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UILinearLayout_strokeWidth, 0);
        cornerRadius = ta.getDimension(R.styleable.UILinearLayout_cornerRadius, 0);
        ta.recycle();
    }

    private void invalidateComponent() {
        try {
            if (bgColor == Color.TRANSPARENT) {
                if (getBackground() != null) {
                    bgColor = ((ColorDrawable) getBackground()).getColor();
                }
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(mContext, "Error in UILinearLayout Background", Toast.LENGTH_LONG).show();
            }
            bgColor = Color.TRANSPARENT;
        }

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(bgColor);
        gd.setCornerRadius(cornerRadius);
        gd.setStroke((int) strokeWidth, strokeColor);
        this.setBackground(gd);
    }
}
