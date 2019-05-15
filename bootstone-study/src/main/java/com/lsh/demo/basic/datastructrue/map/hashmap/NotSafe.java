package com.lsh.demo.basic.datastructrue.map.hashmap;

/**
 *  Created by lsh on 2019-05-14.
 *
 *  * HashMap之所以线程不安全，就是resize出的问题
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
 *  * 2.另外一个比较明显的线程不安全的问题是HashMap的get操作可能因为resize而引起死循环（cpu100%）
 *
 *
 */
public class NotSafe {




}
