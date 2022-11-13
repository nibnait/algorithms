package algorithmzuo.b_体系学习班.c0101_排序;

import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by nibnait on 2022/10/22
 */
@Slf4j
public class Code06_2_SortArrayDistanceLessK {

	@Test
	public void loopTestCase() {
		for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
			testCase();
		}
	}

	@Test
	public void testCase() {
		int maxSize = 100;
		int maxValue = 100;
		int k = SysRandom.generateNaturalNum(maxSize);
		int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
		int[] copyArr = Arrays.copyOf(arr, arr.length);
		sortArrayDistanceLessK(arr, k);

		if (!CompareUtils.isSortAsc(arr)) {
			log.error("原数组: {}", copyArr);
			log.error("排序后: {}", arr);
			throw new RuntimeException();
		}
	}

	/**
	 * 已知一个几乎有序的数组。请选择一个合适的排序策略，对这个数组进行排序。
	 * 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
	 * k相对于数组长度来说是比较小的。
	 */
	private void sortArrayDistanceLessK(int[] arr, int k) {
		if (k == 0) {
			return;
		}

		// 默认小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();

		int index = 0;
		int heapSize = Math.min(k, arr.length - 1);
		while (index <= heapSize) {
			heap.add(arr[index++]);
		}

		int i = 0;
		while (index < arr.length) {
			heap.add(arr[index++]);
			arr[i++] = heap.poll();
		}

		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}

	//-------------------------- 比较器 --------------------------//
	private static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		// 先排个序
		Arrays.sort(arr);
		// 然后开始随意交换，但是保证每个数距离不超过K
		// swap[i] == true, 表示i位置已经参与过交换
		// swap[i] == false, 表示i位置没有参与过交换
		boolean[] isSwap = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
			if (!isSwap[i] && !isSwap[j]) {
				isSwap[i] = true;
				isSwap[j] = true;
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		return arr;
	}
}