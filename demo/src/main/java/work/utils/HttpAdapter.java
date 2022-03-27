package work.utils;

import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class HttpAdapter {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RestTemplate restLongTemplate;


    public String doGet(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        // 获取状态对象
        HttpStatus httpStatus = responseEntity.getStatusCode();
        // 获取状态码
        int statusCodeValue = responseEntity.getStatusCodeValue();
        // 获取headers
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        // 获取body
        String result = responseEntity.getBody();
        BootStoneLog.bootStone.info("状态码：{}，body：{}", httpStatus, result);
        return result;
    }


    public String doGetLong(String url) {
        ResponseEntity<String> responseEntity = restLongTemplate.getForEntity(url, String.class);
        // 获取状态对象
        HttpStatus httpStatus = responseEntity.getStatusCode();
        // 获取状态码
        int statusCodeValue = responseEntity.getStatusCodeValue();
        // 获取headers
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        // 获取body
        String result = responseEntity.getBody();
        BootStoneLog.bootStone.info("状态码：{}，body：{}", httpStatus, result);
        return result;
    }


    public String doPost(String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> param = Maps.newHashMap();
        HttpEntity httpEntity = new HttpEntity(param, httpHeaders);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            BootStoneLog.bootStone.error("http error", e);
        }
        return null;
    }

    public String doPostLong(String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> param = Maps.newHashMap();
        HttpEntity httpEntity = new HttpEntity(param, httpHeaders);
        try {
            ResponseEntity<String> responseEntity = restLongTemplate.postForEntity(url, httpEntity, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            BootStoneLog.bootStone.error("http error", e);
        }
        return null;
    }

}
