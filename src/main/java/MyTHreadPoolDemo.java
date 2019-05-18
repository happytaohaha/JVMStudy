import java.util.concurrent.*;

/**
 * @Classname MyTHreadPoolDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description 线程池
 * 好处：省略了上下文的切换
 *   需要线程池直接用
 *   第四种获取线程   线程池
 *   ThreadPoolExecutor 线程池
 */
public class MyTHreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,//设置为多少合适
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        //最大  5+3
        try{
            for(int i = 0; i < 10; i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();

        }

    }

    private static void threadPoolInit() {
        //查看CPU个数
        //System.out.println(Runtime.getRuntime().availableProcessors());

        //一池 固定线程数newFixedThreadPool
        // 一池 一线程newSingleThreadExecutor
        // 一池多线程带缓存newCachedThreadPool
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池多线程
        //模拟10个用户来办理业务，每个用户激素hi一个来自外部的请求线程
        try{
            for(int i = 0; i < 10; i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();

        }
    }
}
