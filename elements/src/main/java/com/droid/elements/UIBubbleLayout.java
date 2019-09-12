package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class UIBubbleLayout extends LinearLayout {

    Context mContext;

    public UIBubbleLayout(Context context) {
        super(context);
        mContext = context;
        init(null, context);
    }

    public UIBubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, context);
    }

    private void init(AttributeSet set, Context context) {

        if (set == null) {
            return;
        }

        //Retrieve Values
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.UIBubbleLayout);
        int arrowDirection = ta.getInt(R.styleable.UIBubbleLayout_arrowDirection, 4);
        ta.recycle();

        Drawable bubble = null;

        if (arrowDirection == 1) {
            bubble = mContext.getResources().getDrawable(R.drawable.speech_bubble_left);
        } else if (arrowDirection == 2) {
            bubble = mContext.getResources().getDrawable(R.drawable.speech_bubble_right);
        } else if (arrowDirection == 3) {
            bubble = mContext.getResources().getDrawable(R.drawable.speech_bubble_top);
        } else if (arrowDirection == 4) {
            bubble = mContext.getResources().getDrawable(R.drawable.speech_bubble_bottom);
        }

        bubble.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

        setBackground(bubble);
    }

}
