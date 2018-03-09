package cn.mcg.suanfa.sort;

import java.util.Arrays;

/**
 * Author: mac
 * Date: 2017/10/22
 * Description: 插入排序实现
 */
public class InsertSort {
    public static void insertSort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && (a[j]<a[j-1]); j--) {
                swap(a, j, j-1);
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
        insertSort(arr, arr.length);
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));

    }

}
