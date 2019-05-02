package StandardStruct;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nibnait on 2016/10/2.
 */
public class TreeNode {

    public int value;
    public List<TreeNode> children = new LinkedList<>();

    public TreeNode() {
    }

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
