package cn.mcg.suanfa.sort;

/**
 * Author: mac
 * Date: 2017/10/21
 * Description: 快速排序的实现
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 4, 78, 34, 12, 64, 1, 8};
        Integer[] b = {49, 38, 65, 97, 76, 13, 27, 4, 78, 34, 12, 64, 1, 8};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //快速排序
        quick(a);
//        Quick3way.sort(b);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
//            System.out.print(b[i].intValue()+"  ");
        }
    }

    private static void quick(int[] a) {
        if (a.length > 0) {
//            quickSort(a, 0, a.length - 1);
            quick3Sort(a, 0, a.length - 1);

        }
    }

    private static void quickSort(int[] a, int low, int high) {
        //如果不加这个判断递归会无法退出导致堆栈溢出异常
        if (low < high) {
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    /**
     * 三向切分的快速排序
     *
     * @param a
     * @param low
     * @param high
     */
    private static void quick3Sort(int[] a, int low, int high) {
        if (low >= high) return;
        int lt = low, gt = high;
        int temp = a[low];
        int i = low;
        while (i <= gt) {
            if (a[i] < temp) swap(a, lt++, i++);
            else if (a[i] > temp) swap(a, i, gt--);
            else i++;
        }
        quick3Sort(a, low, lt - 1);
        quick3Sort(a, gt + 1, high);
    }

    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];//基准元素
        while (low < high) {
            //找到比基准元素小的元素位置
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
}
