package cn.mcg.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author maocg
 * 创建时间：2018/3/1
 * 描述：TODO
 */
public class TestLock {
    @Test
    public void testDistributedLock(){
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadOfLock threadA = new ThreadOfLock(service);
            threadA.start();
        }

    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadOfLock threadA = new ThreadOfLock(service);
            threadA.start();
        }
    }
}
