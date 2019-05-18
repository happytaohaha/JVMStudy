import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产者消费者
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue = false;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            //如果能插直接插入 ，最多等两秒
            retValue =blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列失败");
            }
            //生产完之后等待1秒
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了,FLAG = false");
    }
    public void myConsumer() throws Exception{
        String result;
        while (FLAG){
            //如果能取马上取 ，最多等两秒
            result =blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有获取蛋糕，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+ result+"成功");
        }
    }
    public void stop(){
        FLAG =false;
    }
}
/**
 * @Classname ProdConsumer_BlockQueueDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description 生产者消费者阻塞队列版
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * 总结  volatile 可见性 无原子性 禁止重排
 *        CAS 比较交换
 *      atomicInteger 原子整型  整形赋值3步变为原子 型
 *       BlockQueue  arrayBlockingQueue LinkedBlockingQueue SynchronousQueue
 *        原子引用  将引用作为一个整体
 *       线程交互 共同使用资源池
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程开始");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程开始");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
    }
}
