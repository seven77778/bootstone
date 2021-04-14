package com.lsh.demo.bootstone.dao.mysql.transactional;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *  @Transactional 为什么是spring的注解，不是mybatis的吗
 *  1.mybatis有自己的事务注解吗？
 *  --
 *
 *
 *  2.为什么需要手动回滚，什么时候需要手动回滚？
 *  -- 有一些需求，要catch住异常，就在异常中手动回滚事务
 *  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
 *  或者是因为没有全局异常的处理，直接抛出的异常，导致前端展示有问题，所以catch住，设置下错误码
 *
 * @see TransactionAspectSupport#invokeWithinTransaction(java.lang.reflect.Method, java.lang.Class, org.springframework.transaction.interceptor.TransactionAspectSupport.InvocationCallback)
 */
@Transactional
public class TransactionalLearn {

    /**
     * 测试mybatis的事务
     *
     *
     */
    @Test
    public void testMybatisTrans(){

    }
}
