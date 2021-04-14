package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.web.common.request.BootStoneRequest;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CDT：为夏令时时间（中华人民共和国在1986年~1991年实行了夏令时制度，每年夏令时实行时间如下：
 * 1986年5月4日至9月14日（1986年因是实行夏令时的第一年，从5月4日开始到9月14日结束）
 * 1987年4月12日至9月13日，
 * 1988年4月10日至9月11日，
 * 1989年4月16日至9月17日，
 * 1990年4月15日至9月16日，
 * 1991年4月14日至9月15日。
 * ）；CST：为美国、澳大利亚、古巴或中国的标准时间。
 * 3. CST的时间和CDT的时间是相差了13个小时。
 * 解决方案：
 * 常用的CMT+8的时区设置，并不兼容夏令时。
 * 而使用TimeZone = Asia/Shanghai，则会兼容夏令时。
 * 当mysql的url，修改为配置serverTimezone=Asia/Shanghai；解决了插入数据库时间错误问题。
 * 但是，当返回给前端的时候，设置JsonFormat的timeZone的时候，也需要使用此配置：@JsonFormat(timezone = “Asia/Shanghai”, pattern = “yyyy/MM/dd”)；这样才可以保证前端的数据展示正确
 */
public class TimeZoneTest extends BaseTest{


    @Test
    public void test()throws Exception{
        BootStoneRequest rq = new BootStoneRequest();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat2.parse("2021-06-10");
        rq.setDate(start);
        System.out.println(rq);


        BootStoneRequest rq2 = new BootStoneRequest();
        DateFormat dateFormat22 = new SimpleDateFormat("yyyy-MM-dd");
        Date start2 = dateFormat22.parse("1988-06-10");
        rq2.setDate(start2);
        //start2是CDT，CDT
        System.out.println(rq2);
    }




}
