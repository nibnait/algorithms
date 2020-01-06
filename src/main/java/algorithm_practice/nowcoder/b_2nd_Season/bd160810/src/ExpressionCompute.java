package algorithm_practice.nowcoder.b_2nd_Season.bd160810.src;

import java.util.LinkedList;

public class ExpressionCompute {

	/**
	 *
	 * @param str
	 * @return
	 */
	public static int getValue(String str) {
		return value(str.toCharArray(), 0)[0];
	}

	/**
	 * 主递归：
	 * @param str
	 * @param i 当前递归函数 开始处理的位置
	 * @return
	 */
	public static int[] value(char[] str, int i) {
		LinkedList<String> que = new LinkedList<String>();	//que中没有“(”、“)”,放的就是数字和运算符
		int pre = 0;
		int[] bra = null;
		while (i < str.length && str[i] != ')') {		//
			if (str[i] >= '0' && str[i] <= '9') {	//遇到数字，
				pre = pre * 10 + str[i++] - '0';		//则收集数字，累起来
			} else if (str[i] != '(') {			//遇到“+”、“-”、“*”、“/”的时候
				addNum(que, pre);						//将收集到的数字放到que中
				que.addLast(String.valueOf(str[i++]));	//然后将数字后面的符号（str[i]）放到que中
				pre = 0;								//pre清零
			} else {								//当前字符为“(”时
				bra = value(str, i + 1);
				pre = bra[0];							//bra[0]为：当前表达式的结果
				i = bra[1] + 1;							//bra[1]为：当前处理到str的第i号元素
			}
		}
		addNum(que, pre);
		return new int[] { getNum(que), i };		//返回本次递归计算出的表达式的值，和本次递归处理到了str的位置
	}

	/**
	 * 将num放到que中
	 * @param que
	 * @param num
	 */
	public static void addNum(LinkedList<String> que, int num) {
		if (!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();
			if (top.equals("+") || top.equals("-")) {
				que.addLast(top);
			} else {	//当往队列中放“*”、“/”号时，直接将两个数合成一个数！
				cur = Integer.valueOf(que.pollLast());
				num = top.equals("*") ? (cur * num) : (cur / num);
			}
		}
		que.addLast(String.valueOf(num));
	}

	/**
	 * 简单表达式求值
	 * @param que	存放一个不含任何“(”、“)”的表达式
	 * @return
	 */
	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while (!que.isEmpty()) {
			cur = que.pollFirst();
			if (cur.equals("+")) {
				add = true;
			} else if (cur.equals("-")) {
				add = false;
			} else {
				num = Integer.valueOf(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String exp = "48*((70-65)-43)+8*1";
		System.out.println(getValue(exp));

		exp = "4*(6+78)+53-9/2+45*8";
		System.out.println(getValue(exp));

		exp = "10-5*3";
		System.out.println(getValue(exp));

		exp = "-3*4";
		System.out.println(getValue(exp));

		exp = "3+1*4";
		System.out.println(getValue(exp));

	}

}
