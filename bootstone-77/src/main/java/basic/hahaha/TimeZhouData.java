package basic.hahaha;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class TimeZhouData {

    private String name;
    private List<MethodData> methods;




    @Data
   static class MethodData{
        private String name;
        private String time;

        public MethodData(String name, String time) {
            this.name = name;
            this.time = time;
        }

        public MethodData() {
        }

        private MethodData methodData;
    }

    public static void main(String[] args) {
        TimeZhouData timeZhouData = new TimeZhouData();
        timeZhouData.setName("callBankend");
        MethodData methodData1 = new MethodData();
        methodData1.setName("GET");
        methodData1.setTime("0.112ms");
        timeZhouData.setMethods(Lists.newArrayList(methodData1));

        MethodData methodDatacreate = new MethodData("createOrder","0.115ms");
        MethodData methodDatagetitem = new MethodData("getitem","220.115ms");

        methodDatacreate.setMethodData(methodDatagetitem);
        methodData1.setMethodData(methodDatacreate);
        System.out.println(JSON.toJSON(timeZhouData));


    }
}
