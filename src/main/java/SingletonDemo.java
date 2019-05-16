
/**
 * @Classname SingletonDemo
 * @Author 严涛
 * @Date 2019/5/15
 * @Description volatile的使用案例 单例模式
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }
    //1.高并发加 synchronized  这种情况是不太好的
    //2.DCL (double Check Lock 双端检索机制)  双端检索机制 多线程不一定安全 有指令重排的可能
    public static SingletonDemo getInstance(){
        if(instance == null){
            //代码块
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance =new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());

        //并发多线程后，情况发生很大的动作。。。。。。。。。
        for(int i = 0; i < 10; i ++){
            new Thread(()->{
                SingletonDemo.getInstance();
                },String.valueOf(i)).start();
        }
    }
}
