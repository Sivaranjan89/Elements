package com.droid.btcomponents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.droid.elements.DroidFunctions;
import com.droid.elements.TextSpanner;
import com.droid.elements.UIButton;
import com.droid.elements.UIEditText;
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

        UIButton uitext = findViewById(R.id.uilabel);
        uitext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, UILabelActivity.class);
                startActivity(i);
            }
        });


        /*RelativeLayout main = new RelativeLayout(this);
        main.setBackgroundColor(Color.WHITE);
        main.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        UIEditText et = new UIEditText(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        et.setLayoutParams(params);
        et.setText("Sivaranjan");
        et.setTextSize(15);
        et.setTextColor(Color.BLACK);
        et.setHelperText("Helper");
        et.setHelperTextSize(10);
        et.setHelperPosition(1);
        et.setHelperTextColor(Color.RED);
        et.setStrokeWidth(2);
        et.setCornerRadius(4);

        setContentView(main);


        main.addView(et);*/
    }
}
