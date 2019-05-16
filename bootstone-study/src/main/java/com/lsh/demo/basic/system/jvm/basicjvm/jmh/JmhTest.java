package com.lsh.demo.basic.system.jvm.basicjvm.jmh;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 10, time = 5)
public class JmhTest {

    @Benchmark
    @Threads(8)
    public void runWithCalendar(){
        Calendar calendar = Calendar.getInstance();
    }

    @Benchmark
    @Threads(8)
    public void runWithSys(){
        long res = System.currentTimeMillis() / (24 * 3600 * 1000);
    }

    @Benchmark
    @Threads(8)
    public void runWithDate(){
        Date res = new Date();
    }

    /**
     * @BenchmarkMode(Mode.AverageTime)
     * 平均操作时间
     * Benchmark                Mode  Cnt    Score   Error  Units
     * JmhTest.runWithCalendar  avgt   10  682.671 ± 3.157  ns/op
     * JmhTest.runWithDate      avgt   10    7.415 ± 0.679  ns/op
     * JmhTest.runWithSys       avgt   10    7.019 ± 0.146  ns/op
     * */


    @Test
    public void jmhTest() throws Exception {
        Options options = new OptionsBuilder().include(JmhTest.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    @Test
    public void test(){
        long time = System.nanoTime();
        long time1 = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(System.nanoTime() - time);
        System.out.println(System.currentTimeMillis() - time1);
        System.out.println(System.currentTimeMillis()/(24*3600*1000));
        System.out.println(new Date());
    }
}
