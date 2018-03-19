package cn.mcg.thread;

/**
 * @author maocg
 * 创建时间：2018/3/16
 * 描述：TODO
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception {
        MyThread thrd = new MyThread("test");
        thrd.setName("MyThread #1");
        showThreadStatus(thrd);
        thrd.start();
        Thread.sleep(50);
        showThreadStatus(thrd);
        thrd.waiting = false;
        Thread.sleep(50);
        showThreadStatus(thrd);
        thrd.notice();
        Thread.sleep(50);
        showThreadStatus(thrd);
        while (thrd.isAlive()) {
            System.out.println("alive");
        }
        showThreadStatus(thrd);
    }

    static void showThreadStatus(Thread thrd) {
        System.out.println(thrd.getName() + " 存活:" + thrd.isAlive() + " 状态:" + thrd.getState());
    }
}

class MyThread extends Thread {
    boolean waiting = true;
    boolean ready = false;
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " 启动");
        while (waiting)
            System.out.println("等待：" + waiting);
        System.out.println("等待...");
        startWait();
        try {
            Thread.sleep(1000);
        } catch (Exception exc) {
            System.out.println(name + " 中断。");
        }
        System.out.println(name + " 结束。");
    }

    synchronized void startWait() {
        try {
            while (!ready) wait();
        } catch (InterruptedException exc) {
            System.out.println("wait() 中断。");
        }
    }

    synchronized void notice() {
        ready = true;
        notify();
    }
}