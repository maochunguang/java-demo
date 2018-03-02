package cn.mcg.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLock {
//    public synchronized void get(Thread thread) {
//        System.out.println("start time:"+System.currentTimeMillis());
//        for(int i=0; i<5; i++){
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(thread.getName() + ":正在进行读操作……");
//        }
//        System.out.println(thread.getName() + ":读操作完毕！");
//        System.out.println("end time:"+System.currentTimeMillis());
//    }
    //716-493= 223ms


    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //1046-928=74ms
    public void get(Thread thread) {
        lock.readLock().lock();
        try{
            System.out.println("start time:"+System.currentTimeMillis());
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
            System.out.println("end time:"+System.currentTimeMillis());
        }finally{
            lock.readLock().unlock();
        }
    }

    // 读操作
    public void readFile(Thread thread){
        lock.readLock().lock();
        boolean readLock = lock.isWriteLocked();
        if(!readLock){
            System.out.println("当前为读锁！");
        }
        try{
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
        }finally{
            System.out.println("释放读锁！");
            lock.readLock().unlock();
        }
    }
    // 写操作
    public void writeFile(Thread thread){
        lock.writeLock().lock();
        boolean writeLock = lock.isWriteLocked();
        if(writeLock){
            System.out.println("当前为写锁！");
        }
        try{
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行写操作……");
            }
            System.out.println(thread.getName() + ":写操作完毕！");
        }finally{
            System.out.println("释放写锁！");
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {  
        final ReadAndWriteLock lock = new ReadAndWriteLock();  
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.get(Thread.currentThread());
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.get(Thread.currentThread());
//            }
//        }).start();
        // 建N个线程，同时读
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                lock.readFile(Thread.currentThread());
            }
        });
        // 建N个线程，同时写
        ExecutorService service1 = Executors.newCachedThreadPool();
        service1.execute(new Runnable() {
            @Override
            public void run() {
                lock.writeFile(Thread.currentThread());
            }
        });
        ExecutorService service2 = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                lock.writeFile(Thread.currentThread());
            }
        });
    }  
}  