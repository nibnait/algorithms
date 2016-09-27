package xiaozhao;

import java.util.*;

/**
 * 电话号码分身
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 继MIUI8推出手机分身功能之后，MIUI9计划推出一个电话号码分身的功能：首先将电话号码中的每个数字加上8取个位，然后使用对应的大写字母代替
 * （"ZERO", "ONE", "TWO", "THREE", "FOUR",
 * "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"），
 * 然后随机打乱这些字母，所生成的字符串即为电话号码对应的分身。
 * 输入
 * 第一行是一个整数T（1<=T<=100)表示测试样例数；接下来T行，每行给定一个分身后的电话号码的分身（长度在3到10000之间）。
 * 输出
 * 输出T行，分别对应输入中每行字符串对应的分身前的最小电话号码（允许前导0）。
 * <p>
 * 样例输入
 * 4
 * EIGHT
 * ZEROTWOONE
 * OHWETENRTEO
 * OHEWTIEGTHENRTEO
 * 样例输出
 * 0
 * 234
 * 345
 * 0345
 * Created by nibnait on 2016/9/23.
 */
public class XiaoMi_03 {
    private static String[] strList = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    private static void getResult(String str) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer(str);
        HashMap<String, Integer> maps = new HashMap<String, Integer>();
        for (int i = 0; i <= 9; i++) {
            if (containIn(strList[i], sb)) {
                //删除对应字母
                for (int j = 0; j < strList[i].length(); j++) {
                    int p = sb.indexOf(strList[i].charAt(j) + "");
                    sb.delete(p, p + 1);
                }
                //保存字母
                maps.put(strList[i], 1);
            }
        }
        //打印字母并得到对应数字
        Iterator iter = maps.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            int n = getPosition(key);
            int result = ((10 + n) - 8) % 10;
            list.add(result);
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println();
    }

    private static int getPosition(String key) {
        for (int i = 0; i < strList.length; i++) {
            if (strList[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean containIn(String son, StringBuffer str) {
        for (int i = 0; i < son.length(); i++) {
            char c = son.charAt(i);
            if (str.indexOf(c + "") != -1) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String str = sc.nextLine();
                getResult(str);
            }
        }
    }
}