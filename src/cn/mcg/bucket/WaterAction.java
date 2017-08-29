package cn.mcg.bucket;

/**
 * Author: mac
 * Date: 2017/8/27
 * Description: 倒水动作抽象
 */
public class WaterAction {
    private int from;
    private int to;
    private int water;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
