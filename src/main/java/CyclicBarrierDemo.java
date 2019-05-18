import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Classname CyclicBarrierDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description CyclicBarrier的使用
 *    总结： 1先声明CyclicBarrier 进行初始化 指定屏障次数和 过屏障的Runnble方法
 *    往下执行 cycleBarrier.await() 方法进行等待
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier(int parties,Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("********* 召唤神龙");
        });

        for(int i = 0; i < 7; i++){
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+tempInt+"龙珠");
                try {
                    //CyclicBarrier 进行等待 await等待数等于 7  才会进行实现Runnalbe接口的方法
                    cyclicBarrier.await();
                    //而在await  之后的方法得等到全部的执行了Runnble方法后才会执行
                    System.out.println(tempInt+"************");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
