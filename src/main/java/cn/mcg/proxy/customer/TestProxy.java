package cn.mcg.proxy.customer;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author maocg
 * 创建时间：2017/12/15
 * 描述：TODO
 */
public class TestProxy {
    public static void main(String[] args) throws Exception{
        Person p =new MatchMaker().getInstance(new SingleMan());
        System.out.println(p.getClass());
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("./$Proxy0.class");
        os.write(data);
        os.close();

        p.marry();
    }
}
