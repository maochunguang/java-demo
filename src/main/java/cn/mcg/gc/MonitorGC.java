package cn.mcg.gc;

import org.junit.Test;

import java.util.TreeMap;

/**
 * Created by mao on 2017/9/12.
 */
public class MonitorGC {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc-Xms20M-Xmx20M-Xmn10M-XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @Test
    public void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次Minor GC
    }
//    -XX：PretenureSizeThreshold=3145728

    @Test
    public void testAllocationOldGen() {
        byte[] allocation1;
        allocation1 = new byte[4 * _1MB];//出现一次Minor GC

    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            if(i==1){
                break;
            }
            System.out.println(i);
        }
    }
}
