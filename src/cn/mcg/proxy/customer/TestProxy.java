package cn.mcg.proxy.customer;

/**
 * @author maocg
 * 创建时间：2017/12/15
 * 描述：TODO
 */
public class TestProxy {
    public static void main(String[] args) {
        Person p =new MatchMaker().getInstance(new SingleMan());
        System.out.println(p.getClass());
        p.marry();
    }
}
