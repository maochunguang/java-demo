package cn.mcg.basics;

/**
 * @author maocg
 * 创建时间：2018/3/14
 * 描述：TODO
 */
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return true;
//    }

    @Override
    public int hashCode() {
        return 1;
    }
}
