import javafx.scene.effect.Light;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Classname ReferenceQueueDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 引用队列
 *   对象 在回收之前 放在引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=============");
        o1 = null;
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
