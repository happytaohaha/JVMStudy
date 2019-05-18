import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Classname SpinLocakDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 手写自旋锁
 */
public class SpinLocakDemo {

    //如果是引用初始化为 null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in O(∩_∩)O");

        while(!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked unlock O(∩_∩)O");
    }
    public static void main(String[] args) {
        SpinLocakDemo spinLocakDemo = new SpinLocakDemo();

        new Thread(() -> {
            spinLocakDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLocakDemo.unlock();
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLocakDemo.mylock();
            spinLocakDemo.unlock();
        },"BB").start();
    }
}
