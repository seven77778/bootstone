package com.bootstone.spring.resourceandtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResourceAndTypeTest {

    @Autowired
    private MyTypeBean myTypeBean1;

    @Resource
    private MyNameBean myNameBean1;

    /**
     * 用@resource 注入MyTypeBean
     * NoUniqueBeanDefinitionException: No qualifying bean of type
     *
     * @resource 默认名字
     *
     * @Autowired,
     * Field myTypeBean in com.bootstone.spring.resourceandtype.ResourceAndTypeTest
     *  required a single bean, but 2 were found
     *
     *  @Autowired 是按照类型匹配，MyTypeBean有两个实现类，然后默认按名字，名字是实现类
     *  首字母小写，myTypeBean1 就可以匹配到。
     *
     */

}
