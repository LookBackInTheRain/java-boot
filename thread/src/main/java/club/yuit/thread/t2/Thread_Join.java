package club.yuit.thread.t2;

/**
 * @author yuit
 * 测试join方法是否会释放获得的资源
 * 结果：join 让当前线程阻塞等待其他线程结束，等待过程中不会释放资源
 */
public class Thread_Join {

    static final Object monitor = new Object();

    public static void main(String[] args) {
       Thread t1= new Thread(()->{
           try {
               Thread.sleep(10000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });

       Thread  t2 = new Thread(()->{
          synchronized (monitor){
              System.out.println("t2获得锁");
              try {
                  t1.join();
                  System.out.println("t1完成");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
       });
       Thread t3 = new Thread(()->{
          synchronized (monitor){
              System.out.println("t3获得锁");
          }
       });
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
    }
}

