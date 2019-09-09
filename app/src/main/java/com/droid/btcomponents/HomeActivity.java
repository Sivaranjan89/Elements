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
        ArrayList<String> data = new ArrayList<>();
        data.add("P1C1");
        data.add("P1C2");
        ArrayList<Bitmap> icons = new ArrayList<>();
        icons.add(DroidFunctions.imageResourceToBitmap(HomeActivity.this, R.mipmap.icon));
        icons.add(DroidFunctions.imageResourceToBitmap(HomeActivity.this, R.mipmap.icon));
        check.setData(data);
        check.setIcons(icons);


        /*LinkedHashMap details = new LinkedHashMap<>();
        List<String> children1 = new ArrayList<>();
        children1.add("P1C1");
        children1.add("P1C2");
        List<String> children2 = new ArrayList<>();
        children2.add("P2C1");
        children2.add("P2C2");
        List<String> children3 = new ArrayList<>();
        children3.add("P3C1");
        children3.add("P3C2");

        details.put("Parent 1", children1);
        details.put("Parent 2", children2);
        details.put("Parent 3", children3);

        UIExpandableListView listView = findViewById(R.id.exp);
        listView.setData(details);
        listView.designParentChildView(new UIExpandableListView.DesignParentChildView() {
            @Override
            public View designChildView(int parentPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, Object childText) {
                if (convertView == null) {
                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = layoutInflater.inflate(R.layout.list_child, null);
                }

                UILabel label = convertView.findViewById(R.id.child);
                label.setText((String) childText);

                return convertView;
            }

            @Override
            public View designParentView(int parentPosition, boolean isExpanded, View convertView, ViewGroup parent, List<String> parentTitles) {
                if (convertView == null) {
                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = layoutInflater.inflate(R.layout.list_parent, null);
                }

                UILabel label = convertView.findViewById(R.id.parent);
                label.setText(parentTitles.get(parentPosition));


                return convertView;
            }
        });*/
    }
}
