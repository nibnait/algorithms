package xiaozhao;

import java.util.Scanner;

/**
 *
 树的高度
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 65536KB；其他语言 589824KB
 题目描述：
 现在有一棵合法的二叉树，树的节点都是用数字表示，现在给定这棵树上所有的父子关系，求这棵树的高度
 输入
 输入的第一行表示节点的个数n（1<=n<=1000，节点的编号为0到n-1）组成，
 下面是n-1行，每行有两个整数，第一个数表示父节点的编号，第二个数表示子节点的编号
 输出
 输出树的高度，为一个整数

 样例输入
 5
 0 1
 0 2
 1 3
 1 4
 样例输出
 3
 * Created by nibnait on 2016/9/23.
 */
public class XiaoMi_01 {

    static class Node{
        public int value;
        public Node left = null;
        public Node right = null;

        public Node() {
        }

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.valueOf(sc.nextLine());
            int[][] arr = new int[n][2];
            for (int i = 0; i < n - 1; i++) {
                String string = sc.nextLine();
                String[] str = string.split(" ");
                int[] strArr = new int[]{Integer.valueOf(str[0]), Integer.valueOf(str[1])};
                arr[i][0] = strArr[0];
                arr[i][1] = strArr[1];
            }

            int cnt = 0;
            Node head = new Node(0);    //头节点
            Node root = head;   //逻辑头节点
            for (int i = 0; i < n - 1; i++) {
                root = getKey(head, arr[i][0]);
                if (arr[i] == arr[i-1]){
                    root.right = new Node(arr[i][1]);
                } else {
                    root.left = new Node(arr[i][1]);
                }
            }

            System.out.println(getHight(head));
        }
    }

    private static int getHight(Node head) {
        if (head == null){
            return 0;
        }
        int l =  getHight(head.left);
        int r = getHight(head.right);
        return l>r? l: r;
    }

    private static Node getKey(Node head, Integer key) {
        if (head == null){
            return null;
        }
        //二分查找
        if (head.value == key){
            return head;
        } else if (head.value > key){
            return getKey(head.right, key);
        } else {
            return getKey(head.left, key);
        }
    }
}
