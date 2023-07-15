package algorithm.leetcode.lru;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lsh
 * @date 2023/7/10 20:37
 */
public class TestDeleteMap {


  HashMap<String,String > map = new HashMap<>();

  @Test
  public void test(){
    map.put("1","a");
    map.put("2","b");
    map.put("3","c");
    map.put("4","d");
    map.put("5","e");

//    boolean ss = map.entrySet().removeIf(x -> x.getValue().equals("c"));


    Map<String, String> ss = map.entrySet().stream().filter(x -> !x.getValue().equals("c"))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    System.out.println(ss);
  }
}
