package com.ivan.custom.treelistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivan.custom.treelistviewdemo.ExpandableTreeListView.ExpandableTreeAdapter;
import com.ivan.custom.treelistviewdemo.ExpandableTreeListView.ExpandableTreeListView;
import com.ivan.custom.treelistviewdemo.ExpandableTreeListView.TreeListNodeData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableTreeListView listView = (ExpandableTreeListView) findViewById(R.id.listView);

        ArrayList<TreeListNodeData> arrayList = new ArrayList<>();
        arrayList.add(new TreeListNodeData<>(-1, 0, "root"));
        arrayList.add(new TreeListNodeData<>(0, 1, "section 1"));
        arrayList.add(new TreeListNodeData<>(1, 2, "content 1-1"));
        arrayList.add(new TreeListNodeData<>(1, 3, "content 1-2"));
        arrayList.add(new TreeListNodeData<>(1, 4, "content 1-3"));
        arrayList.add(new TreeListNodeData<>(2, 5, "content 1-1-1"));
        arrayList.add(new TreeListNodeData<>(2, 6, "content 1-1-2"));
        arrayList.add(new TreeListNodeData<>(0, 7, "section 2"));
        arrayList.add(new TreeListNodeData<>(7, 8, "content 2-1"));
        arrayList.add(new TreeListNodeData<>(7, 9, "content 2-2"));
        arrayList.add(new TreeListNodeData<>(8, 10, "content 2-1-1"));
        arrayList.add(new TreeListNodeData<>(8, 11, "content 2-1-2"));

        ExpandableTreeAdapter adapter = new ExpandableTreeAdapter(arrayList) {
            @Override
            protected View getConvertView(TreeListNodeData data, View convertView, ViewGroup parent) {
                TextView tv;
                if (convertView != null) {
                    tv = (TextView) convertView;
                } else {
                    tv = new TextView(MainActivity.this);
                }
                String content = "";
                for (int i=0;i<data.getLevel();i++) {
                    content  += "      ";
                }
                tv.setText(content + data.getContents().toString());
                return tv;
            }

            @Override
            protected boolean showRoot() {
                return false;
            }
        };

        listView.setAdapter(adapter);

    }
}
