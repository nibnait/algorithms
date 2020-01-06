package algorithm_practice.nowcoder.book.BinaryTree;

import common.util.SysOut;

import java.util.HashMap;

/**
 * 通过先序和中序数组生成后序数组
 *
 * 【思路】
 *  - 后序数组从后往前填写
 *  - 根据pre[1] 可以划分出左子树的先序、中序数组，和右子树的先序、中序数组
 *  - 然后 先递归根据右子树的先序、中序数组填写pos[]
 * Created by nibnait on 2016/9/20.
 */
public class getPosArray {

    public static void main(String[] args) {
        int[] pre = new int[]{1,2,4,5,3,6,7};
        int[] in = new int[]{4,2,5,1,6,3,7};
        SysOut.printArray(getPosArray(pre, in));
    }

    public static int[] getPosArray(int[] pre, int[] in){
        if (pre==null || in==null){
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        /*--------------------------------------------------------------*/
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len-1, in, 0, len-1, pos, len-1, map);
        /*--------------------------------------------------------------*/
        return pos;
    }

    /**
     *
     * @param pre   前序遍历数组
     * @param ps    前序遍历开始的位置
     * @param pe    前序遍历结束的位置
     * @param in    中序遍历数组
     * @param is    中序遍历开始的位置
     * @param ie    中序遍历结束的位置
     * @param pos   后序数组
     * @param si    后序数组填写到的位置
     * @param map   中序数组
     * @return
     */
    private static int setPos(int[] pre, int ps, int pe, int[] in, int is, int ie, int[] pos, int si, HashMap<Integer, Integer> map) {
        if (ps > pe){
            //开始位置大于结束位置，说明已经没有需要处理的元素了
            return si;
        }
        pos[si--] = pre[ps];    // 取前序遍历的第一个数字，就是当前的根结点
        int i = 0;
        try {
            i = map.get(pre[ps]);   // 在中序遍历的数组中找根结点的位置
        }catch (Exception e){
            throw new RuntimeException("前序/中序数组有问题");
        }
        si = setPos(pre, pe+1+i-ie, pe, in, i+1, ie, pos, si, map);
        return setPos(pre, ps+1, ps+i-is, in, is, i-1, pos, si, map);
    }

}
