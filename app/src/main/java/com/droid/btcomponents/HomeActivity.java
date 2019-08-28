package com.droid.btcomponents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.droid.elements.DroidFunctions;
import com.droid.elements.TextSpanner;
import com.droid.elements.UIButton;
import com.droid.elements.UILabel;

public class HomeActivity extends Activity {

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UIButton uibutton = findViewById(R.id.uibutton);
        uibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        UIButton uiedit = findViewById(R.id.uiinput);
        uiedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, UIEditActivity.class);
                startActivity(i);
            }
        });

        UILabel label = findViewById(R.id.uilabel);
        label.setSpanText(new TextSpanner("Text ").setTextColor(Color.RED).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("BG").setTextColor(Color.RED).setBackgroundColor(Color.BLACK).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner(" Size ").setTextColor(Color.BLACK).setTextSize(0.5f).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner(" Chip ").setRoundedBackgroundColor(Color.RED, Color.BLUE, DroidFunctions.dpToPx(this, 5)).build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Strike").setStrikeText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Underline").setUnderlineText().build());
        label.setSpanText(new TextSpanner(" ").build());
        label.setSpanText(new TextSpanner("Click").setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Clicked", Toast.LENGTH_LONG).show();
            }
        }).setTextColor(Color.BLUE).setUnderlineText().build());
        label.removeSpans();
    }
}
