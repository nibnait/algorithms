package algorithm_practice.nowcoder.a_1st_Season.aa_Manacher_bfprt_KMP;

/**
 * 求 最长回文字串的长度
 * Created by nibnait on 2016/9/18.
 */
public class Manacher {

    public static void main(String[] args) {
//        String str = "tattarrattat";
        String str = "cabaddabac";
        System.out.println(maxLcpsLength(str));
    }

    private static int maxLcpsLength(String str) {
        if (str==null || str.length()==0){
            return 0;
        }
        char[] strArr = str.toCharArray();
        int length = 2*strArr.length+1;
        char[] charArr = new char[length];
        charArr[length-1] = '#';
        //字符串预处理
        int cnt = 0;
        for (int i = 0; i < length-1; ) {
            charArr[i++] = '#';
            charArr[i++] = strArr[cnt++];
        }
        int MaxLen = Integer.MIN_VALUE;
        int[] pArr = new int[length];   //i位置上，所能扩到的最大回文半径
        int maxRight = -1;      //记录回文半径所能扫到最右位置。（maxRight = i+pArr[i]-1）
        int index  = -1;        //当maxRight更新的时候，此时回文中心的位置
        for (int i = 0; i < length; i++) {
            if (i < maxRight){
                pArr[i] = Math.min(pArr[2*index-i], maxRight-i);    //看pArr[j]的长度
            } else {
                pArr[i] = 1;    //i在maxRight的右边，从1开始扩
            }
            //尝试扩展
            while (i+pArr[i]<length && i-pArr[i]>=0 && charArr[i-pArr[i]]==charArr[i+pArr[i]]){
                pArr[i]++;
            }
            //尝试更新maxRight和index
            if (i+pArr[i]>maxRight){
                maxRight = i+pArr[i]-1;
                index = i;
            }
            MaxLen = Math.max(MaxLen, pArr[i]);
        }
        return MaxLen-1;    //因为MaxLen为带有'#'的回文半径，所以不带'#'回文长度也就为MaxLen-1
    }

}
