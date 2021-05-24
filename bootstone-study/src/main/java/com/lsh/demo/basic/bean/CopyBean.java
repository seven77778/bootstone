package com.lsh.demo.basic.bean;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by LSH on 2019/5/3 - 9:31.
 * <p>
 * declaration : 复制bean属性的方法集合
 */
@Data
public class CopyBean {

    public static void main(String[] args) {
        MakeRequest makeRequest = new MakeRequest();
        makeRequest.setAge("16");
        makeRequest.setName("haha");
        makeRequest.setSum(99);
        makeRequest.setCheck(true);

        ReadRequest readRequest = new ReadRequest();
        BeanUtils.copyProperties(makeRequest,readRequest);
        System.out.println(readRequest);
        /*
        CopyBean.ReadRequest(name=haha, age=16, sum=0.0)

        why sum is 0.0 readRequest中是double啊
         */
    }


    @Data
    public static class MakeRequest{
        private String name;
        private String age;
        public Integer sum;
        public boolean check;

    }


    @Data
    public static class ReadRequest{
        private String name;
        private String age;
        private int sum;
    }
}
