package cn.mcg.suanfa;
import java.util.ArrayList;

public class Main {
    private static final int COUNT_BITS = Integer.SIZE - 3;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("RUNNING = [" + RUNNING + "]");
        System.out.println("SHUTDOWN = [" + SHUTDOWN + "]");
        System.out.println("STOP = [" + STOP + "]");
        System.out.println("TIDYING = [" + TIDYING + "]");
        System.out.println("TERMINATED = [" + TERMINATED + "]");
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        String c = new String("abcd");
        String d = "abcd";

        String e = "abcd";
        System.out.println("args ---- [" + (1<<30) + "]");
        System.out.println("args = [" + e.equals(d) + "]");
        ArrayList<String> arrayList = new ArrayList<>();
//        Iterator
    }
}
