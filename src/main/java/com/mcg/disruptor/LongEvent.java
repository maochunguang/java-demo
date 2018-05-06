package com.mcg.disruptor;

/**
 * @author maocg
 * Date：2018/5/5
 * Description：TODO
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}