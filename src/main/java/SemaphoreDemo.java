import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Semaphore
 * @Author 严涛
 * @Date 2019/5/17
 * @Description Semaphore的使用  抢停车位   30 量车 20 车位
 * 可以代替 sychronized 和 lock
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *  默认非公平锁  初始化的时候可以指定
 *总结  semphore  1.是CountDownLatch 和 CyclicBarrier  的升级版
 *      2.他有对资源的使用并释放连接
 *      3. 调用 acqurie  和release  进行使用
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //3车位
        Semaphore semaphore = new Semaphore(3);
        //6辆车
        for(int i = 0; i < 6; i++){
            new Thread(() -> {
                try {
                    //请求车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 停车离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
