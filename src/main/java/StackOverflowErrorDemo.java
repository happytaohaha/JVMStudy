/**
 * @Classname StackOverflowErrorDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description  递归栈溢出
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {

    }

    private static void stackOverFlow(){
        throw new StackOverflowError();  //Error  Java 虚拟机错误  OutMemoryError   错误
    }
}
