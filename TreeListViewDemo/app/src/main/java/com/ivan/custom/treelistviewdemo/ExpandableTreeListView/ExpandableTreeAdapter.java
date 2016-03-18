package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

import android.view.View;
import android.view.ViewGroup;

import com.ivan.healthcare.healthcare_android.view.CustomListView.PinnedHeaderListView.PinnedHeaderListAdapter;

import java.util.ArrayList;

/**
 * 服务ExpandableTreeListView的数据源Adapter
 * Created by Ivan on 16/2/13.
 */
public abstract class ExpandableTreeAdapter extends PinnedHeaderListAdapter {

    private Tree listDataTree;
    private ArrayList<TreeNode> treeNodeArrayList;

    /**
     * 实例化一个ExpandableTreeAdapter，其数据源为基于listData生成的树
     * note：listData必须是有序的，其顺序是依次展开进入的，例如：父-一子-一子一孙-一子二孙-二子一孙-二子二孙-三子
     * @param listData 数据列表，注意该列表必须是有序的
     */
    public ExpandableTreeAdapter(ArrayList<TreeListNodeData> listData) {
        listDataTree = generateTree(listData);
    }

    private Tree generateTree(ArrayList<TreeListNodeData> listData) {
        Tree tree = new Tree(listData.get(0));
        TreeNode root = tree.getRoot();
        TreeNode parent = root;
        for (int i=1;i<listData.size();i++) {
            TreeNode child = new TreeNode(listData.get(i));
            if (child.getParentId() == parent.getId()) {
                tree.addChild(parent, child);
            } else if (child.getParentId() == root.getId()) {
                parent = root;
                tree.addChild(parent, child);
            } else {
                parent = parent.getChildrenNodeList().get(parent.getChildrenNodeList().size()-1);
                tree.addChild(parent, child);
            }
        }
        treeNodeArrayList = tree.searchDepthFirst();
        if (!showRoot()) {
            treeNodeArrayList.remove(0);
        }
        return tree;
    }

    @Override
    public boolean isSectionView(int positon) {
        return treeNodeArrayList.get(positon).getChildrenNodeList() != null;
    }

    @Override
    public int sectionOfItem(int position) {
        TreeNode parent = treeNodeArrayList.get(position).getParentNode();
        if (parent == null) {
            return 0;
        }
        if (parent.equals(listDataTree.getRoot())) {
            return position;
        }
        return treeNodeArrayList.indexOf(parent);
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
