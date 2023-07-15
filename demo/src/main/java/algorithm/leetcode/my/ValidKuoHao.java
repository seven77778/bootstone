package algorithm.leetcode.my;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author lsh
 * @date 2023/6/26 9:42
 * 有效的括号！
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *  
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 注意："([)]" --> 应该是false
 */
public class ValidKuoHao {

  @Test
  public void test() {
    System.out.println(isValid("()[]{}"));
    System.out.println(isValid("()[]{}{}"));
    System.out.println(isValid("([{}])"));
    System.out.println(isValid("(]"));
    System.out.println(isValid("([)]"));

  }



  // ok
  //利用栈先进先出的特性，题目中（）必须挨着，正好可以利用栈来解决
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == '{') {
        stack.push('}');
      } else {
        if (stack.isEmpty()) {
          return false;
        } else {
          if (stack.pop() != c) {
            return false;
          }
        }
      }
    }
    return stack.isEmpty();
  }

  public boolean isValid2(String s) {
    if (s.length() % 2 != 0) {
      return false;
    }
    HashMap<String, Integer> map = new HashMap<>();
    map.put("(", 0);
    map.put("[", 0);
    map.put("{", 0);
    for (char c : s.toCharArray()) {
      switch (c) {
        case '(': {
          map.put("(", map.get("(") + 1);
        }
        break;

        case '[': {
          map.put("[", map.get("[") + 1);
        }
        break;

        case '{': {
          map.put("{", map.get("{") + 1);
        }
        break;

        case ')': {
          if (map.get("(") < 0) {
            return false;
          } else {
            map.put("(", map.get("(") - 1);
          }
        }
        break;

        case ']': {
          if (map.get("[") < 0) {
            return false;
          } else {
            map.put("[", map.get("[") - 1);
          }
        }
        break;

        case '}': {
          if (map.get("{") < 0) {
            return false;
          } else {
            map.put("{", map.get("{") - 1);
          }
        }
        break;
      }

    }
    if (map.get("(") != 0 || map.get("[") != 0 || map.get("{") != 0) {
      return false;
    }

    return true;
  }
}
