package cn.mcg.datastruct;

import java.util.Map;

/**
 * @author maocg
 * Date：2018/4/6
 * Description：TODO
 */
public class MyMapTest {
    public static void main(String[] args) {
        Map<MyUser,String> map = new MyHashMap<>();
        map.put(new MyUser("aaa"), "1111");
        map.put(new MyUser("bbb"), "2222");
        map.put(new MyUser("ccc"), "3333");
        map.put(new MyUser("ddd"), "4444");

//        for (Map.Entry<String,String> m :map.entrySet()){
//            String key = m.getKey();
//            String value = m.getValue();
//            System.out.println("key=="+key+ " value=="+ value);
//        }
        System.out.println(map.size());
    }

}
class MyUser{
    String name;

    public MyUser(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}