package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.lsh.demo.bootstone.service.util.RedisUtil;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * redis set 数量5，过期时间1秒，就是qps 5
 */

@Component
public class RedisQpsLimit implements RateLimit {

    @Resource
    private RedisUtil redisUtil;

//    @Resource
//     CommandExecutor commandExecutor;

    @Override
    public String getType() {
        return "redis-qps";
    }

    @Override
    public void limit(RateConfig config) {

        if(trySetRedis(config.getName(),config.getLimit()+"")){

            System.out.println("剩余 "+redisUtil.get(config.getName()));
        }else {
            throw new RateLimitException("redis-qps");
        }

    }

    @Override
    public void releaseLimit(RateConfig config) {

    }

    @Override
    public void init() {

    }


    /**
     * 使用lua脚本进行redis的操作
     * @return
     */
    private boolean trySetRedis(String name,String limit){
        String script="local times = redis.call('incr',KEYS[1])\n" +
                "\n" +
                "if times == 1 then\n" +
                "    redis.call('expire',KEYS[1], ARGV[1])\n" +
                "end\n" +
                "\n" +
                "if times > tonumber(ARGV[2]) then\n" +
                "    return 0\n" +
                "end\n" +
                "return 1";
        List<String> list = Lists.newArrayList();
        list.add(name);
        String res = redisUtil.invokeScript(script,list,"1",limit);
        return "1".equals(res);
    }


}
