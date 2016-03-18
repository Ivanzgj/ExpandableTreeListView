package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

import java.util.ArrayList;

/**
 * 树数据结构类
 * Created by Ivan on 16/2/13.
 */
public class Tree {

    private TreeNode root;

    public Tree(TreeListNodeData root) {
        setRoot(root);
    }

    public void setRoot(TreeListNodeData root) {
        this.root = new TreeNode(root);
        root.setLevel(0);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void addChild(TreeNode parent, TreeNode child) {
        parent.addChild(child);
    }

    /**
     * 深度优先搜索获得树的节点有序列表
     * @return 按照深度优先排序的节点列表
     */
    public ArrayList<TreeNode> searchDepthFirst() {
        ArrayList<TreeNode> results = new ArrayList<>();
        results.add(root);
        searchDepthFirst(root, results);
        return results;
    }

    private void searchDepthFirst(TreeNode node, ArrayList<TreeNode> collection) {
        if (!node.isExpand()) {
            return;
        }
        ArrayList<TreeNode> children = node.getChildrenNodeList();
        if (children == null) {
            return;
        }
        for (int i=0;i<children.size();i++) {
            TreeNode child = children.get(i);
            collection.add(child);
            searchDepthFirst(child, collection);
        }
    }

}
