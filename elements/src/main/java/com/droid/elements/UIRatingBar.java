package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UIRatingBar extends LinearLayout {

    Context mContext;
    private int starCount;
    private float starSize;
    private int starColor;
    private float spacing;
    private int filledDrawable, emptyDrawable;
    private int direction;
    Drawable filledImage, emptyImage;
    private int progress = -1;


    public UIRatingBar(Context context) {
        super(context);

        starCount = 5;
        starSize = DroidFunctions.dpToPx(20);
        starColor = -1;
        spacing = DroidFunctions.dpToPx(5);
        filledDrawable = -1;
        emptyDrawable = -1;
        direction = 0;

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
        direction = ta.getInteger(R.styleable.UIRatingBar_direction, 0);
        progress = ta.getInteger(R.styleable.UIRatingBar_progress, -1);
        ta.recycle();

        if (direction == 0) {
            this.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            this.setOrientation(LinearLayout.VERTICAL);
        }

        if (progress > starCount) {
            progress = -1;
        } else {
            progress = progress - 1;
        }

        emptyImage = DroidFunctions.bitmapToDrawable(mContext, DroidFunctions.imageResourceToBitmap(mContext, emptyDrawable));
        filledImage = DroidFunctions.bitmapToDrawable(mContext, DroidFunctions.imageResourceToBitmap(mContext, filledDrawable));
    }

    private void invalidateComponent() {
        this.removeAllViews();

        for (int i = 0; i < starCount; i++) {
            ImageView iv = new ImageView(mContext);
            if (emptyDrawable != -1 && filledDrawable != -1 && starColor != -1) {
                iv.setColorFilter(starColor, PorterDuff.Mode.MULTIPLY);
            }
            if (i <= progress) {
                iv.setImageResource(filledDrawable);
            } else {
                iv.setImageResource(emptyDrawable);
            }
            LinearLayout.LayoutParams params = new LayoutParams(new LayoutParams((int)starSize, (int)starSize));
            if (i > 0) {
                if (direction == 0) {
                    params.setMargins((int)spacing, 0, 0, 0);
                } else {
                    params.setMargins(0, (int)spacing, 0, 0);
                }
            }
            iv.setLayoutParams(params);
            iv.setTag("" + i);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    progress = Integer.parseInt(view.getTag().toString());
                    invalidateComponent();
                }
            });

            this.addView(iv);
        }
    }



    //Getters and Setters


    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarColor(int starColor) {
        this.starColor = starColor;
    }

    public int getStarColor() {
        return starColor;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setFilledDrawable(int filledDrawable) {
        this.filledDrawable = filledDrawable;
    }

    public int getFilledDrawable() {
        return filledDrawable;
    }

    public void setEmptyDrawable(int emptyDrawable) {
        this.emptyDrawable = emptyDrawable;
    }

    public int getEmptyDrawable() {
        return emptyDrawable;
    }

    public void setProgress(int progress) {
        if (progress > starCount) {
            progress = -1;
        }
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setSpacing(float spacingInDp) {
        spacingInDp = DroidFunctions.dpToPx(spacingInDp);
        this.spacing = spacingInDp;
    }

    public float getSpacing() {
        return spacing;
    }

    public void setStarSize(float starSizeInDp) {
        starSizeInDp = DroidFunctions.dpToPx(starSizeInDp);
        this.starSize = starSizeInDp;
    }

    public float getStarSize() {
        return starSize;
    }
}
