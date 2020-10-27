package baisc.redission;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientUtil {

    /**
     * 集群单机
     * Caused by: org.redisson.client.RedisException:
     * ERR This instance has cluster support disabled. channel:
     * [id: 0x0fd46049, L:/127.0.0.1:62555 - R:/127.0.0.1:6379] command:
     * CommandData [promise=org.redisson.misc.RedissonPromise@74bd3426[Not completed],
     * command=(CLUSTER NODES), params=[], codec=null]
     * @return
     */
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
