/**
 * @Classname GCRootDemo
 * @Author 严涛
 * @Date 2019/5/18
 * @Description GCRoot
 * 在Java中，可以作为GC Roots的对象有：
 * 1.虚拟机栈（栈中的本地变量表中引用的对象）
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用的对象；
 * 4.本地方法栈中JNI(即一般所说的Native方法)中的引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100*1024*1024];


    public static void m1(){
        //1.虚拟机栈（栈中的本地变量表中引用的对象）
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
