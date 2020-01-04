package algorithm_practice.nowcoder.b_2nd_Season.bg160831.src;

import common.datastruct.BinarySearch;

import java.util.HashMap;

public class LongestSubarrayLessSumAwesomeSolution {

	// So great, first time, my book need modify, add this solution into book
	public static int maxLengthAwesome(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] sums = new int[arr.length];
		HashMap<Integer, Integer> ends = new HashMap<Integer, Integer>();
		sums[arr.length - 1] = arr[arr.length - 1];
		ends.put(arr.length - 1, arr.length - 1);
		for (int i = arr.length - 2; i >= 0; i--) {
			if (sums[i + 1] < 0) {
				sums[i] = arr[i] + sums[i + 1];
				ends.put(i, ends.get(i + 1));
			} else {
				sums[i] = arr[i];
				ends.put(i, i);
			}
		}
		int end = 0;
		int sum = 0;
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			while (end < arr.length && sum + sums[end] <= k) {
				sum += sums[end];
				end = ends.get(end) + 1;
			}
			sum -= end > i ? arr[i] : 0;
			res = Math.max(res, end - i);
			end = Math.max(end, i + 1);
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

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			int[] arr = generateRandomArray(10, 20);
			int k = (int) (Math.random() * 20) - 5;
			if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
				System.out.println("oops!");
			}
		}

	}

}
