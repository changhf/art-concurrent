package com.changhf.chapter01;

/**
 * 并发和单线程执行测试
 * 
 */
public class ConcurrencyTest {

    /** 执行次数 */
    private static final long count = 100000000L;

    public static void main(String[] args) throws InterruptedException {
        //并发计算
        concurrency();
        //单线程计算
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
            System.out.println(a);
        });
        t1.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        //调用join(),t1执行完成后主线程才会计算时间差
        t1.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }

}
