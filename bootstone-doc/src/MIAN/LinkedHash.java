import org.junit.Test;

import java.util.*;

/**
 * Created by lsh on 2020-08-12.
 * <p>
 * LinkedHashMap 有序放入map
 */
public class LinkedHash {

    @Test
    public void test() {
//        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        map.put("1", "1");
//        map.put("3", "3");
//        map.put("2", "2");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("1", "1");
        map1.put("2", "2");
        map1.put("3", "3");
        map1.put("4", "4");
        map1.put("5", "5");

        map1.forEach((x, y) -> System.out.println(x + "-" + y));

        Set<Map.Entry<String, String>> set = map1.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }



    }
}
