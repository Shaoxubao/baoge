package concurrent.thread.count_sum;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/2/23
 */

import java.util.concurrent.*;

/**
 * 假如有Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，
 * 所有线程都统计完毕交给Thread5线程去做汇总，应当如何实现？
 */
public class 四个线程统计_另一个线程汇总 {

    public static void main(String[] args) throws InterruptedException {
//        test2();
        test3();
    }

    public static void test0() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("统计C盘");
            }

        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("统计D盘");
            }

        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("统计E盘");
            }

        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("统计F盘");
            }

        });
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("合计CDEF");
            }

        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
//        while(Thread.activeCount()>1){
//            Thread.yield();
//        }
        t5.start();
    }

    // 本例，用CountDownLatch实现，CountDownLatch相当于一个计时器
    public static void test1() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("统计C盘");
                    countDownLatch.countDown(); // 单任务，把计数器减1
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable run2 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("统计D盘");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable run3 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("统计E盘");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable run4 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("统计F盘");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(run1);
        service.submit(run2);
        service.submit(run3);
        service.submit(run4);
//	        new Thread(run1).start();
//	        new Thread(run2).start();
//	        new Thread(run3).start();
//	        new Thread(run4).start();
        countDownLatch.await(); // 主线程，即第5线程等待
        System.out.println("合计C,D,E,F");
        service.shutdown();
    }


    // 本例，用CyclicBarrier实现，CyclicBarrier相当于一个栅栏，会将线程挡住
    public static void test2() {
        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("统计C,D,E,F盘");
            }
        };

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, barrierAction);
        Runnable run1 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("C盘");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable run2 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("D盘");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable run3 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("E盘");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable run4 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("F盘");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        };

        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(run1);
        service.submit(run2);
        service.submit(run3);
        service.submit(run4);
        service.shutdown();
    }

    // 一般情况，我们实现多线程都是Thread或者Runnable(后者比较多)，但是，这两种都是没返回值的，
    // 所以我们需要使用callable(有返回值的多线程)和future(获得线程的返回值)来实现了。
    public static void test3() {
        ThreadCount threadCount = null;
        ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 4; i++) {
            threadCount = new ThreadCount(i + 1);
            completionService.submit(threadCount);
        }

        // 添加结束，及时shutdown，不然主线程不会结束
        executorService.shutdown();

        int total = 0;
        for (int i = 0; i < 4; i++) {
            try {
                //一个需要注意的小细节，completionService.take.get()获取返回值，是按照完成的顺序的，即上面案例返回顺序是CEFD
                total += completionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(total);
    }
}

class ThreadCount implements Callable<Integer> {

    private int type;

    ThreadCount(int type) {
        this.type = type;
    }

    @Override
    public Integer call() throws Exception {
        if (type == 1) {
            System.out.println("C盘统计大小");
            return 1;
        } else if (type == 2) {
            //Thread.sleep(3000);
            System.out.println("D盘统计大小");
            return 2;
        } else if (type == 3) {
            System.out.println("E盘统计大小");
            return 3;
        } else if (type == 4) {
            System.out.println("F盘统计大小");
            return 4;
        }
        return null;
    }

}
