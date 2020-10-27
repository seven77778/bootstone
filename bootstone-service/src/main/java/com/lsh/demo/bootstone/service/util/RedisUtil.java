package com.lsh.demo.bootstone.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * https://cloud.tencent.com/developer/article/1497575
 * Created by lsh on 2019-11-21.
 * <p>
 * 1. 连接池自动管理，提供了一个高度封装的“RedisTemplate”类
 * 此处指的连接池不一定是JedisPool，因为SpringBoot2.0之后，底层默认不再采用Jedis作为实现了。
 * 而是采用效率更高，线程更安全的lettuce客户端
 * <p>
 * 2. 针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口
 * <p>
 * ValueOperations：简单K-V操作
 * SetOperations：set类型数据操作
 * ZSetOperations：zset类型数据操作
 * HashOperations：针对map类型的数据操作
 * ListOperations：针对list类型的数据操作
 * 3. 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即BoundKeyOperations：
 * <p>
 * BoundValueOperations
 * BoundSetOperations
 * BoundListOperations
 * BoundSetOperations
 * BoundHashOperations
 * <p>
 * 4. 将事务操作封装，有容器控制。
 * <p>
 * 5. 针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)
 * <p>
 * JdkSerializationRedisSerializer：POJO对象的存取场景，使用JDK本身序列化机制，将pojo类通过ObjectInputStream/ObjectOutputStream进行序列化操作，最终redis-server中将存储字节序列。是目前最常用的序列化策略。
 * <p>
 * StringRedisSerializer：Key或者value为字符串的场景，根据指定的charset对数据的字节序列编码成string，是“new String(bytes, charset)”和“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略。
 * <p>
 * JacksonJsonRedisSerializer：jackson-json工具提供了javabean与json之间的转换能力，可以将pojo实例序列化成json格式存储在redis中，也可以将json格式的数据转换成pojo实例。因为jackson工具在序列化和反序列化时，需要明确指定Class类型，因此此策略封装起来稍微复杂。【需要jackson-mapper-asl工具支持】
 * <p>
 * OxmSerializer：提供了将javabean与xml之间的转换能力，目前可用的三方支持包括jaxb，
 */
@Component
public class RedisUtil {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public Boolean set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    public Long decrease(String key) {
        return redisTemplate.opsForValue().increment(key, -1);
    }

    public Boolean setKeyByTime(String key, String value, long times, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, times, timeUnit);
        return true;
    }

    public Boolean expire(String key, long times, TimeUnit timeUnit) {
        Boolean res = redisTemplate.expire(key, times, timeUnit);
        return null != res && res;
    }

    public Long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    public String invokeScript(String script, List<String> params, Object... args) {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
//        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("checkandset.lua")));
        defaultRedisScript.setScriptText(script);
        defaultRedisScript.setResultType(Long.TYPE);
        Long res = redisTemplate.execute(defaultRedisScript, params, args);
        System.out.println(res);
        return res + "";
    }


}
