import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{//资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //生产者
    public void increment() throws Exception{
        lock.lock();
        try{
            //1.判断
            while(number != 0){
                //等待不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    //消费者  只能同时一个人消费
    public void decrement() throws Exception{
        lock.lock();
        try{
            //1.判断
            while(number == 0){
                //等待不能生产
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
}
/**
 * @Classname ProdConsumer_TranditionDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 传统生产者消费者
 * 题目：初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * 1. 线程    操作  资源类
 * 2. 判断    干活   通知
 * 3. 防止虚假判断机制
 */
public class ProdConsumer_TranditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i=1; i <=5; i++ ){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(() -> {
            for (int i=1; i <=5; i++ ){
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
