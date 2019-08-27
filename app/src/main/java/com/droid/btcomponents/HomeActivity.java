package com.droid.btcomponents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.droid.elements.UIButton;

public class HomeActivity extends Activity {

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
    }
}
