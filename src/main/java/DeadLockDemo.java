class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        //拿着A 尝试获取B
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}
/**
 * @Classname DeadLockDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description 死锁
 * 死锁是指两个或者两个以上的进程在执行过程中，因抢夺资源而造成的一种互相等待的现象，若无外力干涉它们将都无法推进下去，如果系统资源充足，进程的资源请求都能够得到满足，死锁出现的可能性也就很低，否则就会因争夺有限的资源而陷入死锁。
 *
 * java版的 ps      看正在运行的线程   jps  -l
 * jstack 9696
 * Found 1 deadlock.
 *
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBB").start();

    }
}
