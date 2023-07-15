package work.writeExam202305.lianxiQueue;

import org.junit.Test;

import java.util.Stack;

/**
 * @author lsh
 * @date 2023/5/15 21:51
 *
 */
public class MyQueue {
    private Stack<Integer> stack1;//进来的元素都放在里面
    private Stack<Integer> stack2;//元素都从这里出去

    public MyQueue() {
        // do initialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    public int pop() {
        // write your code here
        while (!stack1.isEmpty()) {//如果没有元素,就把负责放入元素的栈中元素全部放进来
            stack2.push(stack1.pop());
        }
        int res = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return res;
    }

    public int top() {
        // write your code here
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int res = stack2.peek();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return res;
    }

    /**
     * 使用两个栈来实现队列的一些操作。
     * 队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
     * pop和top方法都应该返回第一个元素的值。
     * 样例：比如push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
     * 要求：仅使用两个栈来实现它，不使用任何其他数据结构，push，pop 和 top的复杂度都应该是均摊O(1)的
     */
    @Test
    public void test() {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        System.out.println(myQueue.pop());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.top());
        System.out.println(myQueue.top());

    }

}
