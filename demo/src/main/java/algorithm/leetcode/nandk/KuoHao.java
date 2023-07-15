package algorithm.leetcode.nandk;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lsh
 * @date 2023/6/16 21:36
 */
public class KuoHao {


    @Test
    public void test() {
        System.out.println( isValid( "((()))[][]{}{{}}" ) );
        System.out.println( isValid( "()" ) );
        System.out.println( isValid( ")()" ) );

        System.out.println( isValid( ")(" ) );

    }


    public boolean isValid(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }

        return stack.isEmpty();
    }
}
