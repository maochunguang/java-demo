package cn.mcg.bucket;

/**
 * Author: mac
 * Date: 2017/8/27
 * Description: 桶状态抽象
 */
public class BucketState {

    public static final int[] BUCKET_CAPICITY = {8, 5, 3};
    public static final int[] BUCKET_INIT_STATE = {8, 0, 0};
    public static final int[] BUCKET_FINAL_STATE = {4, 4, 0};
    public static final int BUCKETS_COUNT = 3;
    private WaterAction action = new WaterAction();
//    private BucketState bucketState = new BucketState();
    private int[] buckets = new int[3];

    public BucketState() {
        setBuckets(BUCKET_INIT_STATE);
        setAction(8, -1, 0);
    }

    public BucketState(int[] buckets) {
        setBuckets(buckets);
        setAction(8, -1, 0);
    }

    public BucketState(BucketState state) {
        setBuckets(state.getBuckets());
        setAction(state.getAction());
    }

    public int[] getBuckets() {
        return buckets;
    }

    public void setBuckets(int[] buckets) {
        for (int i = 0; i < BUCKETS_COUNT; i++) {
            this.buckets[i] = buckets[i];
        }
    }

    public WaterAction getAction() {
        return action;
    }

    public void setAction(int water, int from, int to) {
        action.setFrom(from);
        action.setTo(to);
        action.setWater(water);
    }

    public void setAction(WaterAction action) {
        action.setFrom(action.getFrom());
        action.setTo(action.getTo());
        action.setWater(action.getWater());
    }

    public boolean isSameState(BucketState state) {
        for (int i = 0; i < BUCKETS_COUNT; ++i) {
            if (buckets[i] != state.getBuckets()[i])
                return false;
        }
        return true;
    }

    public boolean isBucketEmpty(int bucket) {
        assert ((bucket >= 0) && (bucket < BUCKETS_COUNT));
        return buckets[bucket] == 0;
    }

    public boolean isBucketFull(int bucket) {
        assert ((bucket >= 0) && (bucket < BUCKETS_COUNT));

        return (buckets[bucket] >= BUCKET_CAPICITY[bucket]);
    }

    public boolean isFinalState() {
        BucketState state = new BucketState(BUCKET_FINAL_STATE);
        return isSameState(state);
    }


    public boolean canTakeDumpAction(int from, int to) {
        assert ((from >= 0) && (from < BUCKETS_COUNT));
        assert ((to >= 0) && (to < BUCKETS_COUNT));
         /*不是同一个桶，且from桶中有水，且to桶中不满*/
        if ((from != to)
                && !isBucketEmpty(from)
                && !isBucketFull(to)) {
            return true;
        }
        return false;

    }

    /*从from到to倒水，返回实际倒水体积*/
    public boolean dumpWater(int from, int to, BucketState next) {
        next.setBuckets(buckets);
        int dump_water = BUCKET_CAPICITY[to] - next.buckets[to];
        if (next.buckets[from] >= dump_water) {
            next.buckets[to] += dump_water;
            next.buckets[from] -= dump_water;
        } else {
            next.buckets[to] += next.buckets[from];
            dump_water = next.buckets[from];
            next.buckets[from] = 0;
        }
        /*是一个有效的倒水动作?*/
        if (dump_water > 0) {
            next.setAction(dump_water, from, to);
            return true;
        }

        return false;
    }

    public void printStates() {
        System.out.println(" dump " + action.getWater() + "water from " +
                action.getFrom() + 1 + " to " + action.getTo() + 1 + " , ");
        System.out.println("buckets water states is :");
        for (int i = 0; i < BUCKETS_COUNT; i++) {
            System.out.printf(" " + buckets[i] + " ");
        }
    }
//    void PrintStates();
//
//    bool DumpWater(int from, int to, BucketState& next);
//
//    int bucket_s[BUCKETS_COUNT];
//    ACTION curAction;
}
