package com.lsh.demo.annolearn.builder_bug;

import lombok.Builder;

@Builder
public class BuilderBug {

    private String name;
    private String age;

    /**
     * mapstruct 进行转换的时候，父类有@builder，转换错误
     * 两个架包缺一不可 mapstruct 和 processer
     */


    public static void main(String[] args) {
        BuilderBugBuilder result = BuilderBug.builder().age("ss").name("haha");
        System.out.println(result);

    }
}
