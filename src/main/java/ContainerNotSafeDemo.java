import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Classname ContainerNotSafeDemo
 * @Author 严涛
 * @Date 2019/5/16
 * @Description 线程不安全的问题
 *          ArrayList
 *1.故障现象
 *          java.util.ConcurrentModificationException  并发修改异常
 *2.导致原因
 *      线程抢占修改  并发争抢修改导致，参考我们的花名册情况，
 *      一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常。并发修改异常。
 *3.解决方案
 *  3.1 new Vector<>();
 *  3.2 Collections.synchronizedlist(new ArrayList<>())
 *  3.3 CopyOnWriteArrayList 写时复制，读写分离
 *  写时复制
 *  CopyOnWrite 容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[]new Elements,然后新的容器Object[]new Elements里添加元素，添加完元素之后，再将原容器的引用指向新的容器setArray(newElements);这样做的好处是可以对copyonwrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何容器。所以copyonwrite容器是一种读写分离的思想，读和写不同的容器。
 *
 *4.优化建议(同样错误不犯第二次)
 *
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap()
        for(int i = 0; i < 30; i++){
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //解决方法1  这种会锁
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //解决方法2
        Set<String> set = new CopyOnWriteArraySet<>();
        for(int i = 0; i < 30; i++){
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        //Hashset底层源码是hashmap map<key,value> hashset但是不关心value，value是一个Object常量 present 当前的
        new HashSet<>().add("a");
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 3; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
