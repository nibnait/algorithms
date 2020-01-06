package data_struct.树.二叉查找树;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;

/**
 *  二叉查找树 测试类
 *  Created by nibnait on 2019-07-25
 */
public class BinarySearchTreeTest extends TestCase {

    @Test
    public void testCase() {
        BinarySearchTree binarySearchTree = new BinarySearchTreeImpl();
        binarySearchTree.put(1, "1");
        binarySearchTree.put(2, "2");
        binarySearchTree.put(3, "3");

//        binarySearchTree.deleteMin();
//        binarySearchTree.deleteMax();
//        binarySearchTree.delete(1);
//        SysOut.println("BinarySearchTree.get(1) = " + binarySearchTree.get(1));

        SysOut.println("\nBinarySearchTree.size() = " + binarySearchTree.size());
        SysOut.println("BinarySearchTree.isEmpty() = " + binarySearchTree.isEmpty());
        SysOut.println("BinarySearchTree.contains(1) = " + binarySearchTree.contains(1));
        SysOut.println("BinarySearchTree.min() = " + binarySearchTree.min());
        SysOut.println("BinarySearchTree.max() = " + binarySearchTree.max());

        System.out.println("\nBinarySearchTree.floor(1) = " + binarySearchTree.floor(1));
        System.out.println("BinarySearchTree.floor(2) = " + binarySearchTree.floor(2));
        System.out.println("BinarySearchTree.floor(3) = " + binarySearchTree.floor(3));
        System.out.println("BinarySearchTree.floor(4) = " + binarySearchTree.floor(4));

        System.out.println("\nBinarySearchTree.ceiling(1) = " + binarySearchTree.ceiling(1));
        System.out.println("BinarySearchTree.ceiling(2) = " + binarySearchTree.ceiling(2));
        System.out.println("BinarySearchTree.ceiling(3) = " + binarySearchTree.ceiling(3));
        System.out.println("BinarySearchTree.ceiling(4) = " + binarySearchTree.ceiling(4));

        System.out.println("\nBinarySearchTree.rank(0) = " + binarySearchTree.rank(0));
        System.out.println("BinarySearchTree.rank(1) = " + binarySearchTree.rank(1));
        System.out.println("BinarySearchTree.rank(2) = " + binarySearchTree.rank(2));
        System.out.println("BinarySearchTree.rank(3) = " + binarySearchTree.rank(3));
        System.out.println("BinarySearchTree.rank(4) = " + binarySearchTree.rank(4));
        System.out.println("BinarySearchTree.rank(5) = " + binarySearchTree.rank(5));

        System.out.println("\nBinarySearchTree.select(0) = " + binarySearchTree.select(0));
        System.out.println("BinarySearchTree.select(1) = " + binarySearchTree.select(1));
        System.out.println("BinarySearchTree.select(2) = " + binarySearchTree.select(2));
        System.out.println("BinarySearchTree.select(3) = " + binarySearchTree.select(3));
        System.out.println("BinarySearchTree.select(4) = " + binarySearchTree.select(4));
        System.out.println("BinarySearchTree.select(5) = " + binarySearchTree.select(5));

        Iterable partKeys = binarySearchTree.keys(1, 2);
        if (partKeys != null) {
            Iterator iterator = partKeys.iterator();
            System.out.print("\npartKeys(): ");
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ", ");
            }
        }

        Iterable keys = binarySearchTree.keys();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            System.out.print("\nkeys(): ");
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ", ");
            }
        }

    }
}
