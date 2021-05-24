package com.lsh.demo.bootstone.dubbo.study;

import org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;

/**
 * dubbo降级策略
 * <p>
 * 1.mock是加在消费者的配置中的
 * <dubbo:reference id="dubboService" interface="com.lsh.scm.dubbo.MyDubboService"
 * check="false" retries="0" timeout="15000" mock="force:return 1" />
 * <p>
 * ① force:return 1 不管服务是否正常，所有请求返回 1，不走任何业务逻辑 -- 全是走了mock
 * ② return 1 , 配合超时时间使用的，超时之前的请求正常，超时之后的，返回 1
 * ③ mock="throw java.lang.Exception"  超时抛出异常
 * ④ mock="com.lsh.scm.dubbo.DubboMock" -- 全是走了mock的类，逻辑可以写在mock接口
 * -- 再次测试，正常，超时才走mock
 * <p>
 * ⑤ timeout="3000" mock="return 服务超时走mock接口" -- 全走了mock，why
 * -- 跟 第二次 的区别是 scm中新增了一个dubbomock接口，删除再打包试试
 * -- 删除了dubbomock就好了！！why -- fixme 很诡异，现在正常了
 * -- 加上dubbomock，也没问题了。。
 *
 * @see MockClusterInvoker
 * @see https://mp.weixin.qq.com/s?__biz=MzIyNzYzMTQ2Ng==&mid=2247485588&idx=1&sn=c6aeceef91c4ca5f21f867dca6efa2c2&chksm=e85f0091df2889872d4738441b5772b9670c821faf42754dc83f97a6f331d6999d69430ea3d9&cur_album_id=1568872034091237377&scene=190#rd
 */
public class DownLevelStrategy {




}
