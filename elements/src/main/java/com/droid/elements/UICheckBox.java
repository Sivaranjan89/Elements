package com.droid.elements;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.CompoundButtonCompat;

import java.util.ArrayList;

public class UICheckBox extends LinearLayout {

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

    //Icon Attributes
    private ArrayList<Bitmap> icons = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private int iconPosition;
    private float iconHeight, iconWidth;

    //Component Attributes
    private int backgroundColor;
    private float spacing;
    private float padding;
    private int checkColor;
    private int customCheck;
    private Typeface tf;

    //Data
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    private int orientation;

    public UICheckBox(Context context) {
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
        iconHeight = -1;
        iconWidth = -1;
        spacing = DroidFunctions.dpToPx(1);
        backgroundColor = Color.TRANSPARENT;
        checkColor = Color.BLACK;
        iconPosition = 1;
        padding = -1;
        customCheck = -1;
        orientation = 0;


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
        textSize = ta.getDimension(R.styleable.UICheckBox_textSize, DroidFunctions.dpToPx(10));
        textStyle = ta.getInt(R.styleable.UICheckBox_textStyle, 0);
        textColor = ta.getColor(R.styleable.UICheckBox_textColor, Color.BLACK);
        font = ta.getString(R.styleable.UICheckBox_fontName);
        strokeColor = ta.getColor(R.styleable.UICheckBox_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UICheckBox_strokeWidth, 0);
        cornerRadius = ta.getDimension(R.styleable.UICheckBox_cornerRadius, 0);
        iconHeight = ta.getDimension(R.styleable.UICheckBox_iconHeight, -1);
        iconWidth = ta.getDimension(R.styleable.UICheckBox_iconWidth, -1);
        spacing = ta.getDimension(R.styleable.UICheckBox_spacing, DroidFunctions.dpToPx(1));
        backgroundColor = ta.getColor(R.styleable.UICheckBox_backgroundColor, Color.TRANSPARENT);
        iconPosition = ta.getInt(R.styleable.UICheckBox_iconPos, 1);
        padding = ta.getDimension(R.styleable.UICheckBox_checkboxPadding, -1);
        checkColor = ta.getColor(R.styleable.UICheckBox_checkColor, Color.BLACK);
        customCheck = ta.getResourceId(R.styleable.UICheckBox_customCheckbox, -1);
        orientation = ta.getInt(R.styleable.UICheckBox_orientation, 0);


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

    public void invalidateUICheckbox() {
        this.removeAllViews();

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
        if (iconPosition == 1 || iconPosition == 2) {
            drawHorizontalLayouts();
        }
    }

    private void drawHorizontalLayouts() {
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
            //Create Layout
            LinearLayout layout = new LinearLayout(mContext);
            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if ( i > 0 ) {
                if (orientation == 0) {
                    params.setMargins((int)padding, 0, 0, 0);
                } else {
                    params.setMargins(0, (int)padding, 0, 0);
                }
            }
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            if (iconPosition == 1) {
                layout.setPadding(0, 0, (int)DroidFunctions.dpToPx(10), 0);
            } else {
                layout.setPadding((int)DroidFunctions.dpToPx(10), 0, 0, 0);
            }

            ImageView iv_icon = new ImageView(mContext);
            //Create the Icon if provided
            if (icons.size() > 0) {
                iv_icon.setScaleType(ImageView.ScaleType.FIT_XY);
                iv_icon.setLayoutParams(new LayoutParams((int)iconWidth, (int)iconHeight));
                iv_icon.setImageBitmap(icons.get(i));
            }

            CheckBox checkBox = new CheckBox(mContext);
            checkBox.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            checkBox.setGravity(Gravity.CENTER_VERTICAL);
            //Create the Checkbox if data provided
            if (data.size() > 0) {
                checkBox.setText(data.get(i));
                checkBox.setTextColor(textColor);
                checkBox.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DroidFunctions.pxToDp(textSize));
                checkBox.setTypeface(tf, textStyle);
                if (iconPosition == 1) {
                    checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                } else {
                    checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                }

                if (Build.VERSION.SDK_INT < 21) {
                    CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList.valueOf(checkColor));
                } else {
                    checkBox.setButtonTintList(ColorStateList.valueOf(checkColor));
                }

                if (customCheck != -1) {
                    checkBox.setButtonDrawable(customCheck);
                }

                checkBox.setTag("" + i);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        if (checked) {
                            selectedItems.add(Integer.valueOf(compoundButton.getTag().toString()));
                        } else {
                            selectedItems.remove(Integer.valueOf(compoundButton.getTag().toString()));
                        }
                    }
                });
            }

            //Create the Space Component
            Space space = new Space(mContext);
            space.setLayoutParams(new LayoutParams((int)spacing, (int)spacing));

            //Add the Views
            if (icons.size() > 0) {
                if (iconPosition == 1) {
                    layout.addView(iv_icon);
                    layout.addView(space);
                    layout.addView(checkBox);
                } else {
                    layout.addView(checkBox);
                    layout.addView(space);
                    layout.addView(iv_icon);
                }
            } else {
                layout.addView(checkBox);
            }

            this.addView(layout);
        }
    }







    //Setters and Getters

    public void setData(ArrayList<String> data) {
        this.data = data;
        invalidateUICheckbox();
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setIcons(ArrayList<Bitmap> icons) {
        this.icons = icons;
        invalidateUICheckbox();
    }

    public ArrayList<Bitmap> getIcons() {
        return icons;
    }
}

