package cc.tianbin.algorithmzuo.b_体系学习班.c0204_树型DP;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.datastruct.TreeNode;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2022/10/31
 */
public class Code09_MaxHappy {

    private static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;

        Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);

        TreeNode result = maxHappy(boss);

        Assert.assertEquals(result, maxHappy1(boss));
    }

    /*
        派对的最大快乐值
         员工信息的定义如下:
        class Employee {
            public int happy; // 这名员工可以带来的快乐值
            List<Employee> subordinates; // 这名员工有哪些直接下级
        }
        公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树
        树的头节点是公司唯一的老板，除老板之外的每个员工都有唯一的直接上级
        叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外每个员工都有一个或多个直接下级
        这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
        1.如果某个员工来了，那么这个员工的所有直接下级都不能来
        2.派对的整体快乐值是所有到场员工快乐值的累加
        3.你的目标是让派对的整体快乐值尽量大
        给定一棵多叉树的头节点boss，请返回派对的最大快乐值。

        给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
    */
    public TreeNode maxHappy(Employee boss) {
        return null;
    }

    @AllArgsConstructor
    private class Info {

    }

    //-------------------------- 比较器 --------------------------//
    private int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }
}
