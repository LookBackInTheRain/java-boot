package club.yuit.thread.t1;

/**
 * @author yuit
 */
public class Th01 {

    public static void main(String[] args) throws InterruptedException {

        T1 t1 = new T1();
        Thread t2 = new Thread(new T2(t1));
        Thread t3 = new Thread(() -> {
            try {
                // 等待t2执行完成
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("T3: " + i);
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }


}


class T1 extends Thread {


    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            System.out.println("T1: " + i);
        }
    }
}


class T2 implements Runnable {
    Thread t1;

    public T2(Thread t1) {
        this.t1 = t1;
    }

    public void run() {
        try {
            // 等待t1执行完成
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("T2: " + i);
        }
    }
}