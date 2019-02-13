package com.lsh.demo.bootstone.workorder;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.jv.framework.domain.ResultDO;
import com.fliggy.jvopf.client.service.WorkOrderService;
import com.fliggy.jvopf.domain.object.WorkOrderContactDO;
import com.fliggy.jvopf.domain.object.WorkOrderFileDO;
import com.fliggy.jvopf.domain.object.WorkOrderUpdateDO;
import com.fliggy.jvopf.domain.object.WorkorderRemarkDO;
import com.fliggy.jvopf.domain.query.MultiConQueryImpWorkOrderDO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lsh on 2019/2/13.
 * test
 *
 */
@Service
public class ProjectOrderService {

    @Reference
    WorkOrderService service;

    public ResultDO getorders(Long id){
        ResultDO result = service.queryWorkOrderById(id);
        System.out.println();
        return result;
    }

    public ResultDO getWorkOrderList(List<String> list) {

        return service.getWorkOrderList(list);
    }

    public ResultDO queryEquProducer() {
        return service.queryEquProducer();
    }

    public ResultDO addReamrk(WorkorderRemarkDO remarkDO) {
        return service.addReamrk(remarkDO);
    }

    public ResultDO uniteQuery(MultiConQueryImpWorkOrderDO multiConQueryImpWorkOrderDO) {
       return service.uniteQuery(multiConQueryImpWorkOrderDO);
    }

    public ResultDO updateStatus(WorkOrderUpdateDO updateDO) {
        return service.updateStatus(updateDO);
    }

    public ResultDO uniteUpdateOrder(WorkOrderUpdateDO updateDO) {
        return service.uniteUpdateOrder(updateDO);
    }

    public ResultDO queryWorkOrderById(Long orderId) {
        return service.queryWorkOrderById(orderId);
    }

    public ResultDO addContact(WorkOrderContactDO contact) {
        return service.addContact(contact);
    }

    public ResultDO downloadPhoto(Long orderId, String fileName, String fileType, HttpServletResponse httpResponse) {
        return service.downloadPhoto( orderId,  fileName,  fileType,  httpResponse);
    }

    public ResultDO uploadPhoto(WorkOrderFileDO fileDO) {
        return service.uploadPhoto(fileDO);
    }

    public ResultDO uploadFile(WorkOrderFileDO file) {
        return service.uploadFile(file);
    }

    public ResultDO downloadFile(Long orderId, String filePath, String fileName, HttpServletResponse response) {
        return service.downloadFile( orderId,  filePath,  fileName,  response);
    }

    public ResultDO deleteFile(WorkOrderFileDO file) {
        return service.deleteFile(file);
    }
}
