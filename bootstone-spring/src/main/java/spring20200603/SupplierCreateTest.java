package spring20200603;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by lsh on 2020-06-04.
 *
 *  SupplierCreateTest supplierCreateTest = new SupplierCreateTest();
 *
 *     public SupplierCreateTest get(){
 *         return supplierCreateTest;
 *     }
 *     轻轻松松写一个stackover
 */
public class SupplierCreateTest {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    SupplierCreateTest supplierCreateTest ;

    public SupplierCreateTest get(){
        return supplierCreateTest;
    }

    public SupplierCreateTest() {
        System.out.println("调用构造方法");
    }

    public static void main(String[] args) {

        Supplier<SupplierCreateTest> supplier = SupplierCreateTest::new;
        supplier.get().setName("123");
        /**
         * setName之后还是null
         * supplier.get() 调用构造方法
         */
        System.out.println(supplier.get().name);

        SupplierCreateTest instance = supplier.get();
        instance.setName("233");
        System.out.println(instance.name);
        SupplierCreateTest sc1 = supplier.get();
        SupplierCreateTest sc2 = supplier.get();
        System.out.println(sc1==sc2);
        /**
         * 返回false，说明创建的对象不一致
         */
    }


    @Test
    public void testFunctionExpression(){
        List<NoXmlBean> list = Arrays.asList(
                new NoXmlBean("aaa"),
                new NoXmlBean("bbb"),
                new NoXmlBean("ccc")
        );
        list.forEach(System.out::println);
        list.forEach(NoXmlBean::run);
    }

}
