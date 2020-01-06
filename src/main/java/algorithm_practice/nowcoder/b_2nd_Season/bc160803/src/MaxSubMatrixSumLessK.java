package algorithm_practice.nowcoder.b_2nd_Season.bc160803.src;

import common.datastruct.BinarySearch;

public class MaxSubMatrixSumLessK {

	public static int maxSubMatrixSumLessThanK(int[][] m, int sum) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < m.length; i++) {
			int[] sumArr = new int[m[0].length];
			for (int j = i; j < m.length; j++) {
				for (int k = 0; k < m[0].length; k++) {
					sumArr[k] += m[j][k];
				}
				res = Math.max(res, (j - i + 1) * maxLength(sumArr, sum));
			}
		}
		return res;
	}

	public static int maxLength(int[] arr, int k) {
		int[] h = new int[arr.length + 1];
		int sum = 0;
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		int res = 0;
		int pre = 0;
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			pre = BinarySearch.search(h, sum - k);
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 0, 1 }, { 0, -2, 3 } };
		System.out.println(maxSubMatrixSumLessThanK(matrix, 2));
	}

}
