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

import java.util.List;

/**
 * Created by lsh on 2019/2/13.
 * test
 */
@Service
public class ProjectOrderService {

    @Reference
    WorkOrderService service;

    public ResultDO getorders(Long id) {
        ResultDO result = service.getWorkOrderById(id);
        System.out.println();
        return result;
    }

    public ResultDO getWorkOrderList(List<String> list) {

        return service.getWorkOrderList(list);
    }

    public ResultDO getMultiConList(MultiConQueryImpWorkOrderDO multiConQueryImpWorkOrderDO) {
        return service.getMultiConList(multiConQueryImpWorkOrderDO);
    }

    public ResultDO getWorkOrderById(Long orderId) {
        return service.getWorkOrderById(orderId);
    }

    public ResultDO getEquProducer() {
        return service.getEquProducer();
    }

    public ResultDO addReamrk(WorkorderRemarkDO remarkDO) {
        return service.addReamrk(remarkDO);
    }


    public ResultDO updateStatus(WorkOrderUpdateDO updateDO) {
        return service.updateStatus(updateDO);
    }

    public ResultDO saveWorkOrder(WorkOrderUpdateDO updateDO) {
        return service.saveWorkOrder(updateDO);
    }

    public ResultDO addContact(WorkOrderContactDO contact) {
        return service.addContact(contact);
    }

    public ResultDO downloadPhoto(WorkOrderFileDO file) {
        return service.downloadPhoto(file);
    }

    public ResultDO uploadPhoto(WorkOrderFileDO fileDO) {
        return service.uploadPhoto(fileDO);
    }

    public ResultDO uploadFile(WorkOrderFileDO file) {
        return service.uploadFile(file);
    }

    public ResultDO downloadFile(WorkOrderFileDO file) {
        return service.downloadPhoto(file);
    }

    public ResultDO deleteFile(WorkOrderFileDO file) {
        return service.deleteFile(file);
    }
}
