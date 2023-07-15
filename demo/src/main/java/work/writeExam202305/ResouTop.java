package work.writeExam202305;

import lombok.Data;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lsh
 * @date 2023/5/10 11:59
 * <p>
 * PriorityQueue  默认最小堆
 * <p>
 * 小顶堆
 */
public class ResouTop {
    @Test
    public void itSelf() {
        PriorityQueue<Integer> array = new PriorityQueue<>();
        array.offer(111);
        array.poll();
        array.peek();
        array.offer(222);
        array.offer(333);
        array.offer(99);
        Integer ss = array.peek();
        System.out.println(array);
        //[99, 111, 333, 222]
        //PriorityQueue 默认是最小堆，最小值在根节点
        //可以改写compare方法，使之成为最大堆
        PriorityQueue<Integer> big = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        big.offer(111);
        big.offer(222);
        big.offer(333);
        big.offer(99);
        System.out.println(big);
        //[333, 111, 222, 99]
    }

    @Test
    public void testResou100() {
        PriorityQueue<ResouTop.Resou> pq = new PriorityQueue<ResouTop.Resou>(
        );
        pq.offer(new ResouTop.Resou(111, "101"));
        pq.offer(new ResouTop.Resou(12, "102"));
        pq.offer(new ResouTop.Resou(666, "103"));
        pq.offer(new ResouTop.Resou(7896, "104"));
        System.out.println(pq);
    }

    @Data
    class Resou {
        private int count;
        private String id;
        private String text;

        public Resou(int count, String id) {
            this.count = count;
            this.id = id;
        }
    }

}
