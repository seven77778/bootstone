package com.lsh.demo.bootstone.dao.mysql;

import lombok.Data;

import java.sql.Date;
import java.sql.PseudoColumnUsage;
import java.time.LocalDateTime;

/**
 * Created by lsh on 2019-11-29.
 * <p>
 * 属性组装到一起，迎接sql返回的值
 * <p>
 * dao映射类中date 引用的是java.util.Date，就会产生CDT 和 CST的问题，导致CDT在1989-1991之间
 * 的日期少一天
 * 换成localdatetime没问题
 * java.sql.Date也没问题
 */
@Data
public class Stu {

    private String name;
    private String id;
    private String age;
    private String grade;
    private String address;
    private LocalDateTime createTime;
    private Date createTime1;
    private LocalDateTime updateTime;
    private Integer version;
    /**
     * 不是sql字段，用来保存对比结果
     */
    private boolean sameFlag;

    public Stu() {
    }

    public Stu(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Stu(String name, String id, String age, String grade, String address, LocalDateTime createTime, LocalDateTime updateTime) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
        this.address = address;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Stu(String name, String age, String address, String id) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
    }

    public static void main(String[] args) {
        System.out.println( (System.currentTimeMillis()/1000) % 30);
    }

}
