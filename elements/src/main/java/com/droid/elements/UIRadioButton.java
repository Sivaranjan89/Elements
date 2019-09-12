package com.droid.elements;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.CompoundButtonCompat;

import java.util.ArrayList;

public class UIRadioButton extends LinearLayout {
    public static final int TEXTSTYLE_NORMAL = 0;
    public static final int TEXTSTYLE_BOLD = 1;

    public static final int DIRECTION_HORIZONTAL = 0;
    public static final int DIRECTION_VERTICAL = 0;

    Context mContext;

    //Text Attributes
    private TextView textView;
    private float textSize;
    private int textStyle;
    private int textColor;
    private String font;

    //Border Attributes
    private int strokeColor;
    private float strokeWidth;
    private float cornerRadius;

    //Component Attributes
    private int backgroundColor;
    private int checkColor;
    private int customCheck;
    private Typeface tf;

    //Data
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private int selectedItem = 0;
    private int orientation;
    private int renderDirection = 0;

    public UIRadioButton(Context context) {
        super(context);
        mContext = context;
        initDefaults();
    }

    private void initDefaults() {
        textSize = DroidFunctions.dpToPx(10);
        textStyle = 0;
        textColor = Color.BLACK;
        font = "";
        strokeColor = Color.BLACK;
        strokeWidth = 0;
        cornerRadius = 0;
        backgroundColor = Color.TRANSPARENT;
        checkColor = Color.BLACK;
        customCheck = -1;
        orientation = 0;
        renderDirection = 0;
        selectedItem = 0;

        invalidateUIRadio();
    }

    public UIRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UIRadioButton);
        textSize = ta.getDimension(R.styleable.UIRadioButton_textSize, DroidFunctions.dpToPx(10));
        textStyle = ta.getInt(R.styleable.UIRadioButton_textStyle, 0);
        textColor = ta.getColor(R.styleable.UIRadioButton_textColor, Color.BLACK);
        font = ta.getString(R.styleable.UIRadioButton_fontName);
        strokeColor = ta.getColor(R.styleable.UIRadioButton_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UIRadioButton_strokeWidth, 0);
        cornerRadius = ta.getDimension(R.styleable.UIRadioButton_cornerRadius, 0);
        backgroundColor = ta.getColor(R.styleable.UIRadioButton_backgroundColor, Color.TRANSPARENT);
        checkColor = ta.getColor(R.styleable.UIRadioButton_checkColor, Color.BLACK);
        customCheck = ta.getResourceId(R.styleable.UIRadioButton_customCheckbox, -1);
        orientation = ta.getInt(R.styleable.UIRadioButton_direction, 0);
        renderDirection = ta.getInt(R.styleable.UIRadioButton_renderDirection, 0);
        selectedItem = ta.getInteger(R.styleable.UIRadioButton_selectedItem, 0);
        CharSequence[] chardata = ta.getTextArray(R.styleable.UIRadioButton_data);

        if (chardata != null) {
            for (int i = 0; i < chardata.length - 1; i++) {
                data.add(chardata[i].toString());
            }
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
        invalidateUIRadio();
    }

    public void invalidateUIRadio() {
        this.removeAllViews();
        radioButtons.clear();

        //Retrieve the font if provided
        tf = null;
        try {
            if (font != null && !font.trim().equalsIgnoreCase("")) {
                tf = Typeface.createFromAsset(mContext.getAssets(), font);
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Log.e("ERROR ::::: ", "MISSING / INVALID PATH FOR FONT IN ASSETS");
                Toast.makeText(mContext, "Error in Font Path", Toast.LENGTH_LONG).show();
                font = null;
            }
        }

        //Draw the layout
        draw();
    }

    private void draw() {
        //Design the Parent Layout
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(backgroundColor);
        gd.setCornerRadius(cornerRadius);
        gd.setStroke((int) strokeWidth, strokeColor);
        this.setBackground(gd);
        if (orientation == 0) {
            this.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            this.setOrientation(LinearLayout.VERTICAL);
        }

        for (int i=0; i<data.size(); i++) {
            RadioButton radio = new RadioButton(mContext);
            if (i == selectedItem) {
                radio.setChecked(true);
            } else {
                radio.setChecked(false);
            }
            radio.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radio.setGravity(Gravity.CENTER_VERTICAL);
            radio.setText(data.get(i));
            radio.setTextColor(textColor);
            radio.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DroidFunctions.pxToDp(textSize));
            radio.setTypeface(tf, textStyle);
            if (renderDirection == 0) {
                radio.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            } else {
                radio.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }

            if (Build.VERSION.SDK_INT < 21) {
                CompoundButtonCompat.setButtonTintList(radio, ColorStateList.valueOf(checkColor));
            } else {
                radio.setButtonTintList(ColorStateList.valueOf(checkColor));
            }

            if (customCheck != -1) {
                radio.setButtonDrawable(customCheck);
            }

            radio.setTag("" + i);
            radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (checked) {
                        selectedItem = Integer.valueOf(compoundButton.getTag().toString());
                        invalidateUIRadio();
                    }
                }
            });

            radioButtons.add(radio);
        }

        for (int i = 0; i < radioButtons.size(); i++) {
            this.addView(radioButtons.get(i));
        }
    }







    //Setters and Getters

    public void setData(ArrayList<String> data) {
        this.data = data;
        invalidateUIRadio();
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setStrokeWidth(float strokeWidthInDp) {
        this.strokeWidth = DroidFunctions.dpToPx(strokeWidthInDp);
        invalidateUIRadio();
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidateUIRadio();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setTextSize(float textSizeInDp) {
        this.textSize = DroidFunctions.dpToPx(textSizeInDp);
        invalidateUIRadio();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
        invalidateUIRadio();
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextStyle(int textStyle) {
        if (textStyle < 0 || textStyle > 1) {
            textStyle = TEXTSTYLE_NORMAL;
        }
        this.textStyle = textStyle;
        invalidateUIRadio();
    }

    public int getTextStyle() {
        return textStyle;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidateUIRadio();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setRenderDirection(int renderDirection) {
        this.renderDirection = renderDirection;
        invalidateUIRadio();
    }

    public int getRenderDirection() {
        return renderDirection;
    }

    public void setFont(String fontName) {
        this.font = fontName;
        invalidateUIRadio();
    }

    public String getFont() {
        return font;
    }

    public void setDirection(int direction) {
        if (orientation < 0 || orientation > 1) {
            orientation = DIRECTION_HORIZONTAL;
        }
        this.orientation = orientation;
        invalidateUIRadio();
    }

    public int getDirection() {
        return orientation;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        invalidateUIRadio();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setCheckColor(int checkColor) {
        this.checkColor = checkColor;
        invalidateUIRadio();
    }

    public int getCheckColor() {
        return checkColor;
    }

    public void setCornerRadius(float cornerRadiusInDp) {
        this.cornerRadius = DroidFunctions.dpToPx(cornerRadiusInDp);
        invalidateUIRadio();
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCustomCheck(int customCheck) {
        this.customCheck = customCheck;
        invalidateUIRadio();
    }

    public int getCustomCheck() {
        return customCheck;
    }

    public void setSelectedItem(int selectedItemPosition) {
        this.selectedItem = selectedItemPosition;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}

