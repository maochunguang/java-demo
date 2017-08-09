package cn.mcg.thread;

import java.util.concurrent.Executors;

/**
 * Author: mac
 * Date: 2017/7/19
 * Description: todo
 */
public class TwoThreadAlive extends Thread {
    private volatile static boolean flag = true;
    public void run() {
        while (flag){
            for (int i = 0; i < 2; i++) {
                printMsg();
            }
            flag = false;

        }
    }

    public void printMsg() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name=" + name);
    }

    public static void main(String[] args) {
        TwoThreadAlive tt = new TwoThreadAlive();
        tt.setName("Thread");
        System.out.println("before start(), tt.isAlive()=" + tt.isAlive());
        tt.start();
        System.out.println("just after start(), tt.isAlive()=" + tt.isAlive());
        for (int i = 0; i < 3; i++) {
            tt.printMsg();
        }
        Executors.newFixedThreadPool(10);
        System.out.println("The end of main(), tt.isAlive()=" + tt.isAlive());
    }
}
