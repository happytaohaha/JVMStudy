/**
 * @Classname StrongReferenceDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 强引用代码  new 创建默认   强引用
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();  //默认创建强引用
        Object obj2 = obj1; //obj2 引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);//obj2   没有受到影响
    }
}
