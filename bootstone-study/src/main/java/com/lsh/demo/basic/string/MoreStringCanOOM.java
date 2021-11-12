package com.lsh.demo.basic.string;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

public class MoreStringCanOOM {
    /**
     * 不停的创建string是否会oom
     *
     * -XX:+PrintGCDetails -Xmx5m -Xms5m -Xmn5m -XX:MetaspaceSize=5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\oomdum
     *
     * 结果是不会oom
     *
     */
    public static void main(String[] args) {
        String str = "abc";
        int i=0;
        List list = Lists.newArrayList();

        while (true){
//            str=str+i++;
            list.add(str+i);

        }
    }


    /**
     * 会oom
     */
    @Test
    public void test1(){
        StringBuilder sb = new StringBuilder();
        int a=0;
        while (true){
            sb.append(a++);
        }
    }
}
