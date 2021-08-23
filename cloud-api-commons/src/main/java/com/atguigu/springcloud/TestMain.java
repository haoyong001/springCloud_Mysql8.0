package com.atguigu.springcloud;

import lombok.Getter;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Mr. Hao
 * @date 2021-08-18   17:50
 */
public class TestMain {
    static volatile int j = 0;

    public static void main(String[] args) {
        /*System.out.println("开始收集龙珠*************CyclicBarrier不会阻塞主线程");
        cyclicBarrierTest();
        System.out.println("结束收集龙珠***********CyclicBarrier不会阻塞主线程");
        System.out.println("秦统一六国开始********CountDownLatch会阻塞主线程");
        try {
            sixCountry();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("秦统一六国结束********CountDownLatch会阻塞主线程");
        System.out.println("抢车位开始*********Semaphore不会阻塞主线程");
        semaphoreTest();
        System.out.println("抢车位结束*********Semaphore不会阻塞主线程");
        OutOfMemoryErrorheapspaceTest();*/
        StackOverflowErrorTest();
    }

    /*模拟堆溢出*/
    public static void OutOfMemoryErrorheapspaceTest() {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("分配次数：" + (++i));
        }
    }

    /*模拟栈溢出*/
    public static void StackOverflowErrorTest() {
        StackSOFTest test = null;
        try {
            test = new StackSOFTest();
            test.sofMethod();
        } finally {
            System.out.println("递归次数：" + test.depth);
        }
    }


    /*信号量的主要用于两个目的，一个是用于多个共享资源的相互排斥使用，另一个用于并发资源数
    的控制。*/
    public static void semaphoreTest() {
        //模拟3个停车位
        Semaphore semaphore = new Semaphore(3);
        //模拟6部汽车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //抢到资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到 车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 停 3秒离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放资源
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }


    /*CyclicBarrier的字面意思是可循环使用的屏障。他要做的事情是，让一组线程到达屏障（也可以
    叫做同步点）时被阻塞，知道最后一个线程到达屏障时屏障才会开门，所以被屏障拦截的线程才会继续
    执行，线程进入屏障通过CyclicBarrier的await（）方法。*/
    public static void cyclicBarrierTest() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    /*介绍：让一些线程阻塞知道另外一些完成后才被执行。
    CountDownLatch主要有两个方法，当一个或者多个线程调用await方法时，调用线程会被阻塞。其他
    线程调用countDown方法计数器减1（调用countDown方法时线程不会阻塞），当计数器的值变为0，
    因调用await方法被阻塞的线程会被唤醒，继续执行。*/
    private static void sixCountry() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "国,灭亡");
                countDownLatch.countDown();
            }, CountryEnum.forEach(i).getName()).start();
        }
        countDownLatch.await();
        System.out.println("********秦统一");
    }

    public enum CountryEnum {
        ONE(1, "齐"),
        TWO(2, "楚"),
        THREE(3, "燕"),
        FOUR(4, "赵"),
        FIVE(5, "魏"),
        SIX(6, "韩");

        CountryEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        @Getter
        private Integer code;
        @Getter
        private String name;

        public static CountryEnum forEach(int index) {
            CountryEnum[] countryEnums = CountryEnum.values();
            for (CountryEnum countryEnum : countryEnums) {
                if (index == countryEnum.getCode()) {
                    return countryEnum;
                }
            }
            return null;
        }
    }

    public void ceshi() {
        List list = new ArrayList();
        List synchronizedList = Collections.synchronizedList(new ArrayList<>());
        List copyOnWriteList = new CopyOnWriteArrayList();
        for (int i = 1; i < 30; i++) {
            j = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                copyOnWriteList.add(UUID.randomUUID().toString().substring(1, 8));
                System.out.println(j + ":" + copyOnWriteList);
            }, String.valueOf(i)).start();


        }
    }

    /*模拟栈溢出*/
    public static class StackSOFTest {
        int depth = 0;

        public void sofMethod() {
            depth++;
            sofMethod();
        }
    }
}
