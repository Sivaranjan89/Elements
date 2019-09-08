package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UICheckBox extends LinearLayout {

    Context mContext;

    //Text Attributes
    private TextView textView;
    private String text;
    private float textSize;
    private int textStyle;
    private int textColor;
    private String font;

    //Border Attributes
    private int strokeColor;
    private float strokeWidth;
    private float cornerRadius;

    //Icon Attributes
    private ArrayList<Bitmap> icons = new ArrayList<>();
    private int iconPosition;
    private float iconHeight, iconWidth;
    private ImageView image;

    //Component Attributes
    private int backgroundColor;
    private float spacing;
    private Space space;
    private float padding;

    public UICheckBox(Context context) {
        super(context);
        mContext = context;
        initDefaults();
    }

    private void initDefaults() {
        text = "";
        textSize = DroidFunctions.dpToPx(10);
        textStyle = 0;
        textColor = Color.BLACK;
        font = "";
        strokeColor = Color.BLACK;
        strokeWidth = 0;
        cornerRadius = 0;
        iconHeight = -1;
        iconWidth = -1;
        spacing = DroidFunctions.dpToPx(5);
        backgroundColor = Color.TRANSPARENT;
        iconPosition = 0;
        padding = DroidFunctions.dpToPx(1);


        if (iconHeight != -1 || iconWidth != -1) {
            if (iconHeight == -1 && iconWidth != -1) {
                iconHeight = iconWidth;
            }
            if (iconWidth == -1 && iconHeight != -1) {
                iconWidth = iconHeight;
            }
        } else {
            iconWidth = textSize * 2;
            iconHeight = textSize * 2;
        }

        invalidateUICheckbox();
    }

    public UICheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UICheckBox);
        text = ta.getString(R.styleable.UICheckBox_text);
        textSize = ta.getDimension(R.styleable.UICheckBox_textSize, DroidFunctions.dpToPx(10));
        textStyle = ta.getInt(R.styleable.UICheckBox_textStyle, 0);
        textColor = ta.getColor(R.styleable.UICheckBox_textColor, Color.BLACK);
        font = ta.getString(R.styleable.UICheckBox_fontName);
        strokeColor = ta.getColor(R.styleable.UICheckBox_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UICheckBox_strokeWidth, 0);
        cornerRadius = ta.getDimension(R.styleable.UICheckBox_cornerRadius, 0);
        iconHeight = ta.getDimension(R.styleable.UICheckBox_iconHeight, -1);
        iconWidth = ta.getDimension(R.styleable.UICheckBox_iconWidth, -1);
        spacing = ta.getDimension(R.styleable.UICheckBox_spacing, DroidFunctions.dpToPx(5));
        backgroundColor = ta.getColor(R.styleable.UICheckBox_backgroundColor, Color.TRANSPARENT);
        iconPosition = ta.getInt(R.styleable.UICheckBox_iconPosition, 0);
        padding = ta.getDimension(R.styleable.UILabel_labelPadding, DroidFunctions.dpToPx(2));


        if (iconHeight != -1 || iconWidth != -1) {
            if (iconHeight == -1 && iconWidth != -1) {
                iconHeight = iconWidth;
            }
            if (iconWidth == -1 && iconHeight != -1) {
                iconWidth = iconHeight;
            }
        } else {
            iconWidth = textSize * 2;
            iconHeight = textSize * 2;
        }


        if (text == null) {
            text = "";
        }

        ta.recycle();


        try {
            if (backgroundColor == Color.TRANSPARENT) {
                if (getBackground() != null) {
                    backgroundColor = ((ColorDrawable) getBackground()).getColor();
                }
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(mContext, "Please give color only for background or Use app:backgroundColor attribute", Toast.LENGTH_LONG).show();
            }
            backgroundColor = Color.TRANSPARENT;
        }
        invalidateUICheckbox();
    }

    private void invalidateUICheckbox() {
        if (icons.size() > 0) {
            drawIcon();
        }

        drawSpace();
        drawCheckBox();
    }

    private void drawSpace() {

    }

    private void drawCheckBox() {

    }

    private void drawIcon() {

    }


}

