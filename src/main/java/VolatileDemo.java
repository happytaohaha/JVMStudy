import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yt
 * @data 2019/5/15
 * 1 验证volatile的可见性
 *  1.1 假如 int number = 0; number 变量之前根本没有添加volatile关键字
 *  1.2 将int number 修改为 volatile int number 这样就实现了可见性
 *
 * 2 验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个业务时，中间不可以被加塞或者被分割。需要整体完整
 *      要么同时成功要么不干
 *  2.2 不保证原子性的事例
 *  volatile 不保证原子性的原理是因为有可能有很多线程同时在写入这个数，会出现丢失写值的情况  （没加synchronized lock）
 *
 *  2.3 why
 *
 *  2.4 如何解决原子性？
 *      * synchronized
 *      * AtomicInteger  原理cvs
 */

class MyData{ //MyDate.java => MyDate.class => JVM字节码
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }
    //这个变量使用了volatile 保证了可见性，但不保证原子性  synchronized(增加到函数上) 会保证 每次只能有一个改这个数据
    // 写入以后，但是没有通知然后另外的线程继续写，出现写覆盖  纳秒级别
    public void addPlusPlus(){
        this.number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {

    //main 是一切方法的入口
    public static void main(String[] args) {
        MyData myData = new MyData();

        for(int i = 1; i <= 20; i ++){
            new Thread(()->{
                for(int j =1; j <= 1000; j++){
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            //交出线程的控制权
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int finally number value: "+ myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger finally number value: "+ myData.atomicInteger);
    }

    //volatile 可以保证可见性，及时通知其他线程，主物理内存的值已经被修改。
    private static void seeOkByVolatile() {
        //资源类
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value: " + myData.number);
        },"AAA").start();

        //第二个线程是main
        while (myData.number == 0){
            //如果检测到number一直为 0 一直循环，只有检测到改变时会跳出循环
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over,main get number value:" + myData.number);
    }
}
