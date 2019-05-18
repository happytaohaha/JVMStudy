
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}
/**
 * @Classname AtomicReferenceDemo
 * @Author 严涛
 * @Date 2019/5/16
 * @Description 原子引用
 * 总结： 只能进行一次修改
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("Z3", 22);
        User l4 = new User("L4",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+ atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+ atomicReference.get());
    }
}
