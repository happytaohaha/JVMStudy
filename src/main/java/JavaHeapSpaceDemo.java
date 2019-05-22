import java.util.Random;

/**
 * @Classname JavaHeapSpaceDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 堆内存溢出  OutOfMemoryError   错误
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "atguigu";

        while (true){
            str += str +new Random().nextInt(11111111) + new Random().nextInt(22222222);
            str.intern();
        }
    }
}
