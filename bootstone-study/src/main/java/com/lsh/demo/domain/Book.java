package com.lsh.demo.domain;

import java.time.LocalDate;

/**
 * Created by lsh on 2019/3/7.
 *
 * 测试参数的传递
 *
 * 2019年3月7日 todo
 * 思考1：
 * 以localdate 接收 mysql 的date类型
 * 由于对方dubbo为2.5.3 无法正常接收localdate
 * 如何优雅的返回String
 *
 * 方案1：降级处理 改为sql.date
 */
public class Book {

    private String name;

    private String price;

    private String nameString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNameString() {
        return name;
    }

    public void setNameString(String nameString) {
        this.nameString = name;
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setName("124");

        book.setNameString("Stringname");

        System.out.println(book.toString());
        //不能行

        LocalDate  localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println(localDate.toString());
    }
}
