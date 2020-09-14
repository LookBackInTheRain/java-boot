package club.yuit.thread.t1;

public class Th_Synchronized {
    int i=0;
    public void exec(){
        // 当前对象加锁
        synchronized (this){
            i++;
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

    // 相当于 synchronized(this)
    public synchronized void exec2(){

    }

    // 在当前类的Class对象上加锁
    public static synchronized void exec1(){

    }


    public static void main(String[] args) {

        Th_Synchronized s = new Th_Synchronized();

        new Thread(()->{
            while (s.i<10){
                s.exec();
                // yield 方法让出CPU进入就绪状态
                Thread.yield();
            }

        }).start();

        new Thread(()->{
            while (s.i<10){
                s.exec();
                // yield 方法让出CPU进入就绪状态
                Thread.yield();
            }
        }).start();
    }

}

