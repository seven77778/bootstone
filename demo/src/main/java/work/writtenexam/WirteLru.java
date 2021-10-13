package work.writtenexam;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WirteLru {
    /**
     * 手写lru
     */

    private LruNode first;
    private LruNode last;
    private Map<String, LruNode> caches;
    private int capcity;

    public WirteLru(int capcity) {
        this.capcity = capcity;
        caches = new ConcurrentHashMap<>(capcity);
    }


    public void put(String k, String v) {
        LruNode node = caches.get(k);
        if (null == node) {
            if (caches.size() >= capcity) {
                caches.remove(last.key);
                removeLast();
            }
            node = new LruNode();
            node.key = k;
        }

        node.value = v;
        caches.put(k,node);
        moveToFirst(node);
    }

    public String get(String k){
        LruNode node = caches.get(k);
        if(node==null){
            return null;
        }
        moveToFirst(node);
        return node.value;
    }

    private void moveToFirst(LruNode node){
        if(first == node){
            return;
        }
        if(node.next != null){
            node.next.pre = node.pre;
        }
        if(node.pre != null){
            node.pre.next = node.next;
        }
        if(node == last){
            last= last.pre;
        }
        if(first == null || last == null){
            first = last = node;
            return;
        }

        node.next=first;
        first.pre = node;
        first = node;
        first.pre=null;
    }


    private void removeLast(){
        if(last != null){
            last = last.pre;
            if(last == null){
                first = null;
            }else{
                last.next = null;
            }
        }
    }

    @Data
    class LruNode {
        LruNode pre;
        LruNode next;
        String key;
        String value;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        LruNode node = first;
        while(node != null){
            sb.append(String.format("%s:%s## ", node.key,node.value));
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WirteLru lru = new WirteLru(5);
        lru.put("1","1---v");
        lru.put("2","2---v");
        lru.put("3","3---v");
        lru.put("4","4---v");
        lru.put("5","5---v");
        lru.put("6","6---v");
        lru.put("7","7---v");
        lru.put("1","2222");
        System.out.println(lru.toString());
    }
}
