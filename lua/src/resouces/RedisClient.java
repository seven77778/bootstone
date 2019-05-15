//package resouces;
//
//import org.springframework.beans.factorymode.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// *
// * @date 2018/01/16
// */
//@Component
//public class RedisClient {
//    private Jedis jedis;//非切片额客户端连接
//    private JedisPool jedisPool;//非切片连接池
//    /**
//     * 初始化非切片池
//     */
//    private void initialPool()
//    {
//        // 池基本配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(5);
//        config.setTestOnBorrow(false);
//
//        jedisPool = new JedisPool(config,"127.0.0.1",6379);
//    }
//
//
//    public Jedis getConn() {
//        try {
//            initialPool();
//             jedis = jedisPool.getResource();
//            return jedis;
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//
//}
