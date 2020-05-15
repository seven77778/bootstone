package work.utils;

import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lsh on 2020-05-15.
 *
 * 找出list中重复的值
 */
public class FindListRepeatValue {

    @Test
    public void testFind(){
        List<Stu> lists = Lists.newArrayList();
        lists.add(new Stu("1001","哈哈1"));
        lists.add(new Stu("1002","哈哈2"));
        lists.add(new Stu("1003","哈哈3"));
        lists.add(new Stu("1001","哈哈1"));
        //找出重复的stu
        List<String> list =lists.stream().
                collect(Collectors.groupingBy(Stu::getId,Collectors.counting()))
                .entrySet().stream()
                .filter(entry->entry.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(list.toString());

    }


    @Test
    public void testNumberNoRepeat(){
        List<Integer> number = Arrays.asList(1, 5, 3, 2, 1, 9, 3, 6, 7, 5);
        // 筛选不重复的
        List<Integer> unrepeated = number.stream()
                .filter(
                        t1 -> number.stream().filter(t2 -> t1.equals(t2)).count() <= 1
                )
                .collect(Collectors.toList());
        System.out.println("unrepeated -> " + unrepeated);
    }

    @Test
    public void testNumberRepeat(){
        List<Integer> number = Arrays.asList(1, 5, 3, 2, 1, 9, 3, 6, 7, 5);
        // 筛选重复的,.distinct()->去重
        List<Integer> unrepeated = number.stream()
                .filter(
                        t1 -> number.stream().filter(t2 -> t1.equals(t2)).count() > 1
                )
                .distinct().collect(Collectors.toList());
        System.out.println("repeated -> " + unrepeated);
    }


    @Data
    private class Stu{
        private String id;
        private String name;

        public Stu(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
