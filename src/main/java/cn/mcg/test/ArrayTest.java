package cn.mcg.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: mac
 * Date: 2017/10/21
 * Description: todo
 */
public class ArrayTest {

    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args) {
        int[] a = { 3, 4, 5, 2, 1 };
        swap(a, 0, 2);
        System.out.print(Arrays.toString(a));
        System.out.print("hello world");

    }
    @Test
    public void testShortDefault(){
        List<String> list = new ArrayList<>();
        List<String> list1 = new LinkedList<>();
        Short id1 = new Short("0");

        id1 = new Short("");
        System.out.println(id1);
    }
}
