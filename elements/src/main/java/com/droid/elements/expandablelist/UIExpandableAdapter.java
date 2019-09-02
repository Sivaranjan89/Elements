package com.droid.elements.expandablelist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

import com.droid.elements.DroidConstants;
import com.droid.elements.UILabel;

import java.util.HashMap;
import java.util.List;

public class UIExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitle;
    private HashMap<String, List<Object>> expandableListDetail;
    private UIExpandableListView.DesignParentChildView parentChildView;

    public UIExpandableAdapter(Context context, List<String> listTitle,
                               HashMap<String, List<Object>> expandableListDetail,
                               UIExpandableListView.DesignParentChildView parentChildView) {
        this.context = context;
        this.listTitle = listTitle;
        this.expandableListDetail = expandableListDetail;
        this.parentChildView = parentChildView;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.listTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        try {
            Object child = (Object) getChild(listPosition, expandedListPosition);
            convertView = parentChildView.designChildView(listPosition, expandedListPosition, isLastChild, convertView, parent, child);
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(context, "Error in UIExpandableAdapter-ChildView", Toast.LENGTH_SHORT).show();
            }
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.listTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.listTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        try {
            convertView = parentChildView.designParentView(listPosition, isExpanded, convertView, parent, listTitle);
        } catch (Exception e) {
            if (DroidConstants.showErrors) {
                e.printStackTrace();
                Toast.makeText(context, "Error in UIExpandableAdapter-ParentView", Toast.LENGTH_SHORT).show();
            }
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}


