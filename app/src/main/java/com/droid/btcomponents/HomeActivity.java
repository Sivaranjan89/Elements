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

        UIButton uitext = findViewById(R.id.uilabel);
        uitext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, UILabelActivity.class);
                startActivity(i);
            }
        });
    }
}
