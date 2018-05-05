package cn.mcg.basics;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 描述：TODO
 */
public class HashMapTest {
    private static Map keyMap = new HashMap();

    public static void main(String[] args) {
        Map map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(new Student(), i);
//            System.out.println(map.size());
        }

        HashMap map1 = new HashMap();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            new Runnable() {
                @Override
                public void run() {
                    String key = UUID.randomUUID().toString();
                    map1.put(key, key);
                    System.out.println(map1.size());
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }.run();
        }
        for (int i = 0; i < 100; i++) {
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(map1.get(random.nextInt(10000)));
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }.run();
        }
        System.out.println(map1.size());

        System.out.println(map1.size());
    }

    @Test
    public void testThreadsPutMap() {
        String test = "aaaa";
        String test1 = null;
        System.out.println(test + test1);
    }

    @Test
    public void forEachHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "111");
        map.put("bb", "222");
        map.put("cc", "333");
        for (Map.Entry<String, String> m : map.entrySet()) {
            String key = m.getKey();
            String value = m.getValue();
            System.out.println("key==" + key + " value==" + value);

        }

    }
}

class Work implements Runnable {
    private String name;

    public Work(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " >> is excuting");
        try {

            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}