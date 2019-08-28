package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UILabel extends LinearLayout {

    private static final int TEXTVIEW_ID = 2;
    Context mContext;
    private String text;
    private float textSize;
    private int textStyle;
    private int textColor;
    private String font;
    private int strokeColor;
    private float strokeWidth;
    private float radius;
    private int icon;
    private int iconPosition;
    private int shape;
    private int bgColor;
    private float imageHeight, imageWidth;
    private float spacing;
    private float endSpacing;
    private TextView textView;
    private ImageView image;
    private int dividerColor, lineColor;
    private boolean showDivider, showLine;
    private float lineWidth, dividerWidth;
    private LinearLayout line;
    private Space space;
    private Space endSpace;
    private View divider;
    private Space space2;
    private boolean strikeText, underlineText;
    private boolean allCaps;
    private int textGravity = 0;

    public UILabel(Context context) {
        super(context);
        mContext = context;
    }

    public UILabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UILabel);
        text = ta.getString(R.styleable.UILabel_text);
        textSize = ta.getDimension(R.styleable.UILabel_textSize, DroidFunctions.dpToPx(mContext, 10));
        textStyle = ta.getInt(R.styleable.UILabel_textStyle, 0);
        textColor = ta.getColor(R.styleable.UILabel_textColor, Color.BLACK);
        font = ta.getString(R.styleable.UILabel_fontName);
        strokeColor = ta.getColor(R.styleable.UILabel_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UILabel_strokeWidth, 0);
        radius = ta.getDimension(R.styleable.UILabel_cornerRadius, 0);
        icon = ta.getResourceId(R.styleable.UILabel_icon, -1);
        shape = ta.getInt(R.styleable.UILabel_imageShape, 1);
        imageHeight = ta.getDimension(R.styleable.UILabel_imageHeight, -1);
        imageWidth = ta.getDimension(R.styleable.UILabel_imageWidth, -1);
        spacing = ta.getDimension(R.styleable.UILabel_spacing, 0);
        endSpacing = ta.getDimension(R.styleable.UILabel_endSpace, 0);
        bgColor = ta.getColor(R.styleable.UILabel_backgroundColor, Color.TRANSPARENT);
        iconPosition = ta.getInt(R.styleable.UILabel_iconAlignment, 1);
        dividerColor = ta.getColor(R.styleable.UILabel_dividerColor, Color.TRANSPARENT);
        lineColor = ta.getColor(R.styleable.UILabel_lineColor, Color.BLACK);
        showDivider = ta.getBoolean(R.styleable.UILabel_showDivider, false);
        showLine = ta.getBoolean(R.styleable.UILabel_showLine, false);
        lineWidth = ta.getDimension(R.styleable.UILabel_lineWidth, DroidFunctions.dpToPx(mContext, 1));
        dividerWidth = ta.getDimension(R.styleable.UILabel_dividerWidth, DroidFunctions.dpToPx(mContext, 1));
        allCaps = ta.getBoolean(R.styleable.UILabel_allCaps, false);
        strikeText = ta.getBoolean(R.styleable.UILabel_strikeText, false);
        underlineText = ta.getBoolean(R.styleable.UILabel_underlineText, false);
        textGravity = ta.getInt(R.styleable.UILabel_textGravity, 0);


        if (imageHeight != -1 || imageWidth != -1) {
            if (imageHeight == -1 && imageWidth != -1) {
                imageHeight = imageWidth;
            }
            if (imageWidth == -1 && imageHeight != -1) {
                imageWidth = imageHeight;
            }
        } else {
            imageWidth = textSize * 2;
            imageHeight = textSize * 2;
        }


        if (text == null) {
            text = "";
        }

        ta.recycle();


        try {
            if (bgColor == Color.TRANSPARENT) {
                if (getBackground() != null) {
                    bgColor = ((ColorDrawable) getBackground()).getColor();
                }
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(mContext, "Please give color only for background or Use backgroundColor attribute", Toast.LENGTH_LONG).show();
            }
            bgColor = Color.TRANSPARENT;
        }
        drawTextView();
    }

    private void drawTextView() {
        setOrientation(LinearLayout.VERTICAL);

        LinearLayout main = new LinearLayout(mContext);
        main.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setGravity(Gravity.CENTER_VERTICAL);

        designLabel();
        designImage();
        generateSpace();
        generateDivider();
        generateSpace2();
        drawLine();
        generateEndSpace();

        main.setBackground(drawGradientDrawable());
        if (iconPosition == 1) {
            if (icon != -1) {
                main.addView(image);
            }
            if (showDivider) {
                main.addView(space);
                main.addView(divider);
                main.addView(space2);
            } else {
                main.addView(space);
            }
            main.addView(textView);
            main.addView(endSpace);
        } else {
            main.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            textView.setGravity(Gravity.LEFT);
            if (icon != -1) {
                main.addView(image);
            }
            if (showDivider) {
                main.addView(space);
                main.addView(divider);
                main.addView(space2);
            } else {
                main.addView(space);
            }
            main.addView(textView);
            main.addView(endSpace);
        }

        addView(main);
        if (showLine) {
            addView(line);
        }
    }

    private void drawLine() {
        line = new LinearLayout(mContext);
        LayoutParams lineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) lineWidth);
        line.setLayoutParams(lineParams);
        line.setBackgroundColor(lineColor);
    }

    private void designImage() {
        if (shape == 1) {
            image = new ImageView(mContext);
        } else {
            image = new CircularImageview(mContext);
        }
        if (icon != -1) {
            image.setImageResource(icon);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        LayoutParams imgParams = new LayoutParams((int) imageWidth, (int) imageHeight);
        imgParams.gravity = Gravity.CENTER;
        image.setLayoutParams(imgParams);
    }

    private void designLabel() {
        Typeface tf = null;
        try {
            if (font != null && !font.trim().equalsIgnoreCase("")) {
                tf = Typeface.createFromAsset(mContext.getAssets(), font);
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Log.e("ERROR ::::: ", "MISSING / INVALID PATH FOR FONT IN ASSETS");
                Toast.makeText(mContext, "Error in Font Path", Toast.LENGTH_LONG).show();
            }
        }

        textView = new TextView(mContext);
        LayoutParams etParams;
        etParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setBackground(null);
        textView.setId(TEXTVIEW_ID);
        textView.setText(text);
        textView.setTextSize(DroidFunctions.pxToDp(mContext, textSize));
        textView.setTypeface(tf, textStyle);
        textView.setTextColor(textColor);
        textView.setAllCaps(allCaps);
        textView.setGravity(textGravity);
        setEllipsize();
        textView.setLayoutParams(etParams);
    }

    private void setEllipsize() {

    }

    private void generateSpace() {
        space = new Space(mContext);
        if (showDivider) {
            space.setLayoutParams(new LayoutParams((int) spacing / 2, 1));
        } else {
            space.setLayoutParams(new LayoutParams((int) spacing, 1));
        }
    }

    private void generateEndSpace() {
        endSpace = new Space(mContext);
        endSpace.setLayoutParams(new LayoutParams((int) endSpacing, 1));
    }

    private void generateDivider() {
        divider = new View(mContext);
        divider.setBackgroundColor(dividerColor);
        divider.setLayoutParams(new LayoutParams((int) dividerWidth, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void generateSpace2() {
        space2 = new Space(mContext);
        if (showDivider) {
            space2.setLayoutParams(new LayoutParams((int) spacing / 2, 1));
        } else {
            space2.setLayoutParams(new LayoutParams((int) spacing, 1));
        }
    }

    private GradientDrawable drawGradientDrawable() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(bgColor);
        gd.setCornerRadius(radius);
        gd.setStroke((int) strokeWidth, strokeColor);
        return gd;
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextSize(float size) {
        textView.setTextSize(size);
    }

    public TextView getLabel() {
        return textView;
    }

    public void setTextView(TextView label) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return image;
    }

    public void setImageView(ImageView image) {
        this.image = image;
    }

    public void setLineColor(int color) {
        lineColor = color;
    }

    public void setDividerColor(int color) {
        this.dividerColor = color;
    }
}
