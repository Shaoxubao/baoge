package com.baoge.countdownlatch;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author shaoxubao
 * @Date 2019/11/21 18:11
 */
public class CyclicBarrierDemo2 {

    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        Soldier(CyclicBarrier barrier, String soldierName) {
            this.cyclicBarrier = barrier;
            this.soldier = soldierName;
        }

        @Override
        public void run() {
            try {
                // 等待所有士兵到齐
                cyclicBarrier.await();

                doWork();

                // 等待所有士兵完成工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(soldier + "任务完成.");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            this.N = n;
        }


        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[士兵 " + N + "个，完成任务!]");
            } else {
                System.out.println("司令：[士兵 " + N + "个，集合完毕!]");

                flag = true;
            }
        }
    }


    public static void main(String[] args) {
        final int n = 10;
        Thread[] allSoldier = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrierRun(flag, n));

        // 设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍!");

        for (int i = 0; i < n; i++) {
            System.out.println("士兵" + i + "报道!");
            allSoldier[i] = new Thread(new Soldier(cyclicBarrier, "士兵" + i));
            allSoldier[i].start();
        }

    }


}
