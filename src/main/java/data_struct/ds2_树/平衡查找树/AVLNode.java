package data_struct.ds2_树.平衡查找树;

import data_struct.ds2_树.二叉查找树.BinarySearchTreeNode;

/**
 *  平衡查找树结点
 *  Created by nibnait on 2019-07-25
 */
public class AVLNode extends BinarySearchTreeNode {

    /**
     * 此时我们将BSTNode中的N 理解为：该结点的高度
     * @param key
     * @param value
     */
    public AVLNode(Comparable key, Object value) {
        super(key, value);
    }
}
