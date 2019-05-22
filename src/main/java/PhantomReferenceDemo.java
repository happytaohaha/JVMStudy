import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Classname PhantomReferenceDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 虚引用
 *phantomReference  get方法总是null
 * java 提供了4种引用
 *
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
            Object o1 = new Object();
            ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
            PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
            System.out.println(o1);
            System.out.println(phantomReference.get());
            System.out.println(referenceQueue.poll());

            System.out.println("=============");
            o1 = null;
            System.gc();

            System.out.println(o1);
            System.out.println(phantomReference.get());
            System.out.println(referenceQueue.poll());



    }
}
