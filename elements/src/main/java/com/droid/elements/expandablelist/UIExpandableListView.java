package com.droid.elements.expandablelist;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.droid.elements.DroidConstants;
import com.droid.elements.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UIExpandableListView extends ExpandableListView {

    private boolean singleGroupDisplay;

    private int lastPosition = -1;

    private Context mContext;
    private List<String> listTitle;
    private HashMap<String, List<Object>> expandableListDetail;
    private UIExpandableAdapter adapter;

    public UIExpandableListView(Context context) {
        super(context);

        singleGroupDisplay = false;
    }

    public UIExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.UIExpandableListView);
        singleGroupDisplay = ta.getBoolean(R.styleable.UIExpandableListView_singleGroupDisplay, false);

        invalidateComponent();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    private void invalidateComponent() {
        if (singleGroupDisplay) {
            setOnGroupExpandListener(new OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastPosition != -1
                            && groupPosition != lastPosition) {
                        collapseGroup(lastPosition);
                    }
                    lastPosition = groupPosition;
                }
            });
        }
    }

    private void invokeAdapter() {
        if (listTitle != null && expandableListDetail != null &&
                listTitle.size() > 0 && expandableListDetail.size() > 0 && parentChildView != null) {
            adapter = new UIExpandableAdapter(mContext, listTitle, expandableListDetail, parentChildView);
            this.setAdapter(adapter);
        } else {
            if (DroidConstants.showErrors) {
                Toast.makeText(mContext, "Please set Parent Child Values Using setData()",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setData(HashMap<String, List<Object>> parentChildData) {
        this.expandableListDetail = parentChildData;
        this.listTitle = new ArrayList<>(expandableListDetail.keySet());
        if (adapter != null) {
            refreshData();
        } else {
            invokeAdapter();
        }
    }

    public void refreshData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private DesignParentChildView parentChildView;
    private GroupExpandListener groupExpandListener;

    public interface DesignParentChildView {
        View designChildView(int parentPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent, Object child);

        View designParentView(int parentPosition, boolean isExpanded,
                             View convertView, ViewGroup parent, List<String> parentTitles);
    }

    public interface GroupExpandListener {

    }

    public void designParentChildView(DesignParentChildView parentChildView) {
        this.parentChildView = parentChildView;
        if (adapter == null) {
            invokeAdapter();
        }
    }

    public DesignParentChildView getParentChildView() {
        return this.parentChildView;
    }
}
