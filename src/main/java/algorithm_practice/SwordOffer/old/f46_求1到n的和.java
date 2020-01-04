package algorithm_practice.SwordOffer.old;

/**
 * 题目：求1 + 2 + ···+ n，
 * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A ? B : C）
 *
 *
 * 这是一道C++序员的题目，书上居然给了4种方法：
 *  Ⅰ. 利用构造函数求解
 *  Ⅱ. 利用虚函数求解
 *  Ⅲ. 利用函数指针求解
 *  Ⅳ. 利用模板类型求解
 *
 *
 * 对于C艹只会cout和cin的我来说，只看懂了第一个：利用构造函数求解：
 *  然而Java中，当你new一个n那么大的Temp型数组，JVM并不会为你执行n次Temp的构造函数。 失败！
 *
 * Created by nibnait on 2016/10/2.
 */
public class f46_求1到n的和 {

    private static int cnt = 0;
    private static int sum = 0;

    static class Temp{
        public Temp() {
            cnt++;
            sum += cnt;
        }
        public static int getSum(){
            return sum;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        Temp[] temps = new Temp[n];
        System.out.println(Temp.getSum());    //失败。。
    }
}
