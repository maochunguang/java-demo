package cn.mcg.suanfa.inteview;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 给定一个数组[-1,3,0,-3,-5,1,4,6] 求最大连续和
 */
public class MaxSum {
    /**
     * 普通实现
     *
     * @param array
     * @return
     */
    public int findMaxSumOfSubArray(int[] array) {

        if (array == null || (array.length == 1 && array[0] <= 0))
            return 0;

        int cur = array[0];
        int sum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cur < 0) {
                cur = 0;
            }
            cur = cur + array[i];
            if (sum <= cur) {
                sum = cur;
            }
        }
        return sum;
    }

    /**
     * 动态规划实现
     *
     * @param arr
     * @param n
     * @return
     */
    public int findMaxSumOfSubArray(int[] arr, int n) {
        int sum = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            sum = getMax(sum + arr[i], arr[i]);
            if (sum >= max) {
                max = sum;
            }
        }

        return max;
    }

    public int getMax(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] test = new int[]{-1, 3, 0, -3, -5, 1, 4, 6, -4, 10};
        MaxSum maxSum = new MaxSum();
        System.out.println(maxSum.findMaxSumOfSubArray(test));
        System.out.println(maxSum.findMaxSumOfSubArray(test, test.length));
    }
}
