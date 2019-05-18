import java.util.concurrent.CountDownLatch;

/**
 * @Classname CountDownLatchDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description CountDownLatch 向下计数直到0 再往下执行方法
 *  班长计数走人问题
 *  总结： CountDownLatch 使用步骤  1.初始化赋值 2.countDown 方法 3.await 方法
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        String []coutray = {"楚国",""};
        for(int i = 1; i <= 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                //这里就是仅仅减一的操作，不用管main线程
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        //这个方法可能异常  抛出Exception   main 线程到这会卡住不会往下走  让给其他线程，直到CountDownLatch 值为 0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t************** 秦帝国。一统华夏");
    }


    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 0; i < 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 上完自习走人");
                //这里就是仅仅减一的操作，不用管main线程
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //这个方法可能异常  抛出Exception   main 线程到这会卡住不会往下走  让给其他线程，直到CountDownLatch 值为 0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t************** 班长最后关门走人");
    }
}
