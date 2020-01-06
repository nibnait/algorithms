package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个整数 n，求以 1 ... n 为结点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-19
 */
public class M096_不同的二叉搜索树的数量 extends TestCase {

    /*
    1 ... n，一共有多少种不同的二叉搜索树？
    假设以i为根结点时，共有f(i)种BST
    则 1 ... n共有 G(n) = f(1) + f(2) + ... + f(n)种BST

    又因为f(i) = G(i-1) * G(n-i)
    所以：G(n) = G(0) * G(n-1) + G(1) * G(n-2) + ... + G(n-1) * G(0)

     */
    public int numTrees(int N) {
        if (N < 1) {
            return 0;
        }
        int[] G = new int[N + 1];
        G[0] = 1;
        G[1] = 1;

        for (int n = 2; n < N + 1; n++) {
            int sum = 0; //f(1) 到 f(n)的和
            for (int i = 1; i < n + 1; i++) {
                sum += G[i-1] * G[n-i];
            }
            G[n] = sum;
        }

        return G[N];
    }

    @Test
    public void testCase() {
        System.out.println(numTrees(0));
        System.out.println(numTrees(1));
        System.out.println(numTrees(2));
        System.out.println(numTrees(3));
        System.out.println(numTrees(10));
    }
}
