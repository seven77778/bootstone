package algorithm.leetcode.other;

import lombok.Data;

import java.util.Map;

/**
 * @author lsh
 * @date 2023/6/11 11:53
 */
public class MyLRU {

    private LruNode first;
    private LruNode last;
    private Map<String, LruNode> caches;
    private int capcity;

    @Data
    class LruNode {
        LruNode pre;
        LruNode next;
        String key;
        String value;
    }
}
