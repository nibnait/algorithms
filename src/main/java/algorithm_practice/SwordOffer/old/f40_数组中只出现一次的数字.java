package algorithm_practice.SwordOffer.old;

/**
 * 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次，请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是 O(n)，空间复杂度是 O(1)。
 * 例如输入数组｛2, 4, 3, 6, 3, 2, 5 }，
 *     因为只有 4 、6 这两个数字只出现一次，其他数字都出现了两次，
 *     所以输出 4 和 6 。
 *
 * 【解】：
 *  1. 假如数组中只有1个数字只出现过一次，其他的都出现了两次，怎么找到它？
 *      我们可以用异或，因为异或：相同为0，不同为1
 *      所以我们可以从头到尾依次异或数组中的每一个数字，最终的异或结果刚好是只出现一次的那个数字。（那些成对出现的两个数字刚好在异或中抵消了）
 *  2. 那数组中又两个只出现一次的数字呢？
 *      我们可以把大数组分成两个子数组啊，每个子数组中都只有1个只出现一次的数字。
 *      这样分：
 *          - 还是从头到位依次异或数组中的每一个数字，最终得到的结果 也是这两个只出现一次的数字异或的结果。
 *          - 假设最终得到的异或结果为 0010，也就是说： 这两个数字的二进制表示时的第3位，一定是一个为1，一个为0
 *          - 因此 我们可以将数组中的所有数字按第3位是1的分成一组，第3位是0的分成一组。
 *          - 然后，再分别求出两个子数组中的那个只出现过一次的数字吧。
 *
 * Created by nibnait on 2016/10/1.
 */
public class f40_数组中只出现一次的数字 {

    public static void main(String[] args) {
        int[] data1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] result1 = findNumbersAppearanceOnce(data1);
        System.out.println(result1[0] + " " + result1[1]);
        int[] data2 = {4, 6};
        int[] result2 = findNumbersAppearanceOnce(data2);
        System.out.println(result2[0] + " " + result2[1]);
        int[] data3 = {4, 6, 1, 1, 1, 1};
        int[] result3 = findNumbersAppearanceOnce(data3);
        System.out.println(result3[0] + " " + result3[1]);
    }

    private static int[] findNumbersAppearanceOnce(int[] arr) {
        if (arr==null || arr.length<2){
            throw new IllegalArgumentException("Invalid args");
        }
        int[] result = new int[2];
        int xor = 0;
        for (int i : arr) {
            xor ^= i;
        }
        int indexOf1 = findFirstBitIs1(xor);
        //然后直接根据indexOf1分成两个数组，然后再分别求两个子数组的那一个数字也可以
        //下面我们完全可以一边分，一遍直接异或，异或的最终结果即为我们要找的那个数字！
        for (int i : arr){
            if (isBit1(i, indexOf1)){
                result[0] ^= i;
            } else {
                result[1] ^= i;
            }
        }
        return result;
    }

    /**
     * i的倒数第indexOf1位 是不是1
     * @param i
     * @param indexOf1
     * @return bool
     */
    private static boolean isBit1(int i, int indexOf1) {
        i = i>>indexOf1;
        return (i&1)==1;
    }

    /**
     * 返回xor的二进制表示是，第一个1出现在倒数第几位。
     * @param xor
     * @return
     */
    private static int findFirstBitIs1(int xor) {
        int index = 0;
        while (index<32 && (xor & 1)==0){
            index++;
            xor = xor>>1;
        }
        return index;
    }


}
