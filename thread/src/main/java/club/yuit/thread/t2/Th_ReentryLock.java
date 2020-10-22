package club.yuit.thread.t2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Th_ReentryLock {

    static final Object monitor = new Object();
    static int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        Thread t1 = new Thread(() -> {

            while (i < 100) {
                lock.lock();
                i++;
                System.out.println("t1:" + i);
                lock.unlock();
            }

        });

        Thread t2 = new Thread(() -> {

            while (i < 100) {
                try {
                    lock.lockInterruptibly();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                System.out.println("t2:" + i);
                lock.unlock();
            }

        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

class SwapOutput {

    static final Object monitor = new Object();
    static int i = 0, j = 0;
    static boolean flg = false;

    public static void main(String[] args) {

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] strs = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};

        Thread t1 = new Thread(() -> {
            synchronized (monitor) {
                for (; i < numbers.length; i++) {
                    System.out.print(numbers[i]);
                    flg = true;
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (monitor) {
                if (!flg) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (; j < numbers.length; j++) {
                    System.out.print(strs[j]);
                    flg = false;
                    monitor.notify();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
