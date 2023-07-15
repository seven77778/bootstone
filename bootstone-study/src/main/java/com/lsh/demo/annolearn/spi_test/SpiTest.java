package com.lsh.demo.annolearn.spi_test;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.junit.Test;

import java.util.List;

/**
 * @author lsh
 * @date 2023/4/19 11:22
 * Adaptive注解的自适应匹配遵循一定顺序。
 *
 * 若Adaptive的value字段不为空，则实现类查找顺序为Adaptive注解的value数组 => spi的value
 * 若Adaptive的value字段为空，则实现类查找顺序为my.spi.service => spi的value
 * 若查找不到所需的实现类则会抛出异常
 *
 * my.spi.service = MySpiService  驼峰拆解
 *
 * printA方法先尝试从url查找key4的value，查找不到，因此使用SPI注解中指定的impl1作为实现类
 * printB方法的Adaptive注解未指定value,因此先查找其驼峰变形名my.spi.service，url中存在并对应了impl2的实现类
 * printC方法先尝试查找key3的value，发现url中存在，因此将impl3作为实现此方法的实现类
 * 注意：由于扩展点获取的是自适应实现，因此未使用@Adaptive标识的方法不应调用，调用则会抛出异常（详情可见下方动态类源码）


 *
 */
public class SpiTest {

    public static void main(String[] args) {

        URL url=URL.valueOf("127.0.0.0:9091/test?key1=impl1&key3=impl3&my.spi.service=impl2");
        MySpiService service= ExtensionLoader.getExtensionLoader(MySpiService.class).getAdaptiveExtension();
        service.printA(url);
        service.printB(url);
        service.printC(url);

    }


    /***
     * 1.D:\WORKCODE\bootstone\bootstone-study\src\main\resource\META-INF\dubbo\com.alibaba.dubbo.rpc.Filter
     * 配置文件中加上才生效，且不用重新install
     *
     * 2.自动激活，根据条件选择实现类
     *
     */
    @Test
    public void test(){
        ExtensionLoader<Filter> extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);

        URL url = URL.valueOf("test://localhost/test");
        //第一个参数为url,第二个参数稍后讲，第三个参数为group
        List<Filter> list = extensionLoader.getActivateExtension(url, "", "lsh");//group
        for (Filter filter : list) {
            filter.invoke(null, null);
        }

    }
}
