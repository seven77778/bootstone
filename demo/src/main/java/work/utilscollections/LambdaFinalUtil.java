package work.utilscollections;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 管道的功能包括：Filter（过滤）、Map(映射)、sort(排序）等
 * <p>
 * 如果返回值是Stream，那么是惰性求值；
 * 如果返回值是另一个值或为空，那么就是及早求值
 * <p>
 * map -- 转换功能，内部就是Function接口。惰性求值
 * filter -- 顾名思义，起过滤筛选的作用。内部就是Predicate接口。惰性求值。
 * flatMap -- 将多个Stream合并为一个Stream。惰性求值
 * max & min -- 我们经常会在集合中求最大或最小值，使用流就很方便。及早求值。
 * collect(Collectors.toList()) -- 将流转换为list。还有toSet()，toMap()等。及早求值。
 * count -- 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。及早求值
 * <p>
 * reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count 、 min 和 max 方 法，
 * 因为常用而被纳入标准库中。事实上，这些方法都是 reduce 操作。及早求值。
 *
 * mapToInt
 */
public class LambdaFinalUtil {
    List<Money> list = Arrays.asList(
            new Money("aaa", 11, "2020-01-01", "2020-01-05", "1"),
            new Money("bbb", 14, "2020-01-02", "2020-01-09", "2"),
            new Money("ccc", 66, "2020-01-03", "2020-01-02", "3")
    );

    List<Student> students = Arrays.asList(
        new Student("路飞", 22, 175),
        new Student("红发", 40, 180),
        new Student("aaa", 333, 180),
        new Student("白胡子", 50, 185));

    /**
     * 找出list中年龄最小的
     */
    @Test
    public void testMapToInt(){
        int res = students.stream().mapToInt(Student::getAge).min().getAsInt();
        System.out.println(res);

        //正确姿势
        OptionalInt optionalInt = students.stream().mapToInt(Student::getAge).min();
        if(optionalInt.isPresent()){
            System.out.println(optionalInt.getAsInt());
        }
    }


    /**
     * 利用 map + return + collectors.toList
     * map 是映射，转换
     */
    @Test
    public void testGetListByReturn() {
        List<String> res = list.stream().map(money -> {
            return money.getName();
        }).collect(Collectors.toList());
        System.out.println(res);
        /**
         * 以上可以缩写为
         * List<String> res = list.stream().map(Money::getName).collect(Collectors.toList());
         */
    }

    /**
     * 调用Stream.of的静态方法将两个list转换为Stream，再通过flatMap将两个流合并为一个
     */
    @Test
    public void testflatMap() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));
        List<Student> studentList = Stream.of(students,
                Arrays.asList(new Student("艾斯", 25, 183),
                        new Student("雷利", 48, 176)))
                .flatMap(students1 -> students1.stream()).collect(Collectors.toList());
        System.out.println(studentList);
    }


    /**
     * list中找出最大或最小，同mapToInt
     */
    @Test
    public void testMaxMin() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));
        Optional<Student> max = students.stream()
                .max(Comparator.comparing(stu -> stu.getAge()));
        Optional<Student> min = students.stream()
                .min(Comparator.comparing(stu -> stu.getAge()));
        //判断是否有值
        if (max.isPresent()) {
            System.out.println(max.get());
        }
        if (min.isPresent()) {
            System.out.println(min.get());
        }
    }

    @Test
    public void testCount() {
        long count = students.stream().filter(s1 -> s1.getAge() < 45).count();
        System.out.println("年龄小于45岁的人数是：" + count);
    }


    @Test
    public void testReduce() {
        Integer reduce = Stream.of(1, 2, 3, 4, 100).reduce(0, (acc, x) -> acc - x);
        System.out.println(reduce);
    }

    /**
     * 分组，两个180一组
     */
    @Test
    public void testGroupBy(){
        Map<Integer, List<Student>> listMap =
                students.stream().collect(
                        Collectors.groupingBy(student -> student.getPrice()));
        System.out.println(listMap);

    }

    /**
     * partitioningBy -- 把students 按照price是否大于180 分为两组
     */
    @Test
    public void testPartitioningBy(){
        Map<Boolean, List<Student>> listMap = students.stream().collect(
                Collectors.partitioningBy(student -> student.getPrice()>180));
        System.out.println(listMap);
    }

    @Test
    public void testJoin(){
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));
        String names = students.stream()
                .map(Student::getName).collect(Collectors.joining("--","[","]"));
        System.out.println(names);
    }

    /**
     * 手动写一个map转换
     */
    @Test
    public void testmyself(){
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 22, 180));
        students.add(new Student("白胡子", 50, 185));

        Map<Integer, Object> res = students.stream().collect(Collectors.toMap(Student::getAge, Function.identity(),(x,y)->x));
        System.out.println(res);
        System.out.println(CollectionUtils.isEmpty(res));
    }

}
