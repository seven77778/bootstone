package work.utils;

import org.springframework.stereotype.Component;
import work.utils.vo.UtilDateVo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * list 排序
 */
@Component
public class ListSortUtil {

    /**
     * list中do含有日期的，按照日期升序排列
     */
    public static void sortListBySort(List<UtilDateVo> list) {
        list.sort((o1, o2) -> {
            LocalDate date1 = LocalDate.parse(o1.getDate(), DateTimeFormatter.ofPattern("yyyy-M-d"));
            LocalDate date2 = LocalDate.parse(o2.getDate(), DateTimeFormatter.ofPattern("yyyy-M-d"));
            if (date1.isAfter(date2)) {
                return 1;
            } else {
                return -1;
            }
        });
    }


    /**
     * 按照type 反向排序，在按照创建时间排序
     * 关键词 reversed
     * @param list
     */
    public static void sortByType(List<UtilDateVo> list) {
        list = list.stream()
                .sorted(Comparator.comparing(UtilDateVo::getType).reversed().thenComparing(UtilDateVo::getCreateTime))
                .collect(Collectors.toList());
    }


}
