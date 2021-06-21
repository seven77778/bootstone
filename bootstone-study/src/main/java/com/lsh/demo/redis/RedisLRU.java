package com.lsh.demo.redis;

import lombok.Data;

/**
 * LRU算法 todo 3 hours not get
 * LRU(least recently used)是一种缓存置换算法 -- 最近最少使用。
 * 即在缓存有限的情况下，如果有新的数据需要加载进缓存，则需要将最不可能被继续访问的缓存剔除掉。
 *
 * 说说你自己的思路
 * -- 用什么东西存呢，map，list，链表
 *
 * 假如用map来实现，
 */
public class RedisLRU {

    @Data
    static class LRUByMapNode{

        private String key;
        private String value;
        private long timeStamp;
        private LRUByMapNode next;
        private LRUByMapNode last;








    }
}
