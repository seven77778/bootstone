/**
 * 一、Redis事务和Mybatis事务有什么区别
 * MULTI 、 EXEC 、 DISCARD 和 WATCH 是 Redis 事务的基础。
 *
 * MULTI 开始事务
 * exec 执行事务
 * discard 取消事务
 * watch 监测某个值，如果在事务执行之前变化，事务被打断
 *
 * redis 3.0测试 比如输入一个sett key value，exec执行失败
 * set k v v ,exec总体执行成功，但是这一条失败
 *
 * redis不支持回滚的原因:
 * 不支持事务回滚是因为这种复杂的功能和Redis追求简单高效的设计主旨不相符，
 * 并且Redis事务的执行时错误通常都是编程错误产生的，
 * 这种错误通常只会出现在开发环境中， 而很少会在实际的生产环境中出现
 *
 * 1.redis事务并不是原子性的，redis单条命令是原子性的，但是事务中多条命令，就不原子了
 * 2.如果某条命令执行失败，还会继续
 *
 *
 */

/*
myredis:0>multi
"OK"
myredis:0>get k1
"QUEUED"
myredis:0>set ke k k
"QUEUED"
myredis:0>exec
1) "OK"
2) "k"
3) "OK"
4) "ERR syntax error"
5) "OK"
myredis:0>
 */
public class Answer1231Souhu {
}
