package cn.mcg.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    private int queueSize = 5;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);
       
    public static void main(String[] args)  {  
        Test test = new Test();
//        for (int i = 0; i < 5; i++) {
            Producer producer = test.new Producer();
            Consumer consumer = test.new Consumer();

            producer.start();
            consumer.start();
//        }

    }  
       
    class Consumer extends Thread{  
           
        @Override  
        public void run() {  
            consume();  
        }  
           
        private void consume() {  
            while(true){  
                try {
                    if(queue.size()< 0){

                    }
                    queue.take();  
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
       
    class Producer extends Thread{  
           
        @Override  
        public void run() {  
            produce();  
        }  
           
        private void produce() {  
            while(true){  
                try {  
                    queue.put(1);  
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                    TimeUnit.MILLISECONDS.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}  