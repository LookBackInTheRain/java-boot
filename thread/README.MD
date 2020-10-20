# 线程
进程调度的基本单位
# 创建线程的方式
1. 继承Thread 类， 重写run方法
 ```java
public class T1 extends Thread {
    public void run(){
        System.out.println("t1");
    }
}
```
2. 实现Runnable接口，重写run方法
```java
public class T2 implements Runnable{
    public void run(){
        System.out.println("t2");
    }
}
```
3. lambda 表达式
```java
public class Main{
    public static void main(String[] args){
      new Thread(()->{
            System.out.println("t3");
        }).start();
    }
}
```

# 线程暂停的方式
1. sleep(long time) 方法，线程阻塞但不会释放资源
2. wait() 方法，释放资源，使用notify（随机唤醒一个）或notifyAll（全部唤醒）唤醒线程

# 线程同步
1. synchronized 关键字
2. join() 方法
