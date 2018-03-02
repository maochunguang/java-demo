package cn.mcg.lock;

import java.util.Stack;

public class BlockQueue<T> {

    /**
     * push的锁
     */
    private final static Object pushLock = new Object();
    /**
     * pop
     */
    private final static Object popLock = new Object();
    // 存储数据
    private Stack<T> stack;

    public BlockQueue() {
        stack = new Stack<>();
    }

    public synchronized void push(T t) {
        int MAX_SIZE = 3;
        if (stack.size() >= MAX_SIZE) {
            // 超过了最大长度，那么就等待
            System.out.println(Thread.currentThread().getName()+"队列已满，请等待!");
            pushLock();
        }
        System.out.println(Thread.currentThread().getName()+"入队操作");
        stack.push(t);
        // 解开pop的锁
        popUnlock();
    }

    public T pop() {
        if (stack.size() == 0) {
            // 不能pop，那么就等待
            System.out.println(Thread.currentThread().getName()+"队列为空，请等待!");
            popLock();
        }
        System.out.println(Thread.currentThread().getName()+"出队操作");
        T t = stack.pop();
        // 解开push的锁
        pushUnlock();
        return t;
    }

    // push锁
    private void pushLock() {
        synchronized (pushLock) {
            try {
                pushLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 解开push锁
    private void pushUnlock() {
        synchronized (pushLock) {
            pushLock.notifyAll();
        }
    }

    // pop锁
    private void popLock() {
        synchronized (popLock) {
            try {
                popLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 解开pop锁
    private void popUnlock() {
        synchronized (popLock) {
            popLock.notify();
        }
    }

    public static void main(String[] args) {
        BlockQueue<String> queue = new BlockQueue<>();
        // 建N个线程，同时读

        for (int i=0;i<20;i++){
            new Runnable() {
                @Override
                public void run() {
                    queue.pop();
                    queue.push(Thread.currentThread().getName());
                }
            }.run();
        }
        for (int i=0;i<20;i++){
            new Runnable() {
                @Override
                public void run() {
                    queue.push(Thread.currentThread().getName());
                    queue.pop();
                }
            }.run();
        }
    }
}