import java.nio.ByteBuffer;

/**
 * @Classname DirectBufferMemoryDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 分配到native os的本地内存
 * 这个错误是 本地内存满了导致程序对象崩溃
 * 默认 本地内存  最大   1/4
 * JVM 和  直接内存  总共 1/4
 * 配置参数：    直接内存5m
 * 故障现象：   Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer bb =ByteBuffer.allocateDirect( 6* 1024 * 1024);
    }
}
