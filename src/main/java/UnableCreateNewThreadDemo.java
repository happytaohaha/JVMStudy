/**
 * @Classname UnableCreateNewThreadDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 不能创建太多线程   linux  默认  一个进程  能够创1024个线程
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for(int i = 0; ; i++){

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"*******************");

            },String.valueOf(i)).start();
        }
    }
}
