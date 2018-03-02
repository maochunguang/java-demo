package cn.mcg.lock;

import java.util.Vector;

/**
 * @author maocg
 * 创建时间：2018/3/2
 * 描述：自定义实现一个阻塞队列，当取元素时，size=0，阻塞取元素的线程，让写的线程进行
 */
public class MyBlockingQueue {
    private static Vector<String> queue = new Vector<>();

    public static void setQueue(String value){
        synchronized (queue){
            while (queue.size()==10){
                queue.notify();
            }
            queue.add(value);
        }
    }

    public static String getQueue(){
        synchronized (queue){
            while (queue.size()==0){
                queue.notify();
            }
            return queue.lastElement();
        }
    }


}
