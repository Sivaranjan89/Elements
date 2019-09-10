package com.droid.elements;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.widget.CompoundButtonCompat;

import java.util.ArrayList;

public class UIRadioButton extends RadioGroup {

    ArrayList<String> items = new ArrayList<>();

    Context mContext;

    private int direction, typeface;
    private float textSize;
    private int textColor, radioColor;
    private int radioDrawable = -1;
    private int layoutDirection = 0;
    private int customRadiobutton = -1;

    private int selectedItem;

    public UIRadioButton(Context context) {
        super(context);
        mContext = context;

        direction = 0;
        layoutDirection = 0;
        typeface = 0;
        textColor = Color.BLACK;
        textSize = DroidFunctions.dpToPx(15);
        radioColor = Color.BLACK;
        customRadiobutton = -1;

        init();
    }

    public UIRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        if (attrs == null) {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.UIRadioButton);
        direction = ta.getInt(R.styleable.UIRadioButton_direction, 0);
        layoutDirection = ta.getInt(R.styleable.UIRadioButton_layoutDirection, 0);
        typeface = ta.getInt(R.styleable.UIRadioButton_textStyle, 0);
        textColor = ta.getColor(R.styleable.UIRadioButton_textColor, Color.BLACK);
        textSize = ta.getDimension(R.styleable.UIRadioButton_textSize, DroidFunctions.dpToPx(15));
        radioColor = ta.getColor(R.styleable.UIRadioButton_radioColor, Color.BLACK);
        customRadiobutton = ta.getResourceId(R.styleable.UIRadioButton_customRadiobutton, -1);

        ta.recycle();

        setOrientation(direction);
    }

    private void init() {
        this.removeAllViews();

        for (int i = 0; i < items.size(); i++) {
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setText(items.get(i));
            radioButton.setTextColor(textColor);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DroidFunctions.pxToDp(textSize));
            radioButton.setTypeface(null, typeface);
            radioButton.setLayoutDirection(layoutDirection);

            if (Build.VERSION.SDK_INT < 21) {
                CompoundButtonCompat.setButtonTintList(radioButton, ColorStateList.valueOf(radioColor));
            } else {
                radioButton.setButtonTintList(ColorStateList.valueOf(radioColor));
            }

            if (radioDrawable != -1) {
                radioButton.setButtonDrawable(radioDrawable);
            }

            addView(radioButton);
        }
    }


    public int getSelectedRadio() {
        return getCheckedRadioButtonId();
    }

    public void setData(ArrayList<String> items) {
        this.items = items;
        init();
    }

    public ArrayList<String> getData() {
        return this.items;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        init();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        init();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        init();
    }

    public int getDirection() {
        return direction;
    }

    public void setRadioColor(int radioColor) {
        this.radioColor = radioColor;
        init();
    }

    public int getRadioColor() {
        return radioColor;
    }

    public void setTypeface(int typeface) {
        this.typeface = typeface;
        init();
    }

    public int getTypeface() {
        return typeface;
    }

    public void setCustomRadiobutton(int customRadiobutton) {
        this.customRadiobutton = customRadiobutton;
        init();
    }

    public int getCustomRadiobutton() {
        return customRadiobutton;
    }

    public void setLayoutDirection(int layoutDirection) {
        this.layoutDirection = layoutDirection;
        init();
    }

    public int getLayoutDirection() {
        return layoutDirection;
    }
}
