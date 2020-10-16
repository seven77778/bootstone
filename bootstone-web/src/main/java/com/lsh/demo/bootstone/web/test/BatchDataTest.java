package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.dao.mysql.BatchDataService;
import com.lsh.demo.bootstone.dao.mysql.Stu;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootStoneWebApplication.class})// 指定启动类
public class BatchDataTest {

    @Resource
    private BatchDataService batchDataService;

    @Test
    public void batchUpdate() {
        List<Stu> lists = new ArrayList<Stu>() {
            private static final long serialVersionUID = 385437546496640496L;

            {
                add(new Stu("aaaa", "9991", "地址1", "1"));
                add(new Stu("bbbb", "9992", "地址2", "2"));
                add(new Stu("cccc", "9995", "地址5", "5"));
            }
        };
        int result = batchDataService.batchUpdate(lists);
        System.out.println(result);
    }


    @Test
    public void batchSelect() {
        List<Integer> ids = Lists.newArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<Stu> stus = batchDataService.batchSelect(ids);
        System.out.println(stus);
    }

    @Test
    public void duplicateInsert() {
        int result = batchDataService.duplicateInsert("woshishei", 11);
        System.out.println(result);
    }

    /**
     * 根据id，没有的会新增，已有的执行更新
     */
    @Test
    public void batchDuplicateInsert() {
        List<Stu> stus = Lists.newArrayList();
        Stu stu1 = new Stu("xx1","12","哈哈哈111","21");
        stu1.setUpdateTime(LocalDateTime.now());
        Stu stu2 = new Stu("xx2","13","哈哈哈","1");
        stus.add(stu1);
        stus.add(stu2);
        int result = batchDataService.batchDuplicateInsert(stus);
        System.out.println(result);
    }

    /**
     * 查询对比两列的结果,结果赋值给 sameFlag
     */
    @Test
    public void testQueryTheSame(){
        List<Stu> res = batchDataService.selectNotSame();
        System.out.println(res);
    }
}
