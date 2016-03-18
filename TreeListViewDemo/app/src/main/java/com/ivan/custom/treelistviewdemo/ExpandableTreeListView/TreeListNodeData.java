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

    /**
     * 层级列表中树的一个节点
     * @param pid 父节点的id
     * @param id 该节点的id
     * @param contents 该节点所代表的内容，其用于显示到列表中
     */
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

    /**
     * 获取该节点的层级
     * @return 该节点在列表中的层级，即其在树中的高度
     */
    public int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }
}
