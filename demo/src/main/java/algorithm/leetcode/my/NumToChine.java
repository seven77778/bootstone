package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/6/30 15:20
 *
 * 是给一串数字，如 104032，将它用中文表示：十万四千零三十二
 */
public class NumToChine {

  @Test
  public void test(){

    String [] nums ={"一","二","三","四","五","六","七","八","九"};
    //由于int 类型的占4个字节，所以正整数的范围为 21 4748 3647 ，所以采用 long类型 ，可以转到 千 亿
    String []unit = {" ","十","百","千","万","十","百","千","亿","十","百","千"};
    //先将数字转为字符串
    String numStr = String.valueOf(104032);
    //将该字符串numStr 存到字符数组中
    char[] chars = numStr.toCharArray();
    //获取该字符数组的长度
    int length = chars.length;
    //定义接获凭借的字符串
    String str = "";
    for (int i = 0; i < length; i++) {
      char cha = chars[i];
      // 将字符 转为 int 类型
      int c = cha - '0';
      //如果数字是就不用处理（不用凭借中文数字字符和中文数字单位）
      //c 不是 0，中文数字： nums[c - 1]
      //         中文单位：unit[length - c - 1]
      if (c != 0){
        str += nums[c - 1] + unit[length - i - 1];
      }
    }
    System.out.println(str);
  }
}
