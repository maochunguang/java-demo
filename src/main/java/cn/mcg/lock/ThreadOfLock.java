package cn.mcg.lock;

/**
 * @author maocg
 * 创建时间：2018/3/1
 * 描述：TODO
 */
public class ThreadOfLock extends Thread {
    private Service service;

    public ThreadOfLock(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
