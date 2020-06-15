package spring20200603;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by lsh on 2020-06-04.
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<Integer> consumer = (x) -> {
            int num = x * 2;
            System.out.println(num);
        };

        Consumer<Integer> consumer1 = (x) -> {
            int num = x * 3;
            System.out.println(num);
        };
        Consumer<Integer> consumer2 = (x) -> {
            int num = x * 4;
            System.out.println(num);
        };
        //
        consumer1.andThen(consumer2).accept(10);
    }


    /**
     * 筛选大于23的lisi
     */
    @Test
    public void packageStu() {
        List<NoXmlBean> list = new ArrayList<>();
        Consumer<NoXmlBean> consumer = x -> {
            if (x.getName().equals("lisi")) {
                list.add(x);
            }
        };
        consumer = consumer.andThen(
                x -> list.removeIf(y -> y.getAge() < 23)
        );
        Stream.of(
                new NoXmlBean(21, "zhangsan"),
                new NoXmlBean(22, "lisi"),
                new NoXmlBean(23, "wangwu"),
                new NoXmlBean(24, "wangwu"),
                new NoXmlBean(23, "lisi"),
                new NoXmlBean(26, "lisi"),
                new NoXmlBean(26, "zhangsan")
        ).forEach(consumer);
        System.out.println(list.toString());
    }
}


