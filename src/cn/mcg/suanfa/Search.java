package cn.mcg.suanfa;

import org.junit.Test;

public class Search {
	@Test
	public void halfSearch() {
		int [] array = new int[]{2,3,5,6,7,8,9,13};
		int a = 13;
		System.out.println(biSearch(array, a));
	}

	public int biSearch(int[] array, int a) {
		int low = 0;
		int high = array.length - 1;
		int mid;
		int times = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			times ++;
			System.out.printf("执行次数== %d", times);
			System.out.println();

			if (array[mid] == a) {
				return mid;
			} else if (array[mid] < a) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	public int sort(int[] array, int a, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (a == array[mid]) {
				return mid;
			} else if (a > array[mid]) {
				return sort(array, a, mid + 1, high);
			} else {
				return sort(array, a, low, mid - 1);
			}
		}
		return -1;
	}
}
