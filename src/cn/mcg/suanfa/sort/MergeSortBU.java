package cn.mcg.suanfa.sort;

import java.util.Arrays;

/**
 * Author: mac
 * Date: 2017/10/24
 * Description: 自底向上的归并排序
 */
public class MergeSortBU {
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // 复制到aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // 合并回 a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

    }

    public static void mergeSort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 4, 78, 34, 12, 64, 1, 8};
        mergeSort(arr);
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));
    }
}
