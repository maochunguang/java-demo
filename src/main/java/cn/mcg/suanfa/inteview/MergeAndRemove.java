package cn.mcg.suanfa.inteview;

import java.util.Arrays;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 1. 定义一个新数组，长度为两个数组长度之和，将两个数组都copy到新数组，然后排序。
 * <p>
 * 2. 给两个数组分别定义一个下标，最大长度是数组长度减一，按位循环比较两个数组，较小元素的放入新数组，
 * 下标加一（注意，较大元素对应的下标不加一），直到某一个下标超过数组长度时退出循环，此时较短数组已经全部放入新数组，
 * 较长数组还有部分剩余，最后将剩下的部分元素放入新数组，大功告成。
 */
public class MergeAndRemove {
    /**
     * 给定两个有序数组，[2,5,7,8,10],[3,5,8,9,11],排序并去重，要求算法复杂度越低越好
     *
     * @param a
     * @param b
     */
    public static void mergeAndRemoveRepeat(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else if (a[i] > b[j]) {
                result[k++] = b[j++];
            } else {
                j++;
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        System.out.println(Arrays.toString(result));
    }

    /**
     * 合并，不去重
     *
     * @param a
     * @param b
     */
    public static void merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        System.out.println(Arrays.toString(result));
    }


    /**
     * 给定多个有序数组，[1,2,3],[3,4,5],[7,8,9],输出为[1,2,3,4,5],[7,8,9]为连续的数组
     *
     * @param arr
     */
    public void mergeAndRange(int[]... arr) {
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 7, 8, 9, 10};
        int[] b = new int[]{3, 6, 8, 9, 11};
        mergeAndRemoveRepeat(a, b);
        merge(a, b);

        int[] arr = new int[]{2, 8, 1, 7, 4, 6, 9, 10, 11};
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] a, int low, int high) {
        if (high > low) {
            int mid = (low + high) / 2;
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, high, mid);
        }
    }


    public static void merge(int[] a, int low, int high, int mid) {
        int[] tempArr = new int[a.length];
        int index = low;
        int temp = low;
        int middle = mid + 1;
        //合并到原数组
        while (low <= mid && middle <=high) {
            if (a[low] <= a[middle]) {
                tempArr[index++] = a[low++];
            } else {
                tempArr[index++] = a[middle++];
            }
        }
        while (low <= mid) {
            tempArr[index++] = a[low++];
        }
        while (middle <= high) {
            tempArr[index++] = a[middle++];
        }
        while (temp<=high){
            a[temp] = tempArr[temp++];
        }
        System.out.println(Arrays.toString(a));

    }

    private static void merge1(int[] a, int left, int middle, int right) {
        int[] tmpArr = new int[a.length];
        int mid = middle+1; //右边的起始位置
        int tmp = left;
        int third = left;
        while(left<=middle && mid<=right){
            //从两个数组中选取较小的数放入中间数组
            if(a[left]<=a[mid]){
                tmpArr[third++] = a[left++];
            }else{
                tmpArr[third++] = a[mid++];
            }
        }
        //将剩余的部分放入中间数组
        while(left<=middle){
            tmpArr[third++] = a[left++];
        }
        while(mid<=right){
            tmpArr[third++] = a[mid++];
        }
        //将中间数组复制回原数组
        while(tmp<=right){
            a[tmp] = tmpArr[tmp++];
        }
    }


}
