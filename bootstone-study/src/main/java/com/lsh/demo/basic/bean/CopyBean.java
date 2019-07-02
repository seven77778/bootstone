package com.lsh.demo.basic.bean;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by LSH on 2019/5/3 - 9:31.
 * <p>
 * declaration : 复制bean属性的方法集合
 */
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

        why sum is 0.0
         */
    }


    @Data // @date对内部类无作用？
    public static class MakeRequest{
        private String name;
        private String age;
        private int sum;
        private boolean check;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }


    @Data
    public static class ReadRequest{
        private String name;
        private String age;
        private double sum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }
    }
}
