package cn.mcg.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maocg
 * 创建时间：2017/12/14
 * 描述：TODO
 */
public class ForEach {
    int num = 90000;

    @Test
    public void test3() {
        long start = System.currentTimeMillis();

        List<String> names = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            names.add("xiaoming" + i);
            names.add("laowang" + i);
        }
        List<String> toUpperCase = names.stream().
                map((String name) -> name.toUpperCase()).collect(Collectors.toList());

        System.out.println(toUpperCase.size() + " " + toUpperCase.get(0));
        System.out.println("test cost3 ==" + (System.currentTimeMillis() - start));

    }


    @Test
    public void test4() {
        long start = System.currentTimeMillis();

        List<String> names = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            names.add("xiaoming" + i);
            names.add("laowang" + i);
        }
        List<String> toUpperCase = new ArrayList<>();
        for (String val : names) {
            toUpperCase.add(val.toUpperCase());
        }
        System.out.println(toUpperCase.size() + " " + toUpperCase.get(0));
        System.out.println("test4 cost ==" + (System.currentTimeMillis() - start));

    }

    @Test
    public void test5() {
        long start = System.currentTimeMillis();
        List<String> names = new ArrayList<>();
        for (int i = 0; i <num; i++) {
            names.add("xiaoming" + i);
            names.add("laowang" + i);
        }
        List<String> toUpperCase = new ArrayList<>();
        names.parallelStream().forEach(j -> toUpperCase.add(j.toUpperCase()));
        System.out.println(toUpperCase.size() + " " + toUpperCase.get(0));
        System.out.println("test5 cost ==" + (System.currentTimeMillis() - start));
    }
}
