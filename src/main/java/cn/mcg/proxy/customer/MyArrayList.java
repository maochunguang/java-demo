package cn.mcg.proxy.customer;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maocg
 * 创建时间：2018/3/1
 * 描述：实现一个arrayList代理类
 */
public class MyArrayList implements InvocationHandler{
    final static List<String> list = new ArrayList<String>();

    public static List<String> getInstance(){
        return (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(),new MyArrayList());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(list, args);
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getClass());
    }
}
