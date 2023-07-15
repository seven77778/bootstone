package algorithm.leetcode.my;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/5 15:15
 *
 * break 、continue都是只关注当前这一层循环
 * break，跳出这一轮
 * continue 跳过这一次
 */
public class BreakContinueTest {

  @Test
  public void test(){

    for(int i=0;i<3;i++){

      for(int j=0;j<3;j++){
        if(j==1){
          return;
        }
        System.out.println(" j--" + j);

      }
      System.out.println("i--" + i);

    }
  }
}
