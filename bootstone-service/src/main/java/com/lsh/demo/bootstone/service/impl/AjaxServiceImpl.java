package com.lsh.demo.bootstone.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsh.demo.bootstone.service.AjaxService;
import com.lsh.demo.bootstone.service.domain.request.AjaxHotelRequest;
import com.lsh.demo.bootstone.service.domain.response.BootResponse;
import com.lsh.demo.bootstone.service.domain.vo.AjaxHotel;
import com.lsh.demo.bootstone.service.domain.vo.AjaxHotelData;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lsh on 2020-06-15.
 */
@Component
public class AjaxServiceImpl implements AjaxService {

    private AjaxHotel hotel = new AjaxHotel();

    @Override
    public BootResponse<AjaxHotel> queryHotel(AjaxHotelRequest rq) {

        if (!CollectionUtils.isEmpty(hotel.getData())) {
            return BootResponse.ofSuccess(hotel);
        }

        AjaxHotel ajaxHotel = queryFromJson(rq.getHotelName());
        if (!CollectionUtils.isEmpty(ajaxHotel.getData())) {
            return BootResponse.ofSuccess(ajaxHotel);
        } else {
            return BootResponse.ofFailure("查询失败");
        }
    }

    @Override
    public BootResponse addHotel(AjaxHotelRequest rq) {
        if (StringUtils.isAnyBlank(rq.getHotelName(), rq.getId(), rq.getTel())) {
            return BootResponse.ofFailure("酒店name,id,tel不能为空");
        }
        AjaxHotel ajaxHotel;
        if (CollectionUtils.isEmpty(hotel.getData())) {
            ajaxHotel = queryFromJson("");
        } else {
            ajaxHotel = hotel;
        }

        AjaxHotelData data = new AjaxHotelData();
        data.setHotelName(rq.getHotelName());
        data.setId(rq.getId());
        data.setTel(rq.getTel());
        if (null != rq.getCreateTime()) {
            data.setCreateTime(rq.getCreateTime());
        }
        if (null != rq.getHotelStatus()) {
            data.setHotelStatus(rq.getHotelStatus());
        }
        ajaxHotel.getData().add(data);
        hotel = ajaxHotel;
        return BootResponse.ofSuccess();

    }

    public AjaxHotel queryFromJson(String name) {
        String jsonStr = "";
        String path = AjaxServiceImpl.class.getClassLoader().getResource("hotel2.json").getPath();
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            List<AjaxHotelData> list = JSON.parseArray(jsonStr, AjaxHotelData.class);
            AjaxHotel ajaxHotel = new AjaxHotel();
            if (StringUtils.isNotBlank(name)) {
                List<AjaxHotelData> ss = list.stream().filter(x -> x.getHotelName().equals(name)).collect(Collectors.toList());
                ajaxHotel.setData(ss);
            } else {
                ajaxHotel.setData(list);
            }
            return ajaxHotel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testArray() {
        String jsonStr = "";
        String path = AjaxServiceImpl.class.getClassLoader().getResource("hotel2.json").getPath();
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            System.out.println(jsonStr);
            List<AjaxHotelData> list = JSON.parseArray(jsonStr, AjaxHotelData.class);

            List<AjaxHotelData> ss = list.stream().filter(x -> x.getHotelName().equals("测试酒店1")).collect(Collectors.toList());

            System.out.println(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonToCls() {
        String jsonStr = "";
        String path = AjaxServiceImpl.class.getClassLoader().getResource("hotel.json").getPath();

        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            System.out.println(jsonStr);
            AjaxHotel ajaxHotel = JSON.parseObject(jsonStr, AjaxHotel.class);
            System.out.println(ajaxHotel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
