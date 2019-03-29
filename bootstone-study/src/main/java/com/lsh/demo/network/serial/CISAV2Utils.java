package com.lsh.demo.network.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Description: The description of the class
 * @Author: wb-pgj358032
 * @CreateDate: 2018-07-18 17:05
 * @UpdateDate: 2018-07-18 17:05
 */
public class CISAV2Utils {
    /**计算验证值
     * @param dates
     * @return
     */
    public static byte[] calcCheckSum(byte[] dates){
        int sum=0;
        for (byte date : dates) {
            sum = (sum + date) & 0xff;
        }
        return padLeft(Integer.toHexString(sum).toUpperCase(),2,"0" ).getBytes(StandardCharsets.US_ASCII);
    }


    /**右补充
     * @param targe
     * @param strLen
     * @param value
     * @return
     */
    public static String padRight(String targe,int strLen,String value){
        String newstr = appendString(targe, value, strLen, false);
        return newstr;
    }

    /**左补充
     * @param targe
     * @param strLen
     * @param value
     * @return
     */
    public static String padLeft(String targe,int strLen,String value){
        String newstr = appendString(targe,value,strLen,true);
        return newstr;
    }
    /**
     * @param targe 需要补充的目标
     * @param appendValue 补充的内容
     * @param strLen 目标补充之后的总长度
     * @param StartOrEnd 是否补充在头或尾（true头，false尾）
     * @return
     */
    public  static String appendString(String targe,String appendValue,int strLen,boolean StartOrEnd){
        if (null==targe){
            targe=appendValue;
        }
        int targeLen=targe.length();
        if (targeLen>=strLen){
            return targe.substring(0,strLen);
        }
        while (targeLen<strLen){
            StringBuilder sb=new StringBuilder();
            if (StartOrEnd) {
                sb.append(appendValue).append(targe);
            }else {
                sb.append(targe).append(appendValue);
            }
            targe=sb.toString();
            targeLen=targe.length();
        }
        if (StartOrEnd){
            if (targeLen>strLen){
                targe=targe.substring(targeLen-strLen);
            }
        }else {
            if (targeLen>strLen){
                targe=targe.substring(0,strLen);
            }
        }
        return targe;
    }

    public static JSONObject getExtraValue(String extJsonString, String commonField) {
        if (extJsonString == null || extJsonString.trim().equals("")) {
            return null;
        }
        JSONObject hander = JSON.parseObject(extJsonString);
        if (hander == null || hander.isEmpty()) {
            return null;
        }
        return hander;

    }

    public static String getMessageByCode(String code) {
        String ret;
        switch (code) {
            case "00": ret = "OK"; break;
            case "01": ret = "Internal system error"; break;
            case "08": ret = "No link (addressed device not responding)"; break;
            default: ret = "UnKnown Error"; break;
        }
        return ret;
    }

    /**
     * 处理返回的byte 数组
     *
     * @param data
     * @return
     */
    public static String toReadableString(byte[] data) {
        if (null == data || data.length == 0) {
            return null;
        }
        int start = -1, end = -1;
        // 起始值STX 0x02索引 终止标识
        // 结束值ETX 0x03索引 取最后0x03终止, 无需终止标识
        boolean startTerminationFlag = false,endTerminationFlag = false;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0x02 && !startTerminationFlag) {
                start = i;
                startTerminationFlag =  true;
            }
            if (data[i] == 0x03 && !endTerminationFlag) {
                end = i;
                endTerminationFlag = true;
            }
            if(start!=-1 && end!=-1){
                break;
            }
        }
        if (start == -1 || end == -1) {
            return null;
        }
        //logger.info("> safLokUtils#toReadableString , data is :" + Arrays.toString(data) + ", start is " + start + ", end is :" + end);
        return new String(Arrays.copyOfRange(data, start + 1, end)).trim();
    }

}
