package com.droid.btcomponents;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.droid.elements.TextSpanner;
import com.droid.elements.UILabel;

public class HomeActivity extends Activity {

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UILabel label = findViewById(R.id.label);
        label.setSpanText(new TextSpanner("This ").setTextColor(Color.RED).build());
        label.setSpanText(new TextSpanner("is ").setTextSizeAbsolute(10).build());
        label.setSpanText(new TextSpanner(" UILabel ").setRoundedBackgroundColor(Color.BLACK, Color.WHITE, 20).build());

    }
}
