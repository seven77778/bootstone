package com.lsh.demo.bootstone.web.test;

import com.google.common.collect.Lists;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.utils.ListSortUtil;
import work.utils.vo.UtilDateVo;
import work.utilscollections.UtilsCollections;

import java.time.LocalDate;
import java.util.List;

/**
 * util的测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})// 指定启动类
public class UtilTest {

    /**
     * list排序
     */
    @Test
    public void test(){
        List<UtilDateVo> list = Lists.newArrayList();
        UtilDateVo u1 = new UtilDateVo("111","2020-12-06","3", LocalDate.of(2020,12,6));
        UtilDateVo u2 = new UtilDateVo("222","2020-12-02","2", LocalDate.of(2020,12,5));
        UtilDateVo u3 = new UtilDateVo("333","2020-12-05","1", LocalDate.of(2020,12,4));
        UtilDateVo u4 = new UtilDateVo("444","2020-12-04","4", LocalDate.of(2020,12,9));
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        System.out.println(list);
        ListSortUtil.sortListBySort(list);
        System.out.println(list);
    }

    /**
     * [1, 2, 3, 6]  反转list
     * [6, 3, 2, 1]
     */
    @Test
    public void test1(){
        List<Integer> list = Lists.newArrayList(1,2,3,6);
        System.out.println(list);
        UtilsCollections.reverseList(list);
        System.out.println(list);
    }


}
