package com.lsh.demo.basic.lambda;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Created by lsh on 2019-07-17.
 *
 * stream 用法合集
 *
 */
public class AllStreams {

    @Test
    public void test1(){
        Stream<String> stream1 = Stream.of("A", "B", "C");
        System.out.println("stream1:" + stream1.collect(joining()));// R -> String
    }

    @Test
    public void test2(){
        String[] values = new String[]{"A", "B", "C"};
        Stream<String> stream2 = Stream.of(values);
        System.out.println("stream2:" + stream2.collect(joining()));
    }

    @Test
    public void test3(){
        String[] values = new String[]{"A", "B", "C"};
        Stream<String> stream3 = Arrays.stream(values);
        System.out.println("stream3:" + stream3.collect(joining()));
    }

    @Test
    public void test4(){
        List<String> list = Arrays.asList("A", "B", "C");
        Stream<String> stream4 = list.stream();
        System.out.println("stream4:" + stream4.collect(joining()));
    }

    @Test
    public void test5(){
        Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C"));
        Stream<String> stream5 = set.stream();
        System.out.println("stream5:" + stream5.collect(joining()));
    }

    @Test
    public void test6(){
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        Stream<String> stream6 = map.values().stream();
        System.out.println("stream6:" + stream6.collect(joining()));
    }

    @Test
    public void test7(){
        Stream<String> stream7 = Stream.iterate("A", e -> String.valueOf((char) (e.charAt(0) + 1))).limit(3);
        System.out.println("stream7:" + stream7.collect(joining()));
    }

    private Pattern pattern = Pattern.compile("\\W");

    @Test
    public void test8(){
        String value = "A B C";
        Stream<String> stream8 = pattern.splitAsStream(value);
        System.out.println("stream8:" + stream8.collect(joining()));
    }

    @Test
    public void test9(){
        try {
            Stream<String> stream9 = Files.lines(Paths.get("d:/data.txt"));
            System.out.println("stream9:" + stream9.collect(joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test10(){
        Stream<String> stream10 = Stream.generate(() -> "A").limit(3);
        System.out.println("stream10:" + stream10.collect(joining()));
    }

}
