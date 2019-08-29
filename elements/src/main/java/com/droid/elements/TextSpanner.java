package com.droid.elements;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.EasyEditSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

public class TextSpanner {

    SpannableString span;

    String text;

    public TextSpanner(String text) {
        this.text = text;
        span = new SpannableString(text);
    }

    public TextSpanner setTextColor(int color) {
        span.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setBackgroundColor(int color) {
        span.setSpan(new BackgroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setRoundedBackgroundColor(int bgColor, int textColor, float radius) {
        span.setSpan(new RoundRectSpan(bgColor, textColor, radius), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setTextSizeRelative(float size) {
        span.setSpan(new RelativeSizeSpan(size), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setTextSizeAbsolute(float sizeInDp) {
        span.setSpan(new AbsoluteSizeSpan((int)sizeInDp, true), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setImage(final Context context, final int image, final float size) {
        span.setSpan(new DynamicDrawableSpan() {
            @Override
            public Drawable getDrawable() {
                Drawable drawable = context.getDrawable(image);
                drawable.setBounds(0, 0, (int) size, (int) size);
                return drawable;
            }
        }, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setClickToDeleteText() {
        span.setSpan(new EasyEditSpan(), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setBlur(float amount) {
        MaskFilter blurMask = new BlurMaskFilter(amount, BlurMaskFilter.Blur.NORMAL);
        span.setSpan(new MaskFilterSpan(blurMask), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setSubScript() {
        span.setSpan(new SubscriptSpan(), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setSuperScript() {
        span.setSpan(new SuperscriptSpan(), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setUrl(String url) {
        String newUrl = "";
        if (!url.contains("https://") || !url.contains("http://")) {
            newUrl = "https://";
        }

        if (!url.contains("www.")) {
            newUrl= newUrl + "www.";
        }

        newUrl = newUrl + url;

        span.setSpan(new URLSpan(newUrl), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setQuote(int color, float width, float gap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            span.setSpan(new QuoteSpan(color, (int)width, (int)gap), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return this;
    }

    public TextSpanner setStrikeText() {
        span.setSpan(new StrikethroughSpan(), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setUnderlineText() {
        span.setSpan(new UnderlineSpan(), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setBoldText() {
        span.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setItalicText() {
        span.setSpan(new StyleSpan(Typeface.ITALIC), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setBoldItalicText() {
        span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public TextSpanner setOnClick(final View.OnClickListener listener) {
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    public SpannableString build() {
        return span;
    }
}
