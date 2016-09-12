package others;

/**
 * 数组中出现次数超过一半的数字
 *
 * Created by nibnait on 2016/9/11.
 */
public class MoreThanHalfNum_Solution {
/*

    问题：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

    分析：解题方法有很多，我们最容易想到的方法就是将数组中的数做个统计，计算每个数出现的次数，看看其中有没有超过数组中出现次数超过一半的数字。我们再深入分析一下，如果存在这样的一个数字，那么它一定只有一个。我们将数组中两个不同的数进行去除，最后剩下的那个数就是数组中出现次数超过一半的数字，根据这个想法我们可以用下面的解法。

    解法：声明两个变量num（记录数组中出现次数超过一半的数字）和cur（记录num出现的次数）。初始时，num指向数组中的第一个元素，cur的值为1。开始遍历，若cur的值为0，将num置为当前数组的值，进行下一个位置的遍历；若当前位置的值和num相等，cur++；否则cur--。直到遍历到数组的最后一个位置。最后我们要进行验证，因为数组中可能不存在出现次数超过一半的数字。验证的方式也很简单，遍历数组，判断数组中的数和num的值相等的个数是否超过数组长度的一半，若超过则该数为num；否则数组中不存在出现次数超过一半的数字，返回0。

*/

    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 0 || array == null){
            return 0;
        }
        int cur = 1;
        int num = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cur == 0) {
                num = array[i];
                cur++;
                continue;
            }
            if (array[i] == num) {
                cur++;
            } else {
                cur--;
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (num == array[i]) {
                sum++;
            }
        }
        if (sum <= array.length / 2)
            num = 0;
        return num;
    }
}
