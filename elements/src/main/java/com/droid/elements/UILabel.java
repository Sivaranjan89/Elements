package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
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

public class UILabel extends LinearLayout {

    public static final int LABEL_TEXTGRAVITY_CENTER = 17;
    public static final int LABEL_TEXTGRAVITY_LEFT = 3;
    public static final int LABEL_TEXTGRAVITY_RIGHT = 5;

    Context mContext;

    //Label Constants
    private static final int TEXTVIEW_ID = 2;

    //Label Attributes
    private String text;
    private float textSize;
    private int textStyle;
    private int textColor;
    private String font;
    private TextView textView;
    private boolean strikeText, underlineText;
    private boolean allCaps;
    private int textGravity = LABEL_TEXTGRAVITY_CENTER;

    //Border Attributes
    private int strokeColor;
    private float strokeWidth;
    private float radius;

    //Icon Attributes
    private int icon;
    private int iconPosition;
    private int shape;
    private float imageHeight, imageWidth;
    private ImageView image;

    //Component Attributes
    private int bgColor;
    private float spacing;
    private float endSpacing;
    private int dividerColor, lineColor;
    private boolean showDivider, showLine;
    private float lineWidth, dividerWidth;
    private LinearLayout line;
    private Space space;
    private Space endSpace;
    private View divider;
    private Space space2;
    private float padding;

    public UILabel(Context context) {
        super(context);
        mContext = context;
        initDefaults();
    }

    private void initDefaults() {
        text = "";
        textSize = DroidFunctions.dpToPx(10);
        textStyle = 0;
        textColor = Color.BLACK;
        font = null;
        strokeColor = Color.BLACK;
        strokeWidth = 0;
        radius = 0;
        icon = -1;
        shape = 1;
        imageHeight = -1;
        imageWidth = -1;
        spacing = 0;
        endSpacing = 0;
        bgColor = Color.TRANSPARENT;
        iconPosition = 1;
        dividerColor = Color.TRANSPARENT;
        lineColor = Color.BLACK;
        showDivider = false;
        showLine = false;
        lineWidth = DroidFunctions.dpToPx(1);
        dividerWidth = DroidFunctions.dpToPx(1);
        allCaps = false;
        strikeText = false;
        underlineText = false;
        textGravity = LABEL_TEXTGRAVITY_CENTER;
        padding = 0;

        invalidateUILabel();
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
        textSize = ta.getDimension(R.styleable.UILabel_textSize, DroidFunctions.dpToPx(10));
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
        lineWidth = ta.getDimension(R.styleable.UILabel_lineWidth, DroidFunctions.dpToPx(1));
        dividerWidth = ta.getDimension(R.styleable.UILabel_dividerWidth, DroidFunctions.dpToPx(1));
        allCaps = ta.getBoolean(R.styleable.UILabel_allCaps, false);
        strikeText = ta.getBoolean(R.styleable.UILabel_strikeText, false);
        underlineText = ta.getBoolean(R.styleable.UILabel_underlineText, false);
        textGravity = ta.getInt(R.styleable.UILabel_textGravity, LABEL_TEXTGRAVITY_CENTER);
        padding = ta.getDimension(R.styleable.UILabel_labelPadding, 0);


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
        invalidateUILabel();
    }

    private void drawTextView() {
        this.setOrientation(LinearLayout.VERTICAL);

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

        this.addView(main);
        if (showLine) {
            this.addView(line);
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
        if (stringBuilder != null && !stringBuilder.toString().equalsIgnoreCase("")) {
            textView.setText(stringBuilder);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView.setText(text);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DroidFunctions.pxToDp(textSize));
        textView.setTypeface(tf, textStyle);
        textView.setTextColor(textColor);
        textView.setAllCaps(allCaps);
        textView.setGravity(textGravity);

        if (strikeText) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        if (underlineText) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }

        setEllipsize();
        textView.setLayoutParams(etParams);
        textView.setPadding((int)padding, (int)padding, (int)padding, (int)padding);
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


    //Getters and Setters
    public void setText(String text) {
        this.text = text;
        stringBuilder.clear();
        invalidateUILabel();
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        invalidateUILabel();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextSize(float sizeInDp) {
        this.textSize = DroidFunctions.dpToPx(sizeInDp);
        invalidateUILabel();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setLabel(TextView label) {
        this.textView = label;
        invalidateUILabel();
    }

    public TextView getLabel() {
        return textView;
    }

    public void setImageView(ImageView image) {
        this.image = image;
        invalidateUILabel();
    }

    public ImageView getImageView() {
        return image;
    }

    public void setLineColor(int color) {
        lineColor = color;
        invalidateUILabel();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setDividerColor(int color) {
        this.dividerColor = color;
        invalidateUILabel();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setFont(String font) {
        this.font = font;
        invalidateUILabel();
    }

    public String getFont() {
        return font;
    }

    public void setSpacing(float spacingInDp) {
        this.spacing = DroidFunctions.dpToPx(spacingInDp);
        invalidateUILabel();
    }

    public float getSpacing() {
        return spacing;
    }

    public void setStrokeWidth(float strokeWidthInDp) {
        this.strokeWidth = DroidFunctions.dpToPx(strokeWidthInDp);
        invalidateUILabel();
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setCornerRadius(float radiusInDp) {
        this.radius = DroidFunctions.dpToPx(radiusInDp);
        invalidateUILabel();
    }

    public float getCornerRadius() {
        return radius;
    }

    public void setIcon(int icon) {
        this.icon = icon;
        invalidateUILabel();
    }

    public int getIcon() {
        return icon;
    }

    public void setImageWidth(float imageWidthInDp) {
        this.imageWidth = DroidFunctions.dpToPx(imageWidthInDp);
        invalidateUILabel();
    }

    public float getImageWidth() {
        return imageWidth;
    }

    public void setImageHeight(float imageHeightInDp) {
        this.imageHeight = DroidFunctions.dpToPx(imageHeightInDp);
        invalidateUILabel();
    }

    public float getImageHeight() {
        return imageHeight;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        invalidateUILabel();
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setAllCaps(boolean allCaps) {
        this.allCaps = allCaps;
        invalidateUILabel();
    }

    public boolean isAllCaps() {
        return this.allCaps;
    }

    public void setDividerWidth(float dividerWidthInDp) {
        this.dividerWidth = DroidFunctions.dpToPx(dividerWidthInDp);
        invalidateUILabel();
    }

    public float getDividerWidth() {
        return dividerWidth;
    }

    public void setEndSpacing(float endSpacingInDp) {
        this.endSpacing = DroidFunctions.dpToPx(endSpacingInDp);
        invalidateUILabel();
    }

    public float getEndSpacing() {
        return endSpacing;
    }

    public void setStrikeText(boolean strikeText) {
        this.strikeText = strikeText;
        invalidateUILabel();
    }

    public boolean isStrikeText() {
        return strikeText;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
        invalidateUILabel();
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
        invalidateUILabel();
    }

    public boolean isShowLine() {
        return showLine;
    }

    public void setLineWidth(float lineWidthInDp) {
        this.lineWidth = DroidFunctions.dpToPx(lineWidthInDp);
        invalidateUILabel();
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setUnderlineText(boolean underlineText) {
        this.underlineText = underlineText;
        invalidateUILabel();
    }

    public boolean isUnderlineText() {
        return underlineText;
    }

    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
    }

    public int getTextStyle() {
        return textStyle;
    }

    public void setIconPosition(int iconPosition) {
        this.iconPosition = iconPosition;
    }

    public int getIconPosition() {
        return iconPosition;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public float getPadding() {
        return padding;
    }

    public void setTextGravity(int textGravity) {
        if (textGravity != LABEL_TEXTGRAVITY_CENTER && textGravity != LABEL_TEXTGRAVITY_LEFT && textGravity != LABEL_TEXTGRAVITY_RIGHT ) {
            textGravity = LABEL_TEXTGRAVITY_LEFT;
        }
        this.textGravity = textGravity;
    }

    public int getTextGravity() {
        return textGravity;
    }

    //Spans
    SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

    public void setSpanText(SpannableString spanner) {
        stringBuilder.append(spanner);

        invalidateUILabel();
    }

    public void removeSpans() {
        stringBuilder.clear();
        if (text == null) {
            text = "";
        }
        invalidateUILabel();
    }

    public void invalidateUILabel() {
        removeAllViews();
        drawTextView();
    }
}

