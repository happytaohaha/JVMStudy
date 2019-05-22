import java.lang.ref.SoftReference;

/**
 * @Classname SoftRefernceDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 软引用
 * 考虑内存
 * 内存够用，不会被回收， 内存不够用会被回收
 */
public class SoftRefernceDemo {

    /**
     * 内存够用不会被回收
     */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * JVM配置，故意产生大对象配置小的内存，让他进行垃圾回收
     * -Xms5m  初始堆内存 -Xmx5m  最大堆内存-XX:+PrintGCDetails
     */
    public static void softRef_Memory_Not_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        try {
            byte[] bytes = new byte[30*1024*1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) {
        softRef_Memory_Not_Enough();
    }
}
