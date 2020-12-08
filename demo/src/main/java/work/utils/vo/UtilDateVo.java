package work.utils.vo;


import lombok.Data;

import java.time.LocalDate;

/**
 * 给util测试的各种vo
 */
@Data
public class UtilDateVo {

    private String name;

    private String date;

    private String type;

    private LocalDate createTime;


    public UtilDateVo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public UtilDateVo() {
    }

    public UtilDateVo(String name, String date, String type, LocalDate createTime) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.createTime = createTime;
    }
}
