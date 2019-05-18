import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class Phone implements Runnable{
    //sychronized 锁
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked SMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t ###invoked Email");
    }


    Lock lock = new ReentrantLock(); //可重入lock锁
    @Override
    public void run() {
        get();
    }
    //lock配对 就正确  编译过 答案正确
    //lock不配对 编译正确  程序运行不报错 但是答案错误 已经被锁住
    void get(){
        lock.lock();
        //lock.lock();
        try{

            System.out.println(Thread.currentThread().getName()+"\t invoked get");
            set();
        }finally {
            lock.unlock();
            //lock.unlock();
        }
    }

    void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked set");
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @Classname ReenterLockDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 可重入锁  拿到锁后内部调用的方法有锁, 则内部用的锁是同一把锁
 *
 *  可重入锁（也就是递归锁）：指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一线程在外层方法获取锁的时候，在进入
 *  内层方法会自动获取锁。
 *  也就是说，线程可以进入任何一个它已经拥有的锁所有同步着的代码块。
 *  锁里还有锁，但是有第一把锁，里面的锁拿到了
 *t1	 invoked SMS   t1 在外层方法获取锁的时候
 *t1	 ###invoked Email t1在内层方法会自动获取锁
 *t2	 invoked SMS
 *t2	 ###invoked Email
 * */
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone =new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();



        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();
    }
}
