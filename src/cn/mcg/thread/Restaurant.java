package cn.mcg.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: mac
 * Date: 2017/7/22
 * Description: todo
 */
public class Restaurant {
    Meal meal;
    WaitPerson waitPerson = new WaitPerson(this);
    ExecutorService exec = Executors.newCachedThreadPool();
    Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(waitPerson);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                    System.out.println("waitPerson get" + restaurant.meal);
                }
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(" waitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r) {
        this.restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(" chef interrupted");
        }
    }
}