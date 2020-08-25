package com.lsh.demo.basic.datastructrue.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsh on 2020-06-05.
 */
public class JsonArrayToMap {


    public static void main(String[] args) {

        String str = "[\n" +
                "    {\n" +
                "        \"id\": \"123\",\n" +
                "        \"area\": \"444\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"123\",\n" +
                "        \"area\": \"xxx\"\n" +
                "    },\n";

        JSONArray array = JSONArray.parseArray(str);

        System.out.println(array);
         Map<String, String> hotelAddress = new HashMap<>();

        for(Object arr : array){
            hotelAddress.put( ((JSONObject)arr).getString("id"),((JSONObject)arr).getString("area"));
        }
        System.out.println(hotelAddress);
    }


    @Test
    public void test(){
        String str="[{\"explain\":\"酒店平均房价\",\"key\":\"roomPrice\",\"paramStatus\":\"1\",\"paramType\":\"2\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"预约入住等待提示时间\",\"key\":\"orderWaitTime\",\"paramStatus\":\"1\",\"paramType\":\"2\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启预授权:\",\"key\":\"openPreAuth\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"总杂费类型\",\"key\":\"totalIncidentalExpensesType\",\"paramStatus\":\"1\",\"paramType\":\"0\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"房杂费数值\",\"key\":\"totalIncidentalExpensesValue\",\"paramStatus\":\"1\",\"paramType\":\"0\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启在线选房:\",\"key\":\"openChooseRoomOnline\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"预授权提示信息\",\"key\":\"prepayCode\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"信用住提示信息:\",\"key\":\"alipayCode\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"扫脸预授权开关:\",\"key\":\"faceScanPreAuthFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"预授权押金日杂费:\",\"key\":\"incidentals\",\"paramStatus\":\"1\",\"paramType\":\"2\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启预约制卡:\",\"key\":\"openPreMakeCard\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"自助机背景图片:\",\"key\":\"imageUrl\",\"paramStatus\":\"1\",\"paramType\":\"0\",\"required\":\"1\",\"validationRule\":\"-\",\"value\":\"https://gw.alicdn.com/tfs/TB19bROeHSYBuNjSspiXXXNzpXa-1080-1475.jpg\"},{\"explain\":\"是否开启预约入住:\",\"key\":\"openPreCheckIn\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"杂费上限(预授权押金)\",\"key\":\"alipayIncidentalsMax\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启手机输入\",\"key\":\"openInputPhone\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"1\"},{\"explain\":\"分账比例\",\"key\":\"royaltyRate\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"最大入住人数\",\"key\":\"maxCheckinGuest\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"1\",\"validationRule\":\"%5E(%5B1-9%5D%7C10)%24\",\"value\":\"3\"},{\"explain\":\"早餐Code\",\"key\":\"breakfastCode\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"酒店政策\",\"key\":\"hotelHint\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否是测试酒店:\",\"key\":\"testHotelFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启预订号查询\",\"key\":\"reservationQueryFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"多订单开关\",\"key\":\"openMultiOrder\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启代订人姓名查询\",\"key\":\"nameQueryFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启手机号单独查询\",\"key\":\"phoneQueryFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启微信支付\",\"key\":\"openWechat\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"微信支付提示信息\",\"key\":\"weChatCode\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启独立支付\",\"key\":\"independentPay\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启独立核身\",\"key\":\"independentIdentify\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"暂不支持自助机办理优惠码配置\",\"key\":\"rateCode\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\" \"},{\"explain\":\"是否开启无卡核身\",\"key\":\"isNeedIdCardCheckIn\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启当面付\",\"key\":\"openAliOfflinePay\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启当面付扫脸支付\",\"key\":\"faceScanOfflinePayFlag\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"新增信用住\",\"key\":\"ttttt\",\"paramStatus\":\"1\",\"paramType\":\"0\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启菲住信用住2\",\"key\":\"openFeizhuCreditPay\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"1\"},{\"explain\":\"是否菲住支付宝开关:\",\"key\":\"openFeizhuAliPay\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"改名字是否开启菲住微信支付\",\"key\":\"openFeizhuWeixinPay\",\"paramStatus\":\"1\",\"paramType\":\"0\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启信用住资格校验\",\"key\":\"checkQualifications\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"1\"},{\"explain\":\"是否开启信用住签约:\",\"key\":\"openCreditSign\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"1\"},{\"explain\":\"酒店区域（杭旅上报用）\",\"key\":\"hotelRegion\",\"paramStatus\":\"1\",\"paramType\":\"1\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"\"},{\"explain\":\"是否开启无证办理\",\"key\":\"openNoCardCheck\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"菲住-是否支持蓝牙门锁\",\"key\":\"isSupportBluetooth\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"菲住-是否支持电子发票\",\"key\":\"isOpenInvoice\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"菲住HOS_是否开启保险\",\"key\":\"isSupportInsurance\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否分销平台酒店\",\"key\":\"isDistributorHotel\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"0\",\"validationRule\":\"\",\"value\":\"0\"},{\"explain\":\"是否开启活体检测\",\"key\":\"isSupportLiveness\",\"paramStatus\":\"1\",\"paramType\":\"3\",\"required\":\"1\",\"validationRule\":\"\",\"value\":\"1\"}]";

        JSONArray array = JSONArray.parseArray(str);

        System.out.println(array);

        for(Object arr : array){
            if(((JSONObject)arr).getString("key").equals("openNoCardCheck")){
                ((JSONObject)arr).put("value","u1100111");
            }
        }
        System.out.println(array);
    }
}
