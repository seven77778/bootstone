package com.lsh.demo.bootstone.dao.mysql;

import lombok.Data;

/**
 * Created by lsh on 2019-11-29.
 *
 * 属性组装到一起，迎接sql返回的值
 *
 */
@Data
public class Stu {

    private String name;
    private String id;
    private String age;

    private String stuName;
    private String score;
    private String address;
    private String address2;
    private String result;

}
