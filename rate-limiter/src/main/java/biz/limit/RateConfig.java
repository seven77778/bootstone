package biz.limit;

import lombok.Data;

/**
 * 指定限流的配置
 */
@Data
public class RateConfig {

    private Integer id;

    /**
     *
     */
    private String type;

    private String name;

    private Integer limit;

    private Boolean wait;

    public RateConfig(String type, String name, Integer limit) {
        this.type = type;
        this.name = name;
        this.limit = limit;
    }

    public RateConfig(String type, String name, Integer limit, Boolean wait) {
        this.type = type;
        this.name = name;
        this.limit = limit;
        this.wait = wait;
    }

    public RateConfig(Integer id, String type, String name, Integer limit, Boolean wait) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.limit = limit;
        this.wait = wait;
    }

    public RateConfig() {

    }
}
