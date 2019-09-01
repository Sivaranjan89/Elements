package com.droid.elements;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
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

public class UIEditText extends LinearLayout {

    public static final int ICONPOSITION_LEFT = 1;
    public static final int ICONPOSITION_RIGHT = 2;
    public static final int ICONPOSITION_TOP = 3;
    public static final int ICONPOSITION_BOTTOM = 4;

    public static final int HELPERTEXTOSITION_TOP_LEFT = 1;
    public static final int HELPERTEXTPOSITION_TOP_RIGHT = 2;
    public static final int HELPERTEXTPOSITION_BOTTOM_LEFT = 3;
    public static final int HELPERTEXTPOSITION_BOTTOM_RIGHT = 4;

    public static final int TEXTSTYLE_NORMAL = 0;
    public static final int TEXTSTYLE_BOLD = 1;

    private static final int EDITTEXT_ID = 1;
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
    private EditText editText;
    private ImageView image;
    private int editTextShape;
    private String hintText;
    private String helperText;
    private float hintTextSize;
    private float helperTextSize;
    private int helperTextStyle;
    private int helperTextColor;
    private String helperFont;
    private int dividerColor, lineColor;
    private boolean showDivider, showLine;
    private float lineWidth, dividerWidth;
    private LinearLayout line;
    private int hintTextColor;
    private Space space;
    private View divider;
    private Space space2;
    private TextView helper;
    private int helperPosition;
    private boolean autoStretch, singleLine;
    private int maxLines;
    private int inputType;
    private String digits;
    private boolean allowPaste;
    private boolean allCaps;
    private int maxLength;
    private int lines;
    private float letterSpacing;
    private float lineSpacing;
    private boolean cursorVisible;
    private String imeActionLabel;
    private int imeOptions;
    private int ellipsize;
    private String currency;
    private float leftPadding, rightPadding, topPadding, bottomPadding;
    private boolean disableComponent = false;

    public UIEditText(Context context) {
        super(context);
        mContext = context;

        initDefaults();
    }

    private void initDefaults() {
        text = "";
        textSize = DroidFunctions.dpToPx(10);
        textStyle = TEXTSTYLE_NORMAL;
        textColor = Color.BLACK;
        font = "";
        strokeColor = Color.BLACK;
        strokeWidth = 0;
        radius = 0;
        icon = -1;
        shape = 1;
        imageHeight = -1;
        imageWidth = -1;
        spacing = 0;
        bgColor = Color.TRANSPARENT;

        hintText = "";
        hintTextSize = DroidFunctions.dpToPx(10);
        helperText = "";
        helperTextSize = DroidFunctions.dpToPx(10);
        helperTextStyle = TEXTSTYLE_NORMAL;
        hintTextColor = Color.GRAY;
        helperTextColor = Color.BLACK;
        helperFont = "";
        iconPosition = ICONPOSITION_LEFT;
        editTextShape = 1;
        dividerColor = Color.TRANSPARENT;
        lineColor = Color.BLACK;
        showDivider = true;
        showLine = true;
        lineWidth = DroidFunctions.dpToPx(1);
        dividerWidth = DroidFunctions.dpToPx(1);
        helperPosition = HELPERTEXTPOSITION_BOTTOM_LEFT;
        autoStretch = false;
        singleLine = false;
        maxLines = 0;
        inputType = 1;
        digits = "";
        allowPaste = true;
        allCaps = false;
        maxLength = 0;
        lines = 0;
        letterSpacing = 0;
        lineSpacing = 0;
        cursorVisible = true;
        imeActionLabel = "";
        imeOptions = 6;
        ellipsize = 0;
        currency = "";
        leftPadding = DroidFunctions.dpToPx(8);
        rightPadding = DroidFunctions.dpToPx(8);
        topPadding = DroidFunctions.dpToPx(8);
        bottomPadding = DroidFunctions.dpToPx(8);
        disableComponent = false;

        invalidateComponent();
    }

    public UIEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UIEditText);
        text = ta.getString(R.styleable.UIEditText_text);
        textSize = ta.getDimension(R.styleable.UIEditText_textSize, DroidFunctions.dpToPx(10));
        textStyle = ta.getInt(R.styleable.UIEditText_textStyle, 0);
        textColor = ta.getColor(R.styleable.UIEditText_textColor, Color.BLACK);
        font = ta.getString(R.styleable.UIEditText_fontName);
        strokeColor = ta.getColor(R.styleable.UIEditText_strokeColor, Color.BLACK);
        strokeWidth = ta.getDimension(R.styleable.UIEditText_strokeWidth, 0);
        radius = ta.getDimension(R.styleable.UIEditText_cornerRadius, 0);
        icon = ta.getResourceId(R.styleable.UIEditText_icon, -1);
        shape = ta.getInt(R.styleable.UIEditText_imageShape, 1);
        imageHeight = ta.getDimension(R.styleable.UIEditText_imageHeight, -1);
        imageWidth = ta.getDimension(R.styleable.UIEditText_imageWidth, -1);
        spacing = ta.getDimension(R.styleable.UIEditText_spacing, 0);
        bgColor = ta.getColor(R.styleable.UIEditText_backgroundColor, Color.TRANSPARENT);

        hintText = ta.getString(R.styleable.UIEditText_hintText);
        hintTextSize = ta.getDimension(R.styleable.UIEditText_hintTextSize, DroidFunctions.dpToPx(10));
        helperText = ta.getString(R.styleable.UIEditText_helperText);
        helperTextSize = ta.getDimension(R.styleable.UIEditText_helperTextSize, DroidFunctions.dpToPx(10));
        helperTextStyle = ta.getInt(R.styleable.UIEditText_helperTextStyle, 0);
        hintTextColor = ta.getColor(R.styleable.UIEditText_hintTextColor, Color.GRAY);
        helperTextColor = ta.getColor(R.styleable.UIEditText_helperTextColor, Color.BLACK);
        helperFont = ta.getString(R.styleable.UIEditText_helperFontName);
        iconPosition = ta.getInt(R.styleable.UIEditText_iconAlignment, 1);
        editTextShape = ta.getInt(R.styleable.UIEditText_editTextShape, 1);
        dividerColor = ta.getColor(R.styleable.UIEditText_dividerColor, Color.TRANSPARENT);
        lineColor = ta.getColor(R.styleable.UIEditText_lineColor, Color.BLACK);
        showDivider = ta.getBoolean(R.styleable.UIEditText_showDivider, true);
        showLine = ta.getBoolean(R.styleable.UIEditText_showLine, true);
        lineWidth = ta.getDimension(R.styleable.UIEditText_lineWidth, DroidFunctions.dpToPx(1));
        dividerWidth = ta.getDimension(R.styleable.UIEditText_dividerWidth, DroidFunctions.dpToPx(1));
        helperPosition = ta.getInt(R.styleable.UIEditText_helperTextPosition, 4);
        autoStretch = ta.getBoolean(R.styleable.UIEditText_autoStretch, false);
        singleLine = ta.getBoolean(R.styleable.UIEditText_singleLine, false);
        maxLines = ta.getInt(R.styleable.UIEditText_maxLines, 0);
        inputType = ta.getInt(R.styleable.UIEditText_inputType, 1);
        digits = ta.getString(R.styleable.UIEditText_digits);
        allowPaste = ta.getBoolean(R.styleable.UIEditText_allowPaste, true);
        allCaps = ta.getBoolean(R.styleable.UIEditText_allCaps, false);
        maxLength = ta.getInt(R.styleable.UIEditText_maxLength, 0);
        lines = ta.getInt(R.styleable.UIEditText_lines, 0);
        letterSpacing = ta.getDimension(R.styleable.UIEditText_letterSpacing, DroidFunctions.dpToPx(0));
        lineSpacing = ta.getDimension(R.styleable.UIEditText_lineSpacing, DroidFunctions.dpToPx(0));
        cursorVisible = ta.getBoolean(R.styleable.UIEditText_cursorVisible, true);
        imeActionLabel = ta.getString(R.styleable.UIEditText_imeActionLabel);
        imeOptions = ta.getInt(R.styleable.UIEditText_imeOptions, 6);
        ellipsize = ta.getInt(R.styleable.UIEditText_ellipsize, 0);
        currency = ta.getString(R.styleable.UIEditText_currencySymbol);
        leftPadding = ta.getDimension(R.styleable.UIEditText_leftPadding, DroidFunctions.dpToPx(8));
        rightPadding = ta.getDimension(R.styleable.UIEditText_rightPadding, DroidFunctions.dpToPx(8));
        topPadding = ta.getDimension(R.styleable.UIEditText_topPadding, DroidFunctions.dpToPx(8));
        bottomPadding = ta.getDimension(R.styleable.UIEditText_bottomPadding, DroidFunctions.dpToPx(8));
        disableComponent = ta.getBoolean(R.styleable.UIEditText_disableComponent, false);


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
        if (helperText == null) {
            helperText = "";
        }
        if (hintText == null) {
            hintText = "";
        }
        if (digits == null) {
            digits = "";
        }
        if (imeActionLabel == null) {
            imeActionLabel = "";
        }
        if (currency == null) {
            currency = "";
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
        invalidateComponent();
    }

    public void invalidateComponent() {
        this.removeAllViews();
        drawEditText();
    }

    private void drawEditText() {
        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout main = new LinearLayout(mContext);
        main.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        main.setOrientation(LinearLayout.HORIZONTAL);

        designEditText();
        designImage();
        generateSpace();
        generateDivider();
        generateSpace2();
        designHelperText();

        if (editTextShape == 1 || editTextShape == 2) {
            main.setBackground(drawGradientDrawable());
            if (icon != -1) {
                if (iconPosition == 1) {
                    main.addView(image);
                    if (showDivider) {
                        main.addView(space);
                        main.addView(divider);
                        main.addView(space2);
                    } else {
                        main.addView(space);
                    }
                    main.addView(editText);
                } else {
                    main.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    editText.setGravity(Gravity.LEFT);
                    main.addView(image);
                    if (showDivider) {
                        main.addView(space);
                        main.addView(divider);
                        main.addView(space2);
                    } else {
                        main.addView(space);
                    }
                    main.addView(editText);
                }
            }
            else {
                main.addView(editText);
            }
        } else if (editTextShape == 3) {
            drawLine();
            if (icon != -1) {
                if (iconPosition == 1) {
                    main.addView(image);
                    if (showDivider) {
                        main.addView(space);
                        main.addView(divider);
                        main.addView(space2);
                    } else {
                        main.addView(space);
                    }
                    main.addView(editText);
                } else {
                    main.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    editText.setGravity(Gravity.LEFT);
                    main.addView(image);
                    if (showDivider) {
                        main.addView(space);
                        main.addView(divider);
                        main.addView(space2);
                    } else {
                        main.addView(space);
                    }
                    main.addView(editText);
                }
            }
            else {
                main.addView(editText);
            }
        }

        if (helperPosition == 1 || helperPosition == 2) {
            this.addView(helper);
        }

        this.addView(main);

        if (editTextShape == 3 && showLine) {
            this.addView(line);
        }
        if (helperPosition == 3 || helperPosition == 4) {
            this.addView(helper);
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

    private void designEditText() {
        if (singleLine) {
            maxLines = 1;
        }
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

        editText = new EditText(mContext);
        LayoutParams etParams;
        if (autoStretch) {
            etParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            etParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        editText.setPadding((int)leftPadding, (int)topPadding, (int)rightPadding, (int)bottomPadding);
        editText.setBackground(null);
        editText.setId(EDITTEXT_ID);
        editText.setText(text);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DroidFunctions.pxToDp(textSize));
        editText.setTypeface(tf, textStyle);
        editText.setTextColor(textColor);

        SpannableStringBuilder hintTextBuilder = new SpannableStringBuilder("");
        hintTextBuilder.append(new TextSpanner(hintText).setTextColor(hintTextColor)
                .setTextSizeAbsolute(DroidFunctions.pxToDp(hintTextSize))
                .build());
        editText.setHint(hintTextBuilder);

        editText.setInputType(inputType);
        editText.setImeOptions(imeOptions);
        editText.setLongClickable(allowPaste);
        ArrayList<InputFilter> filters = new ArrayList<>();
        if (maxLength > 0) {
            filters.add(new InputFilter.LengthFilter(maxLength));
        }
        if (allCaps) {
            filters.add(new InputFilter.AllCaps());
        }
        InputFilter[] finalFilters = new InputFilter[filters.size()];
        for (int i = 0; i < filters.size(); i++) {
            finalFilters[i] = filters.get(i);
        }
        if (maxLength > 0 || allCaps) {
            editText.setFilters(finalFilters);
        }
        if (maxLines > 0) {
            editText.setMaxLines(maxLines);
        }
        if (lines > 0) {
            editText.setLines(lines);
        }
        if (!digits.equalsIgnoreCase("")) {
            editText.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
        editText.setLetterSpacing(letterSpacing);
        editText.setLineSpacing(lineSpacing, 1);
        editText.setCursorVisible(cursorVisible);
        setEllipsize();
        editText.setLayoutParams(etParams);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (onEditorActionListener != null) {
                    onEditorActionListener.onEditorAction(textView, i, keyEvent);
                }
                return false;
            }
        });
        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyClickListener != null) {
                    keyClickListener.onKey(view, i, keyEvent);
                }
                return false;
            }
        });
        editText.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    longClickListener.onLongClick(view);
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (addTextChangedListener != null) {
                    addTextChangedListener.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().startsWith(currency) && !currency.equalsIgnoreCase("") && !s.toString().equalsIgnoreCase("")) {
                    s = currency + s;
                    editText.setText(s);
                    editText.setSelection(s.toString().length());
                }
                if (s.toString().equalsIgnoreCase(currency) && !currency.equalsIgnoreCase("")) {
                    editText.setText("");
                    s = "";
                }
                if (addTextChangedListener != null) {
                    addTextChangedListener.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (addTextChangedListener != null) {
                    addTextChangedListener.afterTextChanged(editable);
                }
            }
        });

        if (disableComponent) {
            editText.setEnabled(false);
            editText.setAlpha(0.5f);
        } else {
            editText.setEnabled(true);
            editText.setAlpha(1f);
        }
    }

    private void designHelperText() {
        Typeface tf = null;
        if (helperFont != null && !font.trim().equalsIgnoreCase("")) {
            tf = Typeface.createFromAsset(mContext.getAssets(), helperFont);
        }

        helper = new TextView(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if (helperPosition == 2 || helperPosition == 4) {
            params.gravity = Gravity.RIGHT;
            helper.setGravity(Gravity.RIGHT);
        } else {
            params.gravity = Gravity.LEFT;
        }
        helper.setBackground(null);
        helper.setText(helperText);
        helper.setTextSize(DroidFunctions.pxToDp(helperTextSize));
        helper.setTypeface(tf, helperTextStyle);
        helper.setTextColor(helperTextColor);
        helper.setLayoutParams(params);
    }

    private void setEllipsize() {
        if (ellipsize == 1) {
            editText.setEllipsize(TextUtils.TruncateAt.START);
        } else if (ellipsize == 2) {
            editText.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (ellipsize == 3) {
            editText.setEllipsize(TextUtils.TruncateAt.END);
        } else if (ellipsize == 4) {
            editText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        }
    }

    private void generateSpace() {
        space = new Space(mContext);
        if (showDivider) {
            space.setLayoutParams(new LayoutParams((int) spacing / 2, 1));
        } else {
            space.setLayoutParams(new LayoutParams((int) spacing, 1));
        }
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
        if (editTextShape == 2) {
            gd.setShape(GradientDrawable.OVAL);
        }
        return gd;
    }


    //Getters and Setters
    public void setText(String text) {
        this.text = text;
        invalidateComponent();
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        invalidateComponent();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextSize(float sizeInDp) {
        this.textSize = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextStyle(int textStyle) {
        if (textStyle < 0 || textStyle > 1) {
            this.textStyle = TEXTSTYLE_NORMAL;
        }
        this.textStyle = textStyle;
        invalidateComponent();
    }

    public int getTextStyle() {
        return textStyle;
    }

    public void setHintText(String text) {
        this.hintText = text;
        invalidateComponent();
    }

    public String getHintText() {
        return editText.getHint().toString();
    }

    public void setHintTextColor(int color) {
        this.hintTextColor = color;
        invalidateComponent();
    }

    public int getHintTextColor() {
        return hintTextColor;
    }

    public void setHintTextSize(float sizeInDp) {
        this.hintTextSize = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getHintTextSize() {
        return hintTextSize;
    }

    public void setHelperText(String text) {
        this.helperText = text;
        invalidateComponent();
    }

    public String getHelperText() {
        return helper.getText().toString();
    }

    public void setHelperTextColor(int color) {
        this.helperTextColor = color;
        invalidateComponent();
    }

    public int getHelperTextColor() {
        return helperTextColor;
    }

    public void setHelperTextSize(float sizeInDp) {
        this.helperTextSize = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getHelperTextSize() {
        return helperTextSize;
    }

    public void setHelperTextStyle(int helperTextStyle) {
        if (helperTextStyle < 0 || helperTextStyle > 1) {
            this.helperTextStyle = TEXTSTYLE_NORMAL;
        }
        this.helperTextStyle = helperTextStyle;
        invalidateComponent();
    }

    public int getHelperTextStyle() {
        return helperTextStyle;
    }

    public void setCursorPosition(int position) {
        editText.setSelection(position);
        invalidateComponent();
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
        invalidateComponent();
    }

    public EditText getEditText() {
        return editText;
    }

    public void setImageView(ImageView image) {
        this.image = image;
        invalidateComponent();
    }

    public ImageView getImageView() {
        return image;
    }

    public void setLineColor(int color) {
        lineColor = color;
        invalidateComponent();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineWidth(float sizeInDp) {
        this.lineWidth = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setDividerColor(int color) {
        this.dividerColor = color;
        invalidateComponent();
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setDividerWidth(float sizeInDp) {
        this.dividerWidth = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getDividerWidth() {
        return dividerWidth;
    }

    public void setFont(String font) {
        this.font = font;
        invalidateComponent();
    }

    public String getFont() {
        return font;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        invalidateComponent();
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setCornerRadius(float sizeInDp) {
        this.radius = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getCornerRadius() {
        return radius;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidateComponent();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeWidth(float sizeInDp) {
        this.strokeWidth = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setEditTextShape(int editTextShape) {
        this.editTextShape = editTextShape;
        invalidateComponent();
    }

    public int getEditTextShape() {
        return editTextShape;
    }

    public void setAllCaps(boolean allCaps) {
        this.allCaps = allCaps;
        invalidateComponent();
    }

    public boolean isAllCaps() {
        return allCaps;
    }

    public void setAutoStretch(boolean autoStretch) {
        this.autoStretch = autoStretch;
        invalidateComponent();
    }

    public boolean isAutoStretch() {
        return autoStretch;
    }

    public void setHelperFont(String helperFont) {
        this.helperFont = helperFont;
        invalidateComponent();
    }

    public String getHelperFont() {
        return helperFont;
    }

    public void setAllowPaste(boolean allowPaste) {
        this.allowPaste = allowPaste;
        invalidateComponent();
    }

    public boolean isAllowPaste() {
        return allowPaste;
    }

    public void setBottomPadding(float sizeInDp) {
        this.bottomPadding = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getBottomPadding() {
        return bottomPadding;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
        invalidateComponent();
    }

    public String getCurrency() {
        return currency;
    }

    public void setDisableComponent(boolean disableComponent) {
        this.disableComponent = disableComponent;
        invalidateComponent();
    }

    public boolean isDisableComponent() {
        return disableComponent;
    }

    public void setCursorVisible(boolean cursorVisible) {
        this.cursorVisible = cursorVisible;
        invalidateComponent();
    }

    public boolean isCursorVisible() {
        return cursorVisible;
    }

    public void setIcon(int icon) {
        this.icon = icon;
        invalidateComponent();
    }

    public int getIcon() {
        return icon;
    }

    public void setIconPosition(int iconPosition) {
        if (iconPosition > 4 || iconPosition < 1) {
            this.iconPosition = ICONPOSITION_LEFT;
        }
        this.iconPosition = iconPosition;
        invalidateComponent();
    }

    public int getIconPosition() {
        return iconPosition;
    }

    public void setHelperPosition(int helperPosition) {
        if (helperPosition > 4 || helperPosition < 1) {
            this.helperPosition = HELPERTEXTOSITION_TOP_LEFT;
        }
        this.helperPosition = helperPosition;
        invalidateComponent();
    }

    public int getHelperPosition() {
        return helperPosition;
    }

    public void setImageHeight(float sizeInDp) {
        this.imageHeight = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getImageHeight() {
        return imageHeight;
    }

    public void setImageWidth(float sizeInDp) {
        this.imageWidth = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getImageWidth() {
        return imageWidth;
    }

    public void setLeftPadding(float sizeInDp) {
        this.leftPadding = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getLeftPadding() {
        return leftPadding;
    }

    public void setLetterSpacing(float sizeInDp) {
        this.letterSpacing = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getLetterSpacing() {
        return letterSpacing;
    }

    public void setLineSpacing(float sizeInDp) {
        this.lineSpacing = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getLineSpacing() {
        return lineSpacing;
    }

    public void setRightPadding(float sizeInDp) {
        this.rightPadding = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getRightPadding() {
        return rightPadding;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
        invalidateComponent();
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
        invalidateComponent();
    }

    public boolean isShowLine() {
        return showLine;
    }

    public void setSpacing(float sizeInDp) {
        this.spacing = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getSpacing() {
        return spacing;
    }

    public void setTopPadding(float sizeInDp) {
        this.topPadding = DroidFunctions.dpToPx(sizeInDp);
        invalidateComponent();
    }

    public float getTopPadding() {
        return topPadding;
    }

    private AddTextChangedListener addTextChangedListener;
    private LongClickListener longClickListener;
    private KeyClickListener keyClickListener;
    private OnEditorActionListener onEditorActionListener;

    public interface AddTextChangedListener {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable editable);
    }

    public AddTextChangedListener getTextChangedListener() {
        return addTextChangedListener;
    }

    public void addTextChangedListener(AddTextChangedListener addTextChangedListener) {
        this.addTextChangedListener = addTextChangedListener;
    }

    public interface LongClickListener {
        void onLongClick(View view);
    }

    public LongClickListener getLongClickListener() {
        return longClickListener;
    }

    public void setOnLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface KeyClickListener {
        void onKey(View view, int i, KeyEvent keyEvent);
    }

    public KeyClickListener getKeyClickListener() {
        return keyClickListener;
    }

    public void setOnKeyClickListener(KeyClickListener keyClickListener) {
        this.keyClickListener = keyClickListener;
    }

    public interface OnEditorActionListener {
        void onEditorAction(TextView textView, int i, KeyEvent keyEvent);
    }

    public OnEditorActionListener getOnEditorActionListener() {
        return onEditorActionListener;
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        this.onEditorActionListener = onEditorActionListener;
    }
}
