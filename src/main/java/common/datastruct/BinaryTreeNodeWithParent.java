package common.datastruct;

/**
 * Created by nibnait on 2016/10/3.
 */
public class BinaryTreeNodeWithParent {
    public int value;
    public BinaryTreeNodeWithParent left;
    public BinaryTreeNodeWithParent right;
    public BinaryTreeNodeWithParent parent;

    public BinaryTreeNodeWithParent() {
    }

    public BinaryTreeNodeWithParent(int data) {
        this.value = data;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
