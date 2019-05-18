import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    //Reentrant 可重用的
    private ReentrantReadWriteLock rwlock =new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 完成写入");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            rwlock.writeLock().unlock();
        }

    }

    public void get(String key){
        rwlock.readLock().lock();

        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 完成读取");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            rwlock.readLock().unlock();
        }
    }

    public void clearMap(){
        map.clear();
    }
}
/**
 * @Classname ReadWriteLockDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其他线程可以对该资源进行读和写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *
 *      写操作： 原子+独占，整个过程必须是一个完整的一个过程，中间不允许被分割，被打断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache =new MyCache();
       for(int i = 0; i < 10; i++){
           final int tempInt = i;
           new Thread(() -> {
                myCache.put(tempInt+"",tempInt+"");
           },String.valueOf(i)).start();
       }
        for(int i = 0; i < 10; i++){
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
