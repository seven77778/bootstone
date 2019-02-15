package com.lsh.demo.bootstone.web.controller;

import com.alibaba.jv.framework.domain.ResultDO;
import com.fliggy.jvopf.domain.object.WorkOrderContactDO;
import com.fliggy.jvopf.domain.object.WorkOrderFileDO;
import com.fliggy.jvopf.domain.object.WorkOrderUpdateDO;
import com.fliggy.jvopf.domain.object.WorkorderRemarkDO;
import com.fliggy.jvopf.domain.query.MultiConQueryImpWorkOrderDO;
import com.lsh.demo.bootstone.service.common.LogFactory;
import com.lsh.demo.bootstone.web.common.ViewResult;
import com.lsh.demo.bootstone.workorder.ProjectOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2019/2/13.
 * @author
 */
@RestController
@RequestMapping("/order")
@Component
public class ProjectOrderController {

    @Resource
    ProjectOrderService service;

    @GetMapping("/query")
    public ViewResult query(Long id){
        return ViewResult.success(service.getorders(id).getModule());
    }

    @PostMapping("/list")
    public ViewResult getWorkOrderList( ){
        List list = new ArrayList();
        list.add("hotel_2018011222141413_65");
        list.add("hotel_2018501220143330_10");
        list.add("hotel_2018011222141413_66");
        return ViewResult.copyResult(service.getWorkOrderList(list));
    }

    /**
     * 动态添加联系人
     */
    @PostMapping("/add/contact")
    public ViewResult addContact(@RequestBody WorkOrderContactDO contact){
        contact.setBucId("999999");
        contact.setBucName("lsh");
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
        remarkDO.setBucId("999999");
        remarkDO.setBucName("lsh");
        return ViewResult.copyResult(service.addReamrk(remarkDO));
    }

    /**
     * 查询工单列表 -- 运维平台
     */
    @RequestMapping("/query/multiCondition")
    public ViewResult getMultiConList(@RequestBody(required = false) MultiConQueryImpWorkOrderDO multiConQueryImpWorkOrderDO) {
        return ViewResult.copyResult(service.getMultiConList(multiConQueryImpWorkOrderDO));
    }

    /**
     * 工单状态修改
     * @param updateDO
     */
    @PostMapping("/updateStatus")
    public ViewResult updateStatus(@RequestBody WorkOrderUpdateDO updateDO) {
        updateDO.setBucId("999999");
        updateDO.setBucName("lsh");
        return ViewResult.copyResult(service.updateStatus(updateDO));
    }

    /**
     * 工单详情保存
     * @param updateDO
     */
    @PostMapping("/save")
    public ViewResult saveWorkOrder(@RequestBody WorkOrderUpdateDO updateDO) {
        return ViewResult.copyResult(service.saveWorkOrder(updateDO));
    }


    /**
     * 下载实施工单照片、文件等
     * @return
     */
    @RequestMapping("/file/downloadFile")
    public ViewResult downloadPhoto(@RequestParam("orderId") Long orderId,@RequestParam("fileName")String fileName,@RequestParam("fileType") String fileType,
                                    HttpServletResponse httpResponse){
        WorkOrderFileDO fileDO = new WorkOrderFileDO();
        fileDO.setOrderId(orderId);
        fileDO.setFileName(fileName);
        fileDO.setFileType(fileType);
        fileDO.setBucId("123");
        fileDO.setBucName("system123");
        ResultDO<byte[]> result = service.downloadPhoto(fileDO);
        if(result.isSuccess() && null != result.getModule()){
            httpResponse.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            try{
                OutputStream out = httpResponse.getOutputStream();
                out.write(result.getModule());
                out.flush();
            }catch (Exception e){
                LogFactory.bootStone.error("download_error",e);
                return ViewResult.fail("999","download_error");
            }
            return ViewResult.success(new ResultDO<>());
        }
        return ViewResult.copyResult(result);
    }

    /**
     * 上传照片
     */
    @PostMapping("/file/uploadFile")
    public ViewResult uploadPhoto(WorkOrderFileDO fileDO){
        return ViewResult.copyResult(service.uploadPhoto(fileDO));
    }

    /**
     *  ************二期临时保留功能*******************
     *  上传安装包 删除安装包 下载安装包
     */
    @PostMapping("/temp/upload")
    public ViewResult uploadFile( WorkOrderFileDO file) {
        return ViewResult.copyResult(service.uploadFile(file));
    }

    @RequestMapping("/temp/delete")
    public ViewResult deleteFile(@RequestBody WorkOrderFileDO file) {
        return ViewResult.copyResult(service.deleteFile(file));
    }
}
