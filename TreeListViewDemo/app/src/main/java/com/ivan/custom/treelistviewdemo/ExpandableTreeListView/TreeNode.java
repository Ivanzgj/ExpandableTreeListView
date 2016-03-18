package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

import java.util.ArrayList;

/**
 * 树的节点
 * Created by Ivan on 16/2/13.
 */
class TreeNode {

    private TreeListNodeData contents;
    private TreeNode parentNode;
    private ArrayList<TreeNode> childrenNodeList;
    private boolean expand = false;

    public TreeNode(TreeListNodeData contents) {
        setContents(contents);
    }

    public TreeListNodeData getContents() {
        return contents;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public ArrayList<TreeNode> getChildrenNodeList() {
        return childrenNodeList;
    }

    public void setContents(TreeListNodeData contents) {
        this.contents = contents;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public void expand() {
        this.expand = true;
    }

    public void close() {
        this.expand = false;
    }

    public boolean isExpand() {
        return expand;
    }

    public int getId() {
        return contents.getId();
    }

    public int getParentId() {
        return contents.getParentId();
    }

    public void addChild(TreeNode child) {
        if (childrenNodeList == null) {
            childrenNodeList = new ArrayList<>();
        }
        childrenNodeList.add(child);
        child.setParentNode(this);
        child.setLevel(getLevel()+1);
    }

    protected int getLevel() {
        return contents.getLevel();
    }

    protected void setLevel(int level) {
        contents.setLevel(level);
    }

}
