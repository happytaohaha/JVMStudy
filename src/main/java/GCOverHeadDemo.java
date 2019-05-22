import java.util.ArrayList;
import java.util.List;

/**
 * @Classname GCOverHeadDemo
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description GCOverHead 错误
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable throwable){
            System.out.println("************i:"+i);
            throwable.printStackTrace();
        }

    }
}
