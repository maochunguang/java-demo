package cn.mcg.suanfa;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author maocg
 * Date：2018/5/12
 * Description：TODO
 */
public class TestCollections {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            User user = new User();
//            user.setName(i + "");
        }


    }
    @Test
    public void testArray(){
        Integer[] integers = new Integer[1000000];
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            integers[i] = i;
        }
        System.out.println(System.currentTimeMillis() - start1);

    }

    @Test
    public void testArrayList(){
        List<Integer> userList = new ArrayList<>(1000000);
        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 1000000; j++) {
            userList.add(j);
        }
        System.out.println(System.currentTimeMillis() - start2);

    }

    @Test
    public void testSet() {
        Set<String> set = new TreeSet<>();

    }



}