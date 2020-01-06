package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;

/**
 * 题目：输入两棵二叉树 A 和 B，判断 B 是不是 A 的子结构。
 *
 * 【解】：
 *      利用二叉树的神级遍历(空间复杂度：O(1)， 时间复杂度：O(h))
 *      转化成字符串匹配问题：KMP算法（时间：O(N)）
 *      总的时间复杂度：O(N)
 *
 * Created by nibnait on 2016/9/23.
 */
public class c18_树的子结构 {

    /**
     * 转化成字符串匹配的KMP问题
     * @param head1
     * @param head2
     * @return
     */
    public static boolean hasSubtree(BinaryTreeNode head1, BinaryTreeNode head2){
        if (head1 == head2){
            return true;
        }
        if (head2 == null){ //树为空
            return true;
        }
        if (head1 == null){ //A树为空
            return false;
        }
        String str1 = MorrisInTraversal(head1);
        String str2 = MorrisInTraversal(head2);
        return KMP_Match(str1, str2);
    }

    /**
     * KMP算法，若str1包含str2则返回true,否则返回false
     * @param str1
     * @param str2
     * @return
     */
    private static boolean KMP_Match(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int ps1 = 0;    //遍历str1用的指针
        int ps2 = 0;    //遍历str2用的指针
        int[] nextArr = getNextArr(s2);
        while (ps1<s1.length && ps2<s2.length){
            if (s1[ps1] == s2[ps2]){
                ps1++;
                ps2++;
            }else if (nextArr[ps2]==-1){
                ps1++;
            } else {
                ps2 = nextArr[ps2];
            }
        }
        return ps2==s2.length? true: false;
    }


    private static int[] getNextArr(char[] s2) {
        if (s2.length == 1){
            return new int[]{-1};
        }
        int[] nextArr = new int[s2.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;    //当前位置
        int cn = 0;     //最长匹配前缀的 下一个字符的位置
        while (pos < s2.length){
            if (s2[pos-1] == s2[cn]){
                nextArr[pos++] = nextArr[pos-1]+1;
                cn++;
            } else if (cn>0){
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }

    /**
     * Morris先序遍历
     * @param head
     * @return
     */
    private static String MorrisInTraversal(BinaryTreeNode head) {
        if (head == null){
            return null;
        }
        BinaryTreeNode cur1 = head;
        BinaryTreeNode cur2 = null;
        StringBuffer sb = new StringBuffer();
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right!=null && cur2.right!=cur1){
                    cur2 = cur2.right;  //一直遍历到左子树的最右结点
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    sb.append(cur1.value);
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                sb.append(cur1.value);
            }
            cur1 = cur1.right;
        }
        return sb.toString();
    }


    /**
     * 书上解法：递归
     *  先找到A中是否有和B的head相同的结点，然后递归匹配A.left与B.left、A.right与B.right
     *
     * @param head1 A树
     * @param head2 B树
     * @return
     */
    private static boolean hasSubtreeWithRecursion(BinaryTreeNode head1, BinaryTreeNode head2) {
        if (head1 == head2){
            return true;
        }
        if (head2 == null){ //树为空
            return true;
        }
        if (head1 == null){ //A树为空
            return false;
        }
        boolean result = false;
        if (head1.value == head2.value){    //找到了相同结点
            result = match(head1, head2);
        }
        if (!result){   //此结点未匹配出B树，继续考察 A.left
            result = hasSubtreeWithRecursion(head1.left, head2);
        }
        if (!result){   //左子树不行 换右子树
            result = hasSubtreeWithRecursion(head1.right, head2);
        }
        return result;
    }

    private static boolean match(BinaryTreeNode head1, BinaryTreeNode head2){
        if (head1 == head2){
            return true;
        }
        if (head2 == null){ //树为空
            return true;
        }
        if (head1 == null){ //A树为空
            return false;
        }
        if (head1.value == head2.value){
            return match(head1.left, head2.left) && match(head1.right, head2.right);
        }
        return false;
    }


    public static void main(String[] args) {
        BinaryTreeNode head1 = new BinaryTreeNode();
        head1.value = 8;
        head1.right = new BinaryTreeNode();
        head1.right.value = 7;
        head1.left = new BinaryTreeNode();
        head1.left.value = 8;
        head1.left.left = new BinaryTreeNode();
        head1.left.left.value = 9;
        head1.left.right = new BinaryTreeNode();
        head1.left.right.value = 2;
        head1.left.right.left = new BinaryTreeNode();
        head1.left.right.left.left = new BinaryTreeNode();
        head1.left.right.left.left.value = 4;
        head1.left.right.left.right = new BinaryTreeNode();
        head1.left.right.left.right.value = 7;
        BinaryTreeNode head2 = new BinaryTreeNode();
        head2.value = 8;
        head2.left = new BinaryTreeNode();
        head2.left.value = 9;
        head2.right = new BinaryTreeNode();
        head2.right.value = 2;
        System.out.println(hasSubtree(head1, head2));
        System.out.println(hasSubtree(head2, head1));
        System.out.println(hasSubtree(head1, head1.left));
        System.out.println(hasSubtree(head1, null));
        System.out.println(hasSubtree(null, head2));
        System.out.println(hasSubtree(null, null));

        System.out.println("--------hasSubtreeWithRecursion-------");
        System.out.println(hasSubtreeWithRecursion(head1, head2));
        System.out.println(hasSubtreeWithRecursion(head2, head1));
        System.out.println(hasSubtreeWithRecursion(head1, head1.left));
        System.out.println(hasSubtreeWithRecursion(head1, null));
        System.out.println(hasSubtreeWithRecursion(null, head2));
        System.out.println(hasSubtreeWithRecursion(null, null));
        
    }

}
