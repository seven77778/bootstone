package work.utils;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lsh on 2018/11/12 11:21.
 *
 * @author lsh
 * @date 2018/11/12
 * <p>
 * 实现 CloseableHttpClient
 * 1.EntityUtils.toString 中finally 中关闭了 InputStream
 */
public class CloseHttpUtil {

    private static CloseableHttpClient closeableHttpClient;
    private static HttpClientBuilder httpClientBuilder;
    static final int CONNTECT_TIMEOUT = 1;
    static final int SOCKET_TIMEOUT = 1;

    static {
        httpClientBuilder = HttpClients.custom();
        closeableHttpClient = httpClientBuilder.build();
    }


    public void httpPost() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.baidu.com");
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.addHeader("Authorization", "123");
        String request = "";
        CloseableHttpResponse httpResponse = null;
        try {
            StringEntity stringEntity = new StringEntity(request, "utf-8");
            httpPost.setEntity(stringEntity);
            httpResponse = httpClient.execute(httpPost);
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            int codeMake = httpResponse.getStatusLine().getStatusCode();
            String content = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("salto http post error:" + e);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                BootStoneLog.bootStone.error("error " + e);
            }
        }
    }

    public void httpGet() {
        HttpGet httpGetRequest = new HttpGet("http://www.baidu.com");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)//设置为1，connectime out
                .setSocketTimeout(1000)//设置为1，readtime out
                .setConnectionRequestTimeout(1)
                .build();

        httpGetRequest.setConfig(requestConfig);

        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGetRequest);
            //
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() {
        System.out.println("*********************");
        httpGet();
    }


}
