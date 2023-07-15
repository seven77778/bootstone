package arthas;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author lsh
 * @date 2023/5/27 17:08
 */
@Component
@Data
public class ArthasMapTest {

    @Value("${value.test}")
    private String name;

    private static HashMap<String,String> map= Maps.newHashMap();

    @PostConstruct
    private void init(){
        map.put("111","AAA");
        map.put("222","BBB");
        map.put("666","LSH");
    }


}
