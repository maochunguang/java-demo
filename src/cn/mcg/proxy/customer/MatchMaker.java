package cn.mcg.proxy.customer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author maocg
 * 创建时间：2017/12/15
 * 描述：媒婆，红娘
 */
public class MatchMaker implements InvocationHandler{
    private Person target;
    public Person getInstance(Person target) {
        this.target = target;
        Class clazz = target.getClass();

        return (Person) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start invoke");
        this.target.marry();
        System.out.println("end invoke");
        return null;
    }
}
