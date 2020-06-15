package basic.fifteen;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * Created by lsh on 2019-08-20.
 *
 * 这一课有点抽象，等我回家哈
 *
 * 再来个网络编程吧，也是比较固定的用法，会改就行
 * 需要改的地方
 * 1. 请求的网址
 * 2. 传递的参数（有的请求需要参数，有的请求不需要参数）
 *
 */
public class FifTeen2 {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * 发起http请求都需要一个httpClient，这是固定写法
     * 有一点需要理解的点 是http请求分为多种，最常用的分别是 GET，POST
     * 不常用 的有 PUT，DELETE
     * 今天就看GET 和POST
     * 1.GET 理解为 类似数据库的查询操作，单纯的查询，没有副作用，不会修改什么内容
     * 请求格式 就是 http://www.baidu.com 一般不带参数，就算带了参数也是直接拼接在URL后面，但是参数直接暴露也是不安全的（早期的黑客都是利用了这一点
     *  至于为什么不安全，一会来个演示哈）
     *
     * 2、POST 理解为 提交数据，一般是带的数据比较多，类似数据库的修改操作，它比GET更安全，因为它的参数不会暴露在url中
     */
    @Test
    public void testBaidu(){
        //创建一个客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个httpGet 请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=62095104_19_oem_dg&wd=范县天气");
        try {
            //使用这个客户端去调用 httpGet 请求，httpResponse就是服务端返回的结果
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            //输出一下 返回结果的状态码，200代表服务正常，不是200就都是有问题的
            System.out.println("http状态码 是 " + httpResponse.getStatusLine().getStatusCode());
            // 打印一下 返回结果的内容，EntityUtils.toString(httpResponse.getEntity()) 也是固定的写法
            String content = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * http 的get请求，无参数
     */
    @Test
    public void test(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8083/77/test");
        String request = "";
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println("http状态码 是 " + httpResponse.getStatusLine().getStatusCode());
            String content = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * http 的get请求，有参数
     * 模拟一个登录系统，用户名和密码拼接在url后面，固定格式是 url + ？ + 具体参数=多少这样
     * 比如 http://localhost:8083/77/test3 ? username=77&password=123456，这样的话，用户名和密码就都暴露给所有人了
     */
    @Test
    public void test2(){
        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8083/77/test3?username=77&password=123456");
        try {
            String content = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
            System.out.println("*************http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * http 的post请求，无参数的，这种无参数的 就类似get请求了
     */
    @Test
    public void testPost1(){
        HttpPost httpPost = new HttpPost("http://localhost:8083/77/post1");
        try {
            String content = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
            System.out.println("http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * http 的post请求，有参数的
     */
    @Test
    public void testPost2(){
        HttpPost httpPost = new HttpPost("http://localhost:8083/77/post2");
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            String request = "{\n" +
                    "\"name\":\"xiaoliu\",\n" +
                    "\"age\":\"18\"\n" +
                    "}";
            StringEntity stringEntityRead = new StringEntity(request, "utf-8");
            httpPost.setEntity(stringEntityRead);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String content = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("******http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * 查询天气 http://www.mxnzp.com/api/weather/current/范县
     * 这是别人 免费开放的 查询天气的接口
     * 修改 范县为 濮阳市 杭州市 均可，have a try？
     * */
    @Test
    public void testFunny(){
        HttpGet http = new HttpGet("http://www.mxnzp.com/api/weather/current/范县");
        try {
            String content = EntityUtils.toString(httpClient.execute(http).getEntity());
            System.out.println("*********** http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

    /**
     * 随机笑话 ： https://www.mxnzp.com/api/jokes/list/random
     * 土味情话 ： https://chp.shadiao.app/api.php
     * */
    @Test
    public void testFunny2(){
        HttpGet http = new HttpGet("https://www.mxnzp.com/api/jokes/list/random");
        try {
            String content = EntityUtils.toString(httpClient.execute(http).getEntity());
            System.out.println("*********** http应答 是 " + content);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("error:" + e);
        }
    }

}
