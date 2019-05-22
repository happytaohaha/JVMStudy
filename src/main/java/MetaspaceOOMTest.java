import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname MetaspaceOOMTEST
 * @Author happytaohaha
 * @Date 2019/5/21
 * @Description 原空间
 */
public class MetaspaceOOMTest {

    static class OOMTest{}
    public static void main(String[] args) {
        int i = 0;

        try {

            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable throwable){
            System.out.println("************多少次后发生了异常"+ i);
            throwable.printStackTrace();
        }
    }
}
