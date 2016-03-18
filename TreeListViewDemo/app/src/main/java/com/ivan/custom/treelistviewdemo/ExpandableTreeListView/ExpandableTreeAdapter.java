package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * 服务ExpandableTreeListView的数据源Adapter
 * Created by Ivan on 16/2/13.
 */
public abstract class ExpandableTreeAdapter extends BaseAdapter {

    private Tree listDataTree;
    private ArrayList<TreeNode> treeNodeArrayList;

    /**
     * 实例化一个ExpandableTreeAdapter，其数据源为基于listData生成的树
     * @param listData 数据列表
     */
    public ExpandableTreeAdapter(ArrayList<TreeListNodeData> listData) {
        listDataTree = generateTree(listData);
    }

    private Tree generateTree(ArrayList<TreeListNodeData> listData) {
        Tree tree = new Tree(listData.get(0));

        for (int i=1;i<listData.size();i++) {
            TreeNode child = new TreeNode(listData.get(i));
            TreeNode parent = tree.findNode(child.getParentId());
            tree.addChild(parent, child);
        }

        treeNodeArrayList = tree.searchDepthFirst();
        if (!showRoot()) {
            treeNodeArrayList.remove(0);
        }

        return tree;
    }

    @Override
    public Object getItem(int position) {
        return treeNodeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return treeNodeArrayList.get(position).getId();
    }

    @Override
    public int getCount() {
        return treeNodeArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TreeNode node = treeNodeArrayList.get(position);
        TreeListNodeData nodeData = node.getContents();
        return getConvertView(nodeData, convertView, parent);
    }

    @Override
    public void notifyDataSetChanged() {
        treeNodeArrayList = listDataTree.searchDepthFirst();
        if (!showRoot()) {
            treeNodeArrayList.remove(0);
        }
        super.notifyDataSetChanged();
    }

    /**
     * 获取每一个item的view，用法类似于{@link #getView(int, View, ViewGroup)}方法
     * @see #getView(int, View, ViewGroup)
     * @param data 该item的数据
     * @param convertView 重用的view
     * @param parent listView引用
     * @return 应用data数据的view
     */
    protected abstract View getConvertView(TreeListNodeData data, View convertView, ViewGroup parent);

    /**
     * 是否显示根节点
     * @return 是否显示根节点
     */
    protected abstract boolean showRoot();
}
