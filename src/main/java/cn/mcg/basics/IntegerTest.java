package cn.mcg.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 描述：TODO
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Integer a1 = 1000;
        Integer b1 = 1000;
        System.out.println(a1==b1);
        System.out.println(a1.equals(b1));



        Integer a2 =  new Integer(1000);
        Integer b2 = new Integer(1000);
        System.out.println(a2==b2);
        System.out.println(a2.equals(b2));


        Integer a3 =  new Integer(10);
        Integer b3 = new Integer(10);
        System.out.println(a3==b3);
        System.out.println(a3.equals(b3));
        List<String> list = new ArrayList<>();

        for (int i=0;i<123;i++){
            list.add(i+"");
        }
        Collections.sort(list);
        System.out.println(list);
//        HashMap
    }
}
