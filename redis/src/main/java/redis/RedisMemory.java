package redis;


/**
 * windows 下，进入redis-cli.exe，info | info memory
 *
 * 在config配置文件中
 * maxmemory 2mb
 * maxmemory-policy allkeys-lru
 *
 * 【推荐设置maxmemory】
 * 1、使用swap危害：磁盘性能下降，持久化时间过长，可能出现暂停服务。
 * -- 当使用内存超过系统最大内存，redis开始把内存中不使用的数据写到硬盘上，内存是微妙，硬盘毫秒级操作
 *
 * 2、oom危害：暂停服务，意外Crash(windows版本上十分明显)
 *
 * 【设置内存值的思考】
 * 若是启用了Redis快照功能，应该设置“maxmemory”值为系统可使用内存的45%，
 * 因为快照时需要一倍的内存来复制整个数据集，也就是说如果当前已使用45%，
 * 在快照期间会变成95%(45%+45%+5%)，其中5%是预留给其他的开销。
 * 如果没开启快照功能，maxmemory最高能设置为系统可用内存的95%。
 *
 * 【过期策略的设置】
 * 若是Redis数据集中的key都设置了过期时间，那么“volatile-ttl”策略是比较好的选择。
 * 但如果key在达到最大内存限制时没能够迅速过期，或者根本没有设置过期时间。
 * 那么设置为“allkeys-lru”值比较合适，
 * 它允许Redis从整个数据集中挑选最近最少使用的key进行删除(LRU淘汰算法)。
 *
 *
 * 【hash操作】
 * 10	HMSET key field1 value1 [field2 value2 ]
 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
 * 11	HSET key field value
 * 将哈希表 key 中的字段 field 的值设为 value 。
 * 单独获取 hget key field1
 * 获取key 下所有键值对  hgetall key。获得是只是所有的values
 *
 */
public class RedisMemory {
}
