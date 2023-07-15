package algorithm.leetcode.lru;

import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @author lsh
 * @date 2023/7/7 10:16
 */
public class LRUByLinkedHashMap extends LinkedHashMap {


  @Test
  public void test(){
    LinkedHashMap map = new LinkedHashMap(8);

    map.put(1,1);
    map.get(1);

  }



}
