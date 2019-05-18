import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname SyncAndReentrantLockDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description Lock 和 synchronized有什么区别？新Lock有什么好处？举例说明
 *
 *
    1.原始架构
    synchronized是关键字属于JVM层面，
    monitorenter（底层是通过monitor对象完成，其实wait/notify也依赖于monitor对象，只有在同步块内或者方法内才能调用wait/notify等方法）
    monitorexit   每次1个enter 2个exit  一个正常退出 一个错误退出  所以一定会退出

    Lock（java.util.concurrent.Locks.Lock）是API层面的锁
    2.使用方法
    synchronized 不需要手动释放资源，当synchronized代码执行之后系统会自动让线程释放对锁的占用，不会死锁。
    ReentrantLock则需要用户手动释放锁，如果没有就会产生死锁的情况。
    Lock必须手动释放 Lock unLock 配合try catch finally 一起使用
    3.公平非公平
    synchronized非公平锁
    Lock默认非公平锁，但是可以指定公平锁， true 公平锁 false非公平锁。
    4.等待是否可中断
    synchronized 不可中断，除非抛出异常或者正常运行完成。
    lock可以被中断， 1.设置超时方法 tryLock（Long timeout，TimeUnit unit）
    2.lockInterruptibly（）放入代码块中，调用interrupt（）方法即可
    5.锁定多个条件condition（钥匙）
    synchronized没有 唤醒时notify 和notifyAll 随机唤醒一个 或者全部
    lock有多个condition 可以指定唤醒和分组唤醒。

 题目：多线程之间按顺序调用， A=》 B=》 c 三个线程启动，要求如下：
 AA 打印5 次 BB打印 10次 CC打印15次  按此顺序 打印 10轮

    总结： Lock Conditon的使用   signal完别人后就往下执行直到解锁
      Condition   状态  可以指定唤醒谁  await等待 signal唤醒   signalALl 但是不是他的状态他会继续睡
 而多个condition是为了指定唤醒
 */
class ShareResource{
    //标志
    private int number = 1;// A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //1.判断
            while (number !=1){
                c1.await();
            }
            //2.干活
            for(int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName()+"\t print"+i);
            }
            number = 2;

            //3.唤醒
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //1.判断
            while (number !=2){
                c2.await();
            }
            //2.干活
            for(int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName()+"\t print"+i);
            }
            number = 3;

            //3.唤醒
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //1.判断
            while (number != 3){
                c3.await();
            }
            //2.干活
            for(int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName()+"\t print"+i);
            }
            number = 1;

            //3.唤醒
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource =new ShareResource();
        new Thread(() -> {
            for (int i = 0; i< 10; i++){
                shareResource.print5();
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i< 10; i++){
                shareResource.print10();
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i< 10; i++){
                shareResource.print15();
            }
        },"CC").start();
    }
}
