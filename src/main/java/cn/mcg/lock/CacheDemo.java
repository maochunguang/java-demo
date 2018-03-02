package cn.mcg.lock;

import java.util.HashMap;
import java.util.Map;  
import java.util.concurrent.locks.ReadWriteLock;  
import java.util.concurrent.locks.ReentrantReadWriteLock;  
  
public class CacheDemo {  
    private Map<String, Object> cache = new HashMap<>();  
  
    public static void main(String[] args) {  
  
    }  
  
    // 可做到多个线程并必的读 读和写又互斥 系统性能很高,这就是读写锁的价值
    private ReadWriteLock rwl = new ReentrantReadWriteLock();  
  
    public Object getData(String key) {  
        rwl.readLock().lock();  
        Object value = null;  
        try {  
            value = cache.get(key);  
            if (value == null) {// 避免首次多次查询要加synchronized  
                rwl.readLock().unlock();  
                rwl.writeLock().lock();  
                try {  
                    if (value == null) // 就算第二个第三个线程进来时也不用再写了 跟伪代码相同原理  
                        value = "aaa";// 实际去query db  
                } finally {  
                    rwl.writeLock().unlock();  
                }  
                rwl.readLock().lock();  
            }  
        } finally {  
            rwl.readLock().unlock();  
        }  
        return value;  
    }  
}  