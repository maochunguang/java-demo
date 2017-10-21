package cn.mcg.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: mac
 * Date: 2017/10/21
 * Description: todo
 */
public class ArrayTest {
    @Test
    public void testSwapArray(){
        int [] a= {3,4,5,2,1};
        swap(a,0,2);
        System.out.print(Arrays.toString(a));
    }
    private void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }}
