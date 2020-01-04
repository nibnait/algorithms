package algorithm_practice.nowcoder.b_2nd_Season.bc160803.src;

import common.datastruct.BinarySearch;

public class LongestLessSumSubArrayLength {

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


	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static int maxLengthForce(int[] arr, int k) {
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				if (sum <= k) {
					res = Math.max(res, j - i + 1);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int timeTime = 2000000;
		int len = 20;
		int maxValue = 20;
		for (int i = 0; i < timeTime; i++) {
			int[] arr = generateRandomArray(len, maxValue);
			int k = (int) (Math.random() * maxValue) - (maxValue / 3);
			if (maxLength(arr, k) != maxLengthForce(arr, k)) {
				System.out.println("what a fucking day!");
			}
		}

	}
}
