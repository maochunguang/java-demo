package cn.mcg.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * @author maocg
 * 创建时间：2018/3/1
 * 描述：TODO
 */
public class DistributedLockOfRedis {
    private final JedisPool jedisPool;

    public DistributedLockOfRedis(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String lockWithTimeOut(String lockName, long acquireTimeout, long timeout) {
        Jedis conn = null;
        String reInentifiler = null;
        try {
            conn = jedisPool.getResource();
            String lockKey = "lock:" + lockName;
            String identifier = UUID.randomUUID().toString();
            int lockExpire = (int) (timeout / 1000);
            long end = System.currentTimeMillis() + acquireTimeout;

            while (System.currentTimeMillis() < end) {
                if (conn.setnx(lockKey, identifier) == 1) {
                    conn.expire(lockKey, lockExpire);
                    reInentifiler = identifier;
                    return reInentifiler;
                }
                if (conn.ttl(lockKey) == -1) {
                    conn.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return reInentifiler;
    }


    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:"+lockName;
        boolean flag = false;
        try {
            conn = jedisPool.getResource();
            while (true){
                conn.watch(lockKey);
                if(identifier.equals(conn.get(lockKey))){
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if(results==null){
                        continue;
                    }
                    flag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.close();
            }
        }
        return flag;
    }

}
