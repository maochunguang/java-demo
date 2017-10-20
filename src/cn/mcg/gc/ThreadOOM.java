package cn.mcg.gc;

/**
 * Author: mac
 * Date: 2017/9/13
 * Description: todo
 */
public class ThreadOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Thread t1 = new Thread("thread t1") {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        sleep(1000);
                        byte[] allocation1 = new byte[i * _1MB/2];

                    }
                    System.out.println("new byte finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread("thread t2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(1500);
//                        System.out.println(this.getName() + "start");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t3 = new Thread("thread t3") {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(2000);
//                        System.out.println(this.getName() + "start");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }
}
