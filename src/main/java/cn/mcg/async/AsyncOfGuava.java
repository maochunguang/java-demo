package cn.mcg.async;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author maocg
 * Date：2018/7/8
 * Description：TODO
 */
public class AsyncOfGuava {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final SettableFuture settableFuture1 = SettableFuture.create();
        Futures.addCallback(settableFuture1, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                settableFuture1.set("result1");
                System.out.println("future task1 execute time::" + (System.currentTimeMillis() - start));

            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println("ExecutionException---" + e);
                System.out.println("ExecutionException_msg---" + e.getMessage());
                System.out.println("ExecutionException_cause---" + e.getCause());
            }
        }, Executors.newFixedThreadPool(1));


        final SettableFuture settableFuture2 = SettableFuture.create();
        Futures.addCallback(settableFuture2, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
         /*       try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                settableFuture1.set("result2");
                System.out.println("future task2 execute time::" + (System.currentTimeMillis() - start));
            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println("ExecutionException---" + e);
            }
        }, Executors.newFixedThreadPool(1));

        try {
            settableFuture1.get(4500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 第二个get会执行第一个get结束的时间+第二个get的时间
            settableFuture2.get(4500, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void future_tasks_in_parallel() {
        long start = System.currentTimeMillis();
        final SettableFuture<String> settableFuture1 = SettableFuture.create();
        Futures.addCallback(settableFuture1, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                settableFuture1.set("result1");
                System.out.println("future task1 execute time::" + (System.currentTimeMillis() - start));

            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println("ExecutionException---" + e);
                System.out.println("ExecutionException_msg---" + e.getMessage());
                System.out.println("ExecutionException_cause---" + e.getCause());
            }
        }, Executors.newFixedThreadPool(5));


        final SettableFuture<String> settableFuture2 = SettableFuture.create();
        Futures.addCallback(settableFuture2, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
         /*       try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                settableFuture1.set("result2");
                System.out.println("future task2 execute time::" + (System.currentTimeMillis() - start));
            }


            @Override
            public void onFailure(Throwable e) {
                System.out.println("ExecutionException---" + e);
            }
        }, Executors.newFixedThreadPool(5));

        try {
            settableFuture1.get(4500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 第二个get会执行第一个get结束的时间+第二个get的时间
//            settableFuture2.get(4500, TimeUnit.MILLISECONDS);
            settableFuture2.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void should_run_future_tasks_in_parallel() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        long start = System.currentTimeMillis();
        ListenableFuture<?> task1 = service.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("future task1 done.....");
                System.out.println("future task1 execute time::" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            task1.get(3100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListenableFuture<?> task2 = service.submit(() -> {
            try {
                Thread.sleep(6000);
                System.out.println("future task2 done.....");
                System.out.println("future task2 execute time::" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        Thread.sleep(3000);

        try {
            // 第二个get会执行第一个get结束的时间+第二个get的时间
            task2.get(3100, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main task done.....");

    }

}

class AsyncFuntionSample implements AsyncFunction<Long, String> {
    private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
    private ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    @Override
    public ListenableFuture<String> apply(final Long input) throws Exception {
        if (map.containsKey(input)) {
            SettableFuture<String> listenableFuture = SettableFuture.create();//构建一个SettableFuture
            listenableFuture.set(map.get(input));
            return listenableFuture;
        } else {
            return listeningExecutorService.submit(() -> {
                String retrieved = "compute to get the data";
                map.putIfAbsent(input, retrieved);
                return retrieved;
            });
        }
    }

}
