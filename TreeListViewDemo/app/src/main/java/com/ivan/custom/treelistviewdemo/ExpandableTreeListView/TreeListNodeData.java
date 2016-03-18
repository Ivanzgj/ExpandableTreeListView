package com.ivan.custom.treelistviewdemo.ExpandableTreeListView;

/**
 * ExpandableTreeListView的数据类
 * Created by Ivan on 16/2/13.
 */
public class TreeListNodeData<T> {

    private int parentId;
    private int id;
    private int level;
    private T contents;

    public TreeListNodeData(int pid, int id, T contents) {
        this.id = id;
        this.parentId = pid;
        this.contents = contents;
    }

    public int getParentId() {
        return parentId;
    }

    public int getId() {
        return id;
    }

    public T getContents() {
        return contents;
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }
}
