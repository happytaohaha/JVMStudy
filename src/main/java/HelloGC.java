/**
 * @Classname HelloGc
 * @Author 严涛
 * @Date 2019/5/18
 * @Description HelloGC
 */
public class HelloGC {
    public static void main(String[] args) {
        System.out.println("*********HelloGC");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
