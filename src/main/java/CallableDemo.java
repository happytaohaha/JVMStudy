import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"******** come in");
        TimeUnit.SECONDS.sleep(3);
        return 1234;
    }
}
/**
 * @Classname CallableDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description java 多线程中，获取多线程的方式
 *   1.继承Thread
 *   2.Runnable
 *   3.Callable
 *   总结： FutureTask   参数是Callable   实现了 Runnable
 */
public class CallableDemo {

    public static void main(String[] args) {
        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        //是一个进程
        new Thread(futureTask,"AA").start();
        //算一遍就不会再次进入计算 ，没用
        new Thread(futureTask,"BB").start();

        //查询是否算完，没有堵塞，main线程还在转着
//        while(!futureTask.isDone()){
//
//        }


        try {
            //没有算完main线程会堵塞，等待算完
            System.out.println("*******"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("**********");
    }


}
