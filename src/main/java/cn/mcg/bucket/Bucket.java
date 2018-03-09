package cn.mcg.bucket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: mac
 * Date: 2017/8/28
 * Description: todo
 */
public class Bucket {
    private BucketState state;

    private boolean isSameBucketState(BucketState state1, BucketState state2) {
        return state1.isSameState(state2);
    }

    private static boolean isProcessedState(Queue<BucketState> states, BucketState newState) {
        Iterator<BucketState> it = states.iterator();
        while (it.hasNext()) {
            BucketState state = it.next();
            if (state.isSameState(newState))
                return false;
        }
        return true;
    }

    private static void printResult(Queue<BucketState> states) {
        Iterator<BucketState> it = states.iterator();
        while (it.hasNext()) {
            BucketState state = it.next();
            state.printStates();
        }
    }


    public static void searchStateOnAction(Queue<BucketState> states, BucketState current, int from, int to) {
        if (current.canTakeDumpAction(from, to)) {
            BucketState next = states.peek();
         /*从from到to倒水，如果成功，返回倒水后的状态*/
            boolean bDump = current.dumpWater(from, to, next);
            if (bDump && !isProcessedState(states, next)) {
                states.add(next);
                searchState(states);
                states.peek();
            }
        }
    }

    public static void searchState(Queue<BucketState> states) {
        BucketState current = states.peek(); /*每次都从当前状态开始*/
        if (current.isFinalState()) {
            printResult(states);
            return;
        }

    /*使用两重循环排列组合6种倒水状态*/
        for (int j = 0; j < BucketState.BUCKETS_COUNT; ++j) {
            for (int i = 0; i < BucketState.BUCKETS_COUNT; ++i) {
                searchStateOnAction(states, current, i, j);
            }
        }
    }

    public static void main(String[] args) {
        Queue<BucketState> states = new LinkedList<>();
        BucketState state = new BucketState(BucketState.BUCKET_INIT_STATE);
        states.add(state);
        searchState(states);
        assert (states.size() == 1);
    }

}
