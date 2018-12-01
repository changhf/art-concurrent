package com.changhf.chapter08;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchBodyTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                System.out.println("学生" + Thread.currentThread().getName() + "开始测试");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("学生" + Thread.currentThread().getName() + "测试完毕");
                latch.countDown();
            }, i + "").start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("5个学生测试完毕！开始处理结果。。");
    }
}