package nowcoder.book.BinaryTree;

import Standard.stdOut;

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
        stdOut.print(getPosArray(pre, in));
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

    private static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, HashMap<Integer, Integer> map) {
        if (pi > pj){
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj-nj+i+1, pj, n, i+1, nj, s, si, map);
        return setPos(p, pi+1, pi+i-ni, n, ni, i-1, s, si, map);
    }

}
