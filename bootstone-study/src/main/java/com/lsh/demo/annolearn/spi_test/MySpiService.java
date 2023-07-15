package com.lsh.demo.annolearn.spi_test;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;


/**
 * @author lsh
 * @date 2023/4/19 11:15
 */

//@SPI("impl1")
@SPI
public interface MySpiService {

    String hello(URL url, String s);

    @Adaptive({"key4"})
    void printA(URL url);

    @Adaptive
    void printB(URL url);

    @Adaptive({"key3","key2","key1"})
    void printC(URL url);

}
