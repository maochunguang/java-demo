package cn.mcg.lock;

import cn.mcg.redis.RedisDao;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author maocg
 * 创建时间：2018/3/9
 * 描述：TODO
 */
public class RedisIncrease {
    private static final Jedis jedis = RedisDao.getJedis();
    private static final String KEY = "test";

    public static int increase(String key){
        return jedis.incr(key).intValue();
    }
    public static synchronized int setNx(String key){
        int state = jedis.setnx(key,key).intValue();
        if(state == 1){
            return jedis.expire(key,2).intValue();
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Runnable() {
                @Override
                public void run() {
                    if(setNx(KEY)==1){
                        System.out.println(Thread.currentThread().getName()+ ":: 成功");
                    }else {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+ ":: 失败");
                    }
                }
            }.run();
        }


    }
}
