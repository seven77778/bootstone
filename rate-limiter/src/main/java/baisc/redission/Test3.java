package baisc.redission;

import org.junit.Test;

import java.util.Stack;

public class Test3 {
    public class SimpleQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public SimpleQueue() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        public void push(int element) {
            stack1.add(element);
        }

        //弹出第一个数，并返回值
        public int pop() {
            while(!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
            int ret = stack2.pop();

            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }

            return ret;
        }

        //返回第一数值，不弹出
        public int top() {
            while(!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
            int ret = stack2.peek();

            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }

            return ret;
        }
    }

    @Test
    public void test(){

    }
}
