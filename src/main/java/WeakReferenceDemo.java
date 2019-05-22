import java.lang.ref.WeakReference;

/**
 * @Classname WeakReferenceDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 弱引用
 * 只  要原对象没有引用 被 GC 一定回收
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(weakReference.get());
        obj1 =null;
        System.gc();
        System.out.println(obj1);
        System.out.println(weakReference.get());
    }
}
