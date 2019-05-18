/**
 * @Classname TransmitDemo
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 值传递
 */
public class TransmitDemo {

        public static void test(String str)
        {
            str = str+"world";
        }

        public static void test(StringBuffer str)
        {
            str.append(" world");
        }

        public static void main(String[] args)
        {
            String  str1 = new String("hello");
            test(str1);
            System.out.println(str1);
            StringBuffer  str2 = new StringBuffer("hello");
            test(str2);
            System.out.println(str2);
        }



}
