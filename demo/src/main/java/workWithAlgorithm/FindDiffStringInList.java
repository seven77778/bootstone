package workWithAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsh
 * @date 2023/12/17 10:39
 *
 * 订单号|流水号|金额……
 * 订单号|流水号|金额……
 * 订单号|流水号|金额……
 *
 * 找出订单号相同，而流水号不同的数据
 */
public class FindDiffStringInList {

  @Test
  public void test(){
    List<FindDiffStringInListPO> list = new ArrayList<>();
    FindDiffStringInListPO findDiffStringInListPO1 = new FindDiffStringInListPO("123","456",100);
    FindDiffStringInListPO findDiffStringInListPO2 = new FindDiffStringInListPO("123","456",100);
    FindDiffStringInListPO findDiffStringInListPO3 = new FindDiffStringInListPO("123","456",100);
    list.add(findDiffStringInListPO1);
    list.add(findDiffStringInListPO2);
    list.add(findDiffStringInListPO3);

    //do
    long count = list.stream().map(FindDiffStringInListPO::getOrderId).count();
    
    System.out.println();
  }




  @Data
  @AllArgsConstructor
  class FindDiffStringInListPO{
    String orderId;
    String payId;
    Integer amount;
  }

}
