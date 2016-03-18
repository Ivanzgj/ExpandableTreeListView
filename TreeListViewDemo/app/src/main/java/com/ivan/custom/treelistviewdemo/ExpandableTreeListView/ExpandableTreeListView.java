package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 可折叠展开的listView
 * Created by Ivan on 16/2/13.
 */
public class ExpandableTreeListView extends ListView {

    private ExpandableTreeAdapter expandableTreeAdapter;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ExpandableTreeListView(Context context) {
        super(context);
        initView();
    }

    public ExpandableTreeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(parent, view, position, id);
                }
                if (expandableTreeAdapter == null) {
                    return;
                }
                update(position);
            }
        });
    }

    private void update(int position) {
        TreeNode node = (TreeNode) expandableTreeAdapter.getItem(position);
        if (node.getChildrenNodeList() == null) {
            return;
        }
        if (node.isExpand()) {
            node.close();
        } else {
            node.expand();
        }
        expandableTreeAdapter.notifyDataSetChanged();
    }

    public void setOnExpandableListItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof ExpandableTreeAdapter) {
            this.expandableTreeAdapter = (ExpandableTreeAdapter) adapter;
        }
    }
}
