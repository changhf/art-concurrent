package com.changhf.chapter08;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T44Latch {
    static int count;
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start..");
            if (count != 5) {
                try {
                    latch.await();
//                    latch.await(15,TimeUnit.SECONDS);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end..");
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println(i);
                if (count == 5) {
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}