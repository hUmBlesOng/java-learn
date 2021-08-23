package juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 *
 * @author 🦑bys
 * @date 2021/7/28 17:22
 */
public class WhatReentrant {

    public static void main(String[] args) {
        // synchronized演示
//        sync();
        reen();
    }

    /**
     * synchronized锁
     */
    private static void sync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("第一次获取锁");
                    for (int i = 0; i < 10; i++) {
                        synchronized (this) {
                            System.out.println("第" + i + "次获取锁");
                        }
                    }
                }
            }
        }).start();
    }

    // ReentrantLock锁
    public static void reen() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("第一次获取锁");
                    for (int i = 0; i < 10; i++) {
                        try {
                            lock.lock();
                            System.out.println("第" + i + "次获取锁");
                        } finally {
                            lock.unlock();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
