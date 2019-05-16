import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname CASDemo
 * @Author 严涛
 * @Date 2019/5/16
 * @Description compareAndSet   比较并设置
 *   先比较期望的 如果  一样 再进行修改
 *              如果 不一样 就不进行修改  也就是有冲突就需要先拿到最新的然后去做其他操作
 */
public class CASDemo {
    public static void main(String[] args) {

         AtomicInteger atomicInteger = new AtomicInteger(5);

        //main do someting....

        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t current date:"+ atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t current date:"+ atomicInteger.get());
    }
}
