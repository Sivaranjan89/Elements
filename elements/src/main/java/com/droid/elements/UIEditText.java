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
    private boolean shouldAnimate;
    private long animationDuration;

    public UIEditText(Context context) {
        super(context);
        mContext = context;
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

        TypedArray ca = mContext.obtainStyledAttributes(attrs, R.styleable.UICommon);
        text = ca.getString(R.styleable.UICommon_text);
        textSize = ca.getDimension(R.styleable.UICommon_textSize, DroidFunctions.dpToPx(mContext, 10));
        textStyle = ca.getInt(R.styleable.UICommon_textStyle, 0);
        textColor = ca.getColor(R.styleable.UICommon_textColor, Color.BLACK);
        font = ca.getString(R.styleable.UICommon_fontName);
        strokeColor = ca.getColor(R.styleable.UICommon_strokeColor, Color.BLACK);
        strokeWidth = ca.getDimension(R.styleable.UICommon_strokeWidth, 0);
        radius = ca.getDimension(R.styleable.UICommon_cornerRadius, 0);
        icon = ca.getResourceId(R.styleable.UICommon_icon, -1);
        shape = ca.getInt(R.styleable.UICommon_imageShape, 1);
        imageHeight = ca.getDimension(R.styleable.UICommon_imageHeight, -1);
        imageWidth = ca.getDimension(R.styleable.UICommon_imageWidth, -1);
        spacing = ca.getDimension(R.styleable.UICommon_spacing, 0);
        bgColor = ca.getColor(R.styleable.UICommon_backgroundColor, Color.TRANSPARENT);

        ca.recycle();

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UIEditText);
        hintText = ta.getString(R.styleable.UIEditText_hintText);
        helperText = ta.getString(R.styleable.UIEditText_helperText);
        helperTextSize = ta.getDimension(R.styleable.UIEditText_helperTextSize, DroidFunctions.dpToPx(mContext, 10));
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
        lineWidth = ta.getDimension(R.styleable.UIEditText_lineWidth, DroidFunctions.dpToPx(mContext, 1));
        dividerWidth = ta.getDimension(R.styleable.UIEditText_dividerWidth, DroidFunctions.dpToPx(mContext, 1));
        helperPosition = ta.getInt(R.styleable.UIEditText_helperTextPosition, 4);
        autoStretch = ta.getBoolean(R.styleable.UIEditText_autoStretch, false);
        singleLine = ta.getBoolean(R.styleable.UIEditText_singleLine, false);
        maxLines = ta.getInt(R.styleable.UIEditText_maxLines, 0);
        inputType = ta.getInt(R.styleable.UIEditText_inputType, 0);
        digits = ta.getString(R.styleable.UIEditText_digits);
        allowPaste = ta.getBoolean(R.styleable.UIEditText_allowPaste, true);
        allCaps = ta.getBoolean(R.styleable.UIEditText_allCaps, false);
        maxLength = ta.getInt(R.styleable.UIEditText_maxLength, 0);
        lines = ta.getInt(R.styleable.UIEditText_lines, 0);
        letterSpacing = ta.getDimension(R.styleable.UIEditText_letterSpacing, DroidFunctions.dpToPx(mContext, 0));
        lineSpacing = ta.getDimension(R.styleable.UIEditText_lineSpacing, DroidFunctions.dpToPx(mContext, 0));
        cursorVisible = ta.getBoolean(R.styleable.UIEditText_cursorVisible, true);
        imeActionLabel = ta.getString(R.styleable.UIEditText_imeActionLabel);
        imeOptions = ta.getInt(R.styleable.UIEditText_imeOptions, 6);
        ellipsize = ta.getInt(R.styleable.UIEditText_ellipsize, 0);
        currency = ta.getString(R.styleable.UIEditText_currencySymbol);
        shouldAnimate = ta.getBoolean(R.styleable.UIEditText_animate, false);
        animationDuration = ta.getInteger(R.styleable.UIEditText_animDuration, 1000);


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
                bgColor = ((ColorDrawable) getBackground()).getColor();
            }
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(mContext, "Please give color only for background or Use backgroundColor attribute", Toast.LENGTH_LONG).show();
            }
            bgColor = Color.TRANSPARENT;
        }
        drawEditText();

        if (shouldAnimate) {
            ScaleAnimation animation = new ScaleAnimation(0, 1, 1, 1);
            animation.setDuration(animationDuration);
            animation.setInterpolator(new AccelerateInterpolator());
            startAnimation(animation);
        }
    }

    private void drawEditText() {
        setOrientation(LinearLayout.VERTICAL);

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
        } else if (editTextShape == 3) {
            drawLine();
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

        if (helperPosition == 1 || helperPosition == 2) {
            addView(helper);
        }
        addView(main);
        if (editTextShape == 3 && showLine) {
            addView(line);
        }
        if (helperPosition == 3 || helperPosition == 4) {
            addView(helper);
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
        if (font != null && !font.trim().equalsIgnoreCase("")) {
            tf = Typeface.createFromAsset(mContext.getAssets(), font);
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
        editText.setBackground(null);
        editText.setId(EDITTEXT_ID);
        editText.setText(text);
        editText.setHint(hintText);
        editText.setTextSize(DroidFunctions.pxToDp(mContext, textSize));
        editText.setTypeface(tf, textStyle);
        editText.setTextColor(textColor);
        editText.setHintTextColor(hintTextColor);
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
        helper.setTextSize(DroidFunctions.pxToDp(mContext, helperTextSize));
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

    public void setText(String text) {
        editText.setText(text);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setHintText(String text) {
        editText.setHint(text);
    }

    public void setHelperText(String text) {
        helper.setText(text);
    }

    public void setTextColor(int color) {
        editText.setTextColor(color);
    }

    public void setHintTextColor(int color) {
        editText.setHintTextColor(color);
    }

    public void setHelperTextColor(int color) {
        helper.setTextColor(color);
    }

    public void setTextSize(float size) {
        editText.setTextSize(size);
    }

    public void setHelperTextSize(float size) {
        helper.setTextSize(size);
    }

    public void setCursorPosition(int position) {
        editText.setSelection(position);
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
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
