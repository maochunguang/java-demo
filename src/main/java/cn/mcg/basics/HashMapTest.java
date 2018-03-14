package cn.mcg.basics;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 描述：TODO
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        for (int i=0;i<10;i++){
            map.put(new Student(),i);
            System.out.println(map.size());
        }
    }
}
