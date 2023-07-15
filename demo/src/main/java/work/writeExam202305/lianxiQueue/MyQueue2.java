package work.writeExam202305.lianxiQueue;

import org.junit.Test;

import java.util.Stack;

/**
 * @author lsh
 * @date 2023/5/15 21:58
 * <p>
 * Stack类里面主要实现的有以下的几个方法：后进先出。
 * <p>
 * (1)boolean empty( )方法是判断堆栈是否为空。
 * <p>
 * (2)Object peek( )方法是 返回  栈顶端的元素，但不从堆栈中移除它。
 * <p>
 * (3)Object pop( )方法是  移除  栈顶部的对象，并作为此函数的值返回该对象。
 * <p>
 * (4)Object push (Object element)方法是把元素压入栈。
 * <p>
 * (5)int search(Object element)方法是返回对象在堆栈中的位置，它是以1为基数。
 */
public class MyQueue2 {


    Stack stack1;//进来的元素都放在里面
    Stack stack2;//元素都从这里出去

    public MyQueue2() {
        // do intialization if necessary
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public Integer pop() {
        // write your code here
        if (stack2.isEmpty()) {//如果没有元素,就把负责放入元素的栈中元素全部放进来
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        if (stack2.empty()) {
            return null;
        } else {
            return (int) stack2.pop();//有元素后就可以弹出了
        }
    }

    /*
     * @return: An integer
     */
    public Integer top() {
        // write your code here
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.empty()) {
            return null;
        } else {
            return (int) stack2.peek();
        }
    }

    @Test
    public void test() {
        MyQueue2 myQueue = new MyQueue2();
        myQueue.push(1);
        System.out.println(myQueue.pop());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.top());
        System.out.println(myQueue.top());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.top());//null
        System.out.println(myQueue.pop());//null
    }

}
