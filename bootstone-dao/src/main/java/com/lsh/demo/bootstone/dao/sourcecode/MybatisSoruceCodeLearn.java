package com.lsh.demo.bootstone.dao.sourcecode;

import com.lsh.demo.bootstone.dao.mysql.mapper.DataService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * mybatis源码
 *
 * @see SqlSessionFactoryBean
 * SqlSessionFactoryBean 实现了
 * 1.FactoryBean 对象工厂--自定义Bean的创建过程，重写了isSingleton，返回true
 * 2.InitializingBean -- 主要是afterPropertiesSet方法
 * 在包含BeanFactory设置了所有bean属性并满足BeanFactoryAware ， ApplicationContextAware等之后调用。
 * 3.ApplicationListener -- 著名的 onApplicationEvent 方法
 *
 * afterPropertiesSet 在 onApplicationEvent之前执行
 *
 */
public class MybatisSoruceCodeLearn  implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {

    private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryBean.class);

    private Resource configLocation;


    /**
     * 手动调试mybatis
     * @param args
     * @throws IOException
     * @see SqlSessionFactoryBuilder#build(Reader)
     * 通过springboot启动的，不走SqlSessionFactoryBuilder#build
     * @see SqlSessionFactoryBean#buildSqlSessionFactory()
     * springboot的入口
     */
    public static void main(String[] args) throws IOException {
        String resource = "mybatisConfig.xml";
        InputStream inputStream = MybatisSoruceCodeLearn.class.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //动态代理
            DataService mapper = session.getMapper(DataService.class);
            mapper.getById(1);

            Object blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
        } finally {
            session.close();
        }

    }
    @Override
    public SqlSessionFactory getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
