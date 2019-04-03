package com.lsh.demo.bootstone.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.jv.framework.domain.ResultDO;
import com.fliggy.jvopf.client.service.WorkOrderService;
import com.fliggy.jvopf.domain.object.WorkOrderContactDO;
import com.fliggy.jvopf.domain.object.WorkOrderFileDO;
import com.fliggy.jvopf.domain.object.WorkOrderUpdateDO;
import com.fliggy.jvopf.domain.object.WorkorderRemarkDO;
import com.fliggy.jvopf.domain.query.MultiConQueryImpWorkOrderDO;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import com.lsh.demo.bootstone.web.common.ViewResult;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * Created by lsh on 2019/2/13.
 * @author
 */
@RestController
@RequestMapping("/api/dapf")
@Component
public class ProjectOrderController {

    @Reference
    private WorkOrderService service;

    /**
     * 查询工单状态列表--原有方法
     */
    @PostMapping("/list")
    public ViewResult getWorkOrderList( ){
        ArrayList<String> list = Lists.newArrayList("hotel_2018011222141413_65","hotel_2018501220143330_10","hotel_2018011222141413_66");
        return ViewResult.copyResult(service.getWorkOrderList(list));
    }

    /**
     * 查询工单详情
     */
    @GetMapping("/query")
    public ViewResult query(@RequestParam(name = "orderId")  Long orderId){
        return ViewResult.success(service.getWorkOrderById(orderId).getModule());
    }


    /**
     * 查询工单列表 -- 运维平台
     */
    @RequestMapping("/query/multiCondition")
    public ViewResult getMultiConList(@RequestBody(required = false) MultiConQueryImpWorkOrderDO multiConQueryImpWorkOrderDO) {
        return ViewResult.copyResult(service.getMultiConList(multiConQueryImpWorkOrderDO));
    }

    /**
     * 查询所有设备商
     */
    @GetMapping("/query/equproducer")
    public ViewResult queryEquProducer(){
        return ViewResult.success(service.getEquProducer());
    }

    /**
     * 动态添加联系人
     */
    @PostMapping("/add/contact")
    public ViewResult addContact(@RequestBody WorkOrderContactDO contact){
        contact.setBucId("123");
        contact.setBucName("system123");
        return ViewResult.copyResult(service.addContact(contact));
    }

    /**
     * 添加备注
     */
    @PostMapping("/add/remark")
    public ViewResult addWorkOrderRemark(@RequestBody WorkorderRemarkDO remarkDO){
        if(null == remarkDO || StringUtils.isBlank(remarkDO.getRemark())){
            return null;
        }
        remarkDO.setBucId("123");
        remarkDO.setBucName("system123");
        return ViewResult.copyResult(service.addReamrk(remarkDO));
    }


    /**
     * 工单状态修改
     * @param updateDO
     */
    @PostMapping("/updateStatus")
    public ViewResult updateStatus(@RequestBody WorkOrderUpdateDO updateDO) {
        updateDO.setBucId("123");
        updateDO.setBucName("system123");
        return ViewResult.copyResult(service.updateStatus(updateDO));
    }

    /**
     * 工单详情保存
     * @param updateDO
     */
    @PostMapping("/save")
    public ViewResult saveWorkOrder(@RequestBody WorkOrderUpdateDO updateDO) {
        updateDO.setBucId("123");
        updateDO.setBucName("system123");
        return ViewResult.copyResult(service.saveWorkOrder(updateDO));
    }


    /**
     * 下载文件
     */
    @RequestMapping("/file/downloadFile")
    public ViewResult downloadPhoto(@RequestParam("orderId") Long orderId,@RequestParam("fileName")String fileName,@RequestParam("fileType") String fileType){
        WorkOrderFileDO fileDO = new WorkOrderFileDO();
        fileDO.setOrderId(orderId);
        fileDO.setFileName(fileName);
        fileDO.setFileType(fileType);
        fileDO.setBucId("123");
        fileDO.setBucName("system123");
        ResultDO<String> result = service.downloadFile(fileDO);
        if(result.isSuccess() && null != result.getModule()){
            return ViewResult.success(result.getModule());
        }
        return ViewResult.copyResult(result);
    }

    /**
     * 上传文件
     */
    @PostMapping("/file/uploadFile")
    public ViewResult uploadPhoto(WorkOrderFileDO fileDO, MultipartFile file){
        if(null == file || null ==fileDO || null == fileDO.getOrderId()){
            return ViewResult.fail("","参数异常");
        }
        fileDO.setBucId("123");
        fileDO.setBucName("system123");
        try {
            fileDO.setData(file.getBytes());
            fileDO.setOriginalFilename(file.getOriginalFilename());
        }catch (Exception e){
            BootStoneLog.bootStone.error("uploadFile",e);
            return null;
        }
        return ViewResult.copyResult(service.uploadFile(fileDO));
    }

    /**
     * 删除文件
     */
    @RequestMapping("/file/delete")
    public ViewResult deleteFile(@RequestBody WorkOrderFileDO fileDO) {
        fileDO.setBucId("123");
        fileDO.setBucName("system123");
        return ViewResult.copyResult(service.deleteFile(fileDO));
    }

}
