package com.lsh.demo.bootstone.web.common.request;

import lombok.Data;

import java.util.List;

/**
 * Created by LSH on 2020/6/14 - 16:54.
 * <p>
 * declaration :
 */
@Data
public class SaveStu {

   private String name;
   private List<QiQiStu> stus;


}
