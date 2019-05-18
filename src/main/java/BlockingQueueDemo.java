import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname BlockingQueueDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 阻塞队列
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序。
 * LinkedBlockingQueue: 是一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常比ArrayBlockingQueue.
 * SynchronousQueue: 一个不存储元素的阻塞队列。每一个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
 *
 * 1.队列
 *
 * 2.阻塞队列
 *  2.1 阻塞队列的好的一面
 *
 *  2.2 不得不阻塞，你如何管理
 *  ArrayList LinkedList CopyOnWriteArrayList
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        //超时
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        //阻塞两秒后就超时不后
        //System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        //阻塞两秒后就超时不后
        //blockingQueue.poll(2L,TimeUnit.SECONDS);
    }

    // 堵塞
    private static void putAndtake() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("==================");
        //一直堵着
        //blockingQueue.put("XX");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //一直堵着
        //blockingQueue.take();
    }

    //温和版 返回true false 和  对象 null
    private static void offerAndPollAndPeek() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //特殊值 true false
        System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("b"));//true
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("XX"));//false

        System.out.println(blockingQueue.peek());  //探测队列首部

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null
    }

    //超出后直接抛异常
    private static void addAndremoveAndelement() {
        //List list = new ArrayList();
        //ArrayBlockingQueue 需要指定初始值
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //当队列满的时候直接抛异常
//        System.out.println(blockingQueue.add("xx"));
        //检查队列空不空，队首是谁
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //当队列空的时候直接抛异常
        System.out.println(blockingQueue.remove());
    }

}
