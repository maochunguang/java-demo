package cn.mcg.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maocg
 * 创建时间：2018/3/15
 * 描述：TODO
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 30,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue(15));

        for (int i = 0; i < 30; i++) {
            if(i== 10){
                TimeUnit.MILLISECONDS.sleep(10);
            }
            threadPool.execute(new Work());
        }
        TimeUnit.MILLISECONDS.sleep(200);


    }
}

class Work implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " >> is excuting");
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
