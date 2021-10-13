package baisc.redission;

import org.junit.Test;

import java.util.Stack;

/**
 * 其实还是用了Stack 的一些基本操作
 * 一个用作输出，一个输入
 * push为O(1)，只有输出栈为空才会进行数据移动，所有pop和peek均摊下来为O(1)
 *
 */
public class SimpleQueue {

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public SimpleQueue() {
        stackIn = new Stack<Integer>();
        stackOut = new Stack<Integer>();
    }

    //元素添加到尾部
    public void push(int element) {
        stackIn.add(element);
    }

    //输出第一个元素，并出栈
    public int pop() {
        while (!stackIn.isEmpty()) {
            stackOut.add(stackIn.pop());
        }
        int ret = stackOut.pop();
        while (!stackOut.isEmpty()) {
            stackIn.add(stackOut.pop());
        }
        return ret;
    }

    //输出第一个元素，不出栈
    public int top() {
        while (!stackIn.isEmpty()) {
            stackOut.add(stackIn.pop());
        }
        int ret = stackOut.peek();
        while (!stackOut.isEmpty()) {
            stackIn.add(stackOut.pop());
        }
        return ret;
    }

    @Test
    public void test() {
        SimpleQueue simpleQueue = new SimpleQueue();
        simpleQueue.push(1);
        System.out.println(simpleQueue.pop());
        simpleQueue.push(2);
        simpleQueue.push(3);
        System.out.println(simpleQueue.top());
        System.out.println(simpleQueue.pop());

    }
}
