package cn.mcg.suanfa.sort;

import java.util.Arrays;

/**
 * Author: mac
 * Date: 2017/10/24
 * Description: 归并排序
 */
public class MergeSort {
    private static void sort(int[] a, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        // 复制到aux[]
        int[] aux = new int[a.length];
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

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 4, 78, 34, 12, 64, 1, 8};
        sort(arr,0,arr.length-1);
        System.out.println("排序之后：");
        System.out.println(Arrays.toString(arr));
    }
}
