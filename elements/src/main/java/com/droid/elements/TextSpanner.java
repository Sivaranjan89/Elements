package com.droid.elements;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
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

    public TextSpanner setTextSize(float size) {
        span.setSpan(new RelativeSizeSpan(size), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
