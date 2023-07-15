package work.writeExam202305.lianxiQueue;

import org.junit.Test;

import java.util.Stack;

/**
 * @author lsh
 * @date 2023/5/15 22:37
 */
public class MyWirteQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;


    public MyWirteQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int element) {
        s1.push(element);
    }

    public Integer pop() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public Integer top() {
        if(s2.empty()){
            while (!s1.empty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    @Test
    public void test(){
        MyWirteQueue queue = new MyWirteQueue();
        queue.push(1);
        System.out.println(queue.pop());
        queue.push(2);
        queue.push(3);
        System.out.println(queue.top());
        System.out.println(queue.top());
        System.out.println(queue.pop());
        System.out.println(queue.top());
    }
}
