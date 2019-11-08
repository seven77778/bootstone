package com.lsh.demo.annolearn;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by lsh on 2019-10-24.
 *
 * 注解 - enable* 类
 *
 */
@EnableConfigurationProperties  //-- @Import(EnableConfigurationPropertiesImportSelector.class)
@EnableAsync // @Import(AsyncConfigurationSelector.class)
public class AnnotionLearn1 {

    /*
    每个enable都有一个 import 的类
    所以@Enable*自动开启的实现原理其实就是导入了一些自动配置的Bean
     */


}
