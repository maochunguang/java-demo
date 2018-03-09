package cn.mcg.suanfa.sort;

import java.util.Arrays;

/**
 * Author: mac
 * Date: 2017/10/24
 * Description: 选择排序
 */
public class SelectionSort {
    private static void sort(int[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && (a[j] < a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 4, 78, 34, 12, 64, 1, 8};
        sort(arr);
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));
    }
}
