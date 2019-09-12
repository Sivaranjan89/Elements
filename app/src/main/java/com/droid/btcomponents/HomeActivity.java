package com.droid.btcomponents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.droid.elements.DroidFunctions;
import com.droid.elements.UIButton;
import com.droid.elements.UICheckBox;
import com.droid.elements.UILabel;
import com.droid.elements.UIRadioButton;
import com.droid.elements.expandablelist.UIExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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


        UICheckBox check = findViewById(R.id.check);
        UIRadioButton radio = findViewById(R.id.radio);
        ArrayList<String> data = new ArrayList<>();
        data.add("P1C1");
        data.add("P1C2");
        check.setData(data);
        //radio.setData(data);
    }
}
