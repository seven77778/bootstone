public class Answer1231KuaiShow {

    /**
     * 谈一下项目
     * 谈一下对IOC和AOP的理解
     * Memchached 和 Redis 的区别
     * Redis的持久化实现
     * Redis的淘汰策略
     * 定期删除和惰性删除的区别、优缺点
     * IOC的原理是什么
     * Hashmap的底层实现和原理
     * 手写一个，实现map的put方法
     *
     * 一. IoC(Inverse of Control:控制反转)是一种设计思想 或者说是某种模式。
     * 这个设计思想就是 将原本在程序中手动创建对象的控制权，交由 Spring 框架来管理。
     * IoC 在其他语言中也有应用，并非 Spring 特有。IoC 容器是 Spring 用来实现 IoC 的载体，
     * IoC 容器实际上就是个 Map(key，value),Map 中存放的是各种对象。
     *
     * IoC 最常见以及最合理的实现方式叫做依赖注入(Dependency Injection，简称 DI)。
     *
     * aop 面向切面
     * 切面收集日志，切面处理统一错误码
     *
     * ②
     * 1、 Redis和Memcache都是将数据存放在内存中，都是内存数据库。不过 memcache 还可用于缓存其他东西，例如图片、视频等等。
     * 2、Redis不仅仅支持简单的k/v类型的数据，同时还提供list，set，hash等数据结构的存储。
     * 3、虚拟内存–Redis当物理内存用完时，可以将一些很久没用到的value 交换到磁盘
     * 4、过期策略–memcache在set时就指定，例如set key1 0 0 8,即永不过期。Redis可以通过例如expire 设定，例如expire name 10
     * 5、分布式–设定memcache集群，利用magent做一主多从;redis可以做一主多从。都可以一主一从
     * 6、存储数据安全–memcache挂掉后，数据没了；redis可以定期保存到磁盘（持久化）
     * 7、灾难恢复–memcache挂掉后，数据不可恢复; redis数据丢失后可以通过aof恢复
     * 8、Redis支持数据的备份，即master-slave模式的数据备份
     *
     *
     *
     *
     */
}
