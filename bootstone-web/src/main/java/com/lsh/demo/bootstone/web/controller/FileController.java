package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.domain.object.DeviceImportVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.utils.ExcelReader;

import java.util.List;

/**
 * Created by lsh on 2020-04-22.
 *
 * http://localhost:8084/img/fileUpload.html
 *
 */
@RestController
@Component
@RequestMapping("/file")
public class FileController {

        @PostMapping("/excel")
    public String readExcel(MultipartFile file) {
        // 检查前台数据合法性
        if (null == file || file.isEmpty()) {
            return "file is null";
        }
        try {
            // 解析Excel
            List<DeviceImportVO> parsedResult = ExcelReader.readExcel(file);
            // todo 进行业务操作

            System.out.println(parsedResult);
            return "success";
        } catch (Exception e) {
            return "err";
        }
    }

}
