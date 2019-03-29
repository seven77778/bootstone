package com.lsh.demo.bootstone.study.system.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lsh on 2019/3/7.
 */
public class SystemUtilTest {

    /**
     * System.co
     */
    @Test
    public void test1(){
        int[] arr = {1,2,3};
        int[] brr = new int[]{};

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(brr));

        System.arraycopy(arr,0,brr,0,arr.length-1);
        System.out.println(Arrays.toString(brr));
    }

}
