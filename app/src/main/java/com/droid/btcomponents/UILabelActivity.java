package com.droid.btcomponents;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.droid.elements.DroidFunctions;
import com.droid.elements.TextSpanner;
import com.droid.elements.UILabel;

public class UILabelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        UILabel label = findViewById(R.id.spanner);
        label.setSpanText(new TextSpanner("This").setTextColor(Color.RED).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("is").setStrikeText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Span").setUnderlineText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Text").setBackgroundColor(Color.RED).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("View").setTextSizeAbsolute(10).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner(" Component ").setRoundedBackgroundColor(Color.BLACK, Color.WHITE, DroidFunctions.dpToPx(this, 4)).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Bold").setBoldText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Italic").setItalicText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Clicked").setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UILabelActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            }
        }).setTextColor(Color.BLUE).setUnderlineText().build());
        label.setSpanText(new TextSpanner("\nThis is ").build());
        label.setSpanText(new TextSpanner("Image").setImage(this, R.mipmap.ic_launcher_round, DroidFunctions.dpToPx(this, 20)).build());
        label.setSpanText(new TextSpanner(" Android Icon \n").build());
        label.setSpanText(new TextSpanner("Click to Delete").build());
        label.setSpanText(new TextSpanner("\n").build());
        label.setSpanText(new TextSpanner("Blur").setBlur(3f).build());
        label.setSpanText(new TextSpanner("\n").build());
        label.setSpanText(new TextSpanner("This line is \n Quote Span").setQuote(Color.RED, 10, 10).build());
        label.setSpanText(new TextSpanner("\nThis is ").build());
        label.setSpanText(new TextSpanner("Subscript").setTextSizeRelative(0.4f).setSubScript().build());
        label.setSpanText(new TextSpanner("  This is ").build());
        label.setSpanText(new TextSpanner("Superscript").setTextSizeRelative(0.4f).setSuperScript().build());
        label.setSpanText(new TextSpanner("\n").build());
        label.setSpanText(new TextSpanner("Click for Google").setUrl("google.com").build());
    }
}
