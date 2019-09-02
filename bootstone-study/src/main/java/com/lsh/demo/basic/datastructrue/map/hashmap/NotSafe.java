package com.lsh.demo.basic.datastructrue.map.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  Created by lsh on 2019-05-14.
 *
 *  * HashMap之所以线程不安全，就是resize出的问题 todo
 *  http://www.ruanyifeng.com/blog/2018/09/hash-collision-and-birthday-attack.html
 *  哈希碰撞 和 生日攻击
 *  https://www.cnblogs.com/fswhq/p/hashmap.html resize
 *
 *  *
 *  *1.initialCapacity 初始44 -> 64  threshold
 *  *2.HashIterator modCount 作用，遍历map时，会将modcount赋给expectedModCount，如果遍历过程中
 *  * map被其他线程修改，throw new ConcurrentModificationException()
 *
 *
 *  * 1、put的时候导致的多线程数据不一致。
 *  * 这个问题比较好想象，比如有两个线程a和b，首先a希望插入一个key-value对到hashmap中，
 *  * 首先计算记录所要落到的桶的索引坐标，然后获取到该桶里面的链表头结点，此时线程a的时间片用完了，而此时线程b
 *  * 被调度得以执行，和线程a一样执行，只不过线程b成功将记录插到了桶里面，
 *  * 假设线程a插入的记录计算出来的桶索引和线程b要插入的记录计算出来的桶索引是一样的，那么当线程b成功插入之后，线程a
 *  * 再次被调度运行时，它依然持有过期的链表头但是它对此一无所知，以至于它认为它应该这样做，
 *  * 如此一来就覆盖了线程b插入的记录，这样线程b插入的记录就凭空消失了，造成了数据不一致的行为。
 *  *
 * ~~~~~~~~~~~~~~~
 *  2.另外一个比较明显的线程不安全的问题是HashMap的get操作可能因为resize而引起死循环（cpu100%）
 *  1.7 会出现，1.8解决了死循环的问题，但是1.8依然有并发的问题，推荐使用 ConcurrentHashMap
 *
 *  【通过两个指针loHead/loTail指向重hash后位置不变的链表头和尾
 * 以及hiHead/hiTail指向重hash后位置+oldCap的链表头和尾】
 *
 *  具体分析：
 *
 * while(null != e) {
 *     Entry<K,V> next = e.next; //记录odl hash表中e.next
 *     int i = indexFor(e.hash, newCapacity); //rehash计算出数组的位置(hash表中桶的位置)
 *     e.next = newTable[i]; //e要插入链表的头部， 所以要先将e.next指向new hash表中的第一个元素
 *     newTable[i] = e; //将e放入到new hash表的头部
 *     e = next; //转移e到下一个节点， 继续循环下去
 * }
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *
 * HashMap通常会用一个指针数组（假设为table[]）来做分散所有的key，当一个key被加入时，会通过Hash算法通过key算出这个数组的下标i，
 * 然后就把这个<key, value>插到table[i]中，如果有两个不同的key被算在了同一个i，那么就叫冲突，又叫碰撞，
 * 这样会在table[i]上形成一个链表。
 *
 * 我们知道，如果table[]的尺寸很小，比如只有2个，如果要放进10个keys的话，那么碰撞非常频繁，于是一个O(1)的查找算法，
 * 就变成了链表遍历，性能变成了O(n)，这是Hash表的缺陷（可参看《Hash Collision DoS 问题》）。
 *
 * 一般来说，Hash表这个容器当有数据要插入时，都会检查容量有没有超过设定的thredhold，
 * 如果超过，需要增大Hash表的尺寸，但是这样一来，整个Hash表里的无素都需要被重算一遍。这叫rehash，这个成本相当的大
 *
 */
public class NotSafe {


    /**
     * hashcode相同，值不一定相同
     */
    @Test
    public void test(){

        Map<String,String> map = new HashMap<>(16);
        map.put("123","123");

        //96354 和 abc hashcode一样
        Integer a = new Integer(96354);
        String b = "abc";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(a.equals(b));
    }

}
