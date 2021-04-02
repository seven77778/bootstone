package work.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis集合
 */
@Component
public class RedisFinalUtil {

    // Autowired 可以，@Resource(name = "stringRedisTemplate")也可以
    @Autowired
//    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;


    public String getDB(String key,int dbIndex){
        setDbIndex(dbIndex);
        return redisTemplate.opsForValue().get(key)+"";
    }

    public String getStringKey(String key){
        return redisTemplate.opsForValue().get(key)+"";
    }

    private void setDbIndex(Integer dbIndex) {
        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbIndex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        jedisConnectionFactory.resetConnection();


    }

}
