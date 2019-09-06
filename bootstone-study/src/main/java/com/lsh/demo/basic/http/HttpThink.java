package com.lsh.demo.basic.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by lsh on 2019-09-05.
 */
public class HttpThink {

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * 测试StringEntity 是否可以复用
     *
     * 1.复用一个，for 10次，执行两次，然后无限等待
     *
     * 2.不复用，每次new一个新的，也是执行两次，然后无限等待
     * 不用百度，换
     * 解 ：是没有关闭流，也就是没有调用  EntityUtils.consume 或者 EntityUtils.toString
     * 关闭的是 InputStream
     *
     * 继续测试，换复用，结果都是302，好像没影响
     */
    @Test
    public void testStringEntity(){
        HttpPost post = new HttpPost("http://www.sina.com.cn/");
        try {
            StringEntity stringEntity = new StringEntity("");
            for(int i=0;i<10;i++) {
                post.setEntity(stringEntity);
                CloseableHttpResponse response = httpClient.execute(post);
                System.out.println("应答状态码 " + response.getStatusLine().getStatusCode());
//                EntityUtils.consume(response.getEntity());
                EntityUtils.toString(response.getEntity());
            }
//            System.out.println("应答结果 "+EntityUtils.toString(response.getEntity()));
        }catch ( Exception e){
            System.out.println("error");
        }
    }


    /**
     * for优化
     * see https://cloud.tencent.com/developer/article/1494601
     * 1.重复性的计算不要放在循环内
     * 2.提取与循环无关的运算
     */
    @Test
    public void test(){
        ArrayList<Integer> list1 = new ArrayList();

        for(int i=0;i<1000000;i++){
            list1.add(i);
        }

        long time1 = System.currentTimeMillis();
        for(int i=0;i< list1.size();i++){
            System.out.print(list1.get(i));
            int a = i;
        }
        System.out.println("~~~~");
        System.out.println("优化前耗时 " + (System.currentTimeMillis() - time1));

        Long time2 = System.currentTimeMillis();
        for(int i=0, len = list1.size();i< len;i++){
            System.out.print(list1.get(i));
            int a = i;
        }
        System.out.println("~~~");
        System.out.println("优化后耗时 " + (System.currentTimeMillis() - time2));
    }

}
