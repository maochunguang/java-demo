package cn.mcg.suanfa;


/**
 * @author maocg
 */
public class BinarySearch {
    /**
     * 二分查找非递归
     * @param array
     * @param a
     * @return
     */
	public static int biSearch(int[] array, int a) {
		int low = 0;
		int high = array.length - 1;
		int mid;
		int times =0;
		while (low <= high) {
            System.out.println(times++);
            mid = (low + high) / 2;
			if (array[mid] > a) {
                high = mid - 1;
			} else if (array[mid] < a) {
				low = mid + 1;
			} else {
				return mid;
			}
//            AtomicInteger
		}
		return -1;
	}

    /**
     * 二分查找，递归实现
     * @param array
     * @param a
     * @param low
     * @param high
     * @return
     */
	public static int search(int[] array, int a, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (a == array[mid]) {
				return mid;
			} else if (a > array[mid]) {
				return search(array, a, mid + 1, high);
			} else {
				return search(array, a, low, mid - 1);
			}
		}
		return -1;
	}

    public static void main(String[] args) {
        int [] a = {1, 4, 8, 12, 13, 27, 34, 38 };
        int index = biSearch(a,1);
        System.out.println(a[index]);
    }
}
