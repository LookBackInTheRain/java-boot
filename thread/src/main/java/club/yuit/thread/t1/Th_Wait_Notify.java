package club.yuit.thread.t1;

/**
 * @author yuit
 */
public class Th_Wait_Notify {

    final static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        T t1 = new T();
        T t2 = new T();
        T t3 = new T();
        T t4 = new T();

        t4.start();
        t1.start();
        t2.start();
        t3.start();


      for (int i=0;i<4;i++){
          Thread.sleep(3000);
          synchronized (Th_Wait_Notify.object){
              Th_Wait_Notify.object.notify();
          }
      }

    }

}


class  T extends Thread {




    @Override
    public void run() {
        exec();
    }


    private void exec()  {
        synchronized (Th_Wait_Notify.object){
            System.out.println(getName());
            try {
                Th_Wait_Notify.object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName()+"notified");
        }

    }

}