package baisc.redission;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientUtil {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // set集群版报错
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
        ;
        BootStoneLog.bootStone.info("redissionClient create success");
        return Redisson.create(config);
    }

}
