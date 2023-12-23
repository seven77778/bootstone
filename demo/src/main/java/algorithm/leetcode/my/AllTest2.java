package algorithm.leetcode.my;

import algorithm.leetcode.ListNode;
import org.junit.Test;

import java.util.*;

/**
 * @author lsh
 * @date 2023/8/13 9:58
 */
public class AllTest2 {


  @Test
  public void test() {
    int[][] arr8 = {{1, 0}, {1, 2}, {3, 0}, {2, 3}};
    System.out.println(canFinish(4, arr8));
    int[][] arr7 = {{1, 0}, {0, 2}, {2, 1}};
    System.out.println(canFinish(3, arr7));

    int[][] arr6 = {{0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}};
    System.out.println(canFinish(20, arr6));
    int[][] arr3 = {{1, 0}, {2, 1}};
    System.out.println(canFinish(2, arr3));

    int[][] arr1 = {{1, 0}};
    System.out.println(canFinish(2, arr1));

    int[][] arr2 = {{1, 0}, {0, 1}};
    System.out.println(canFinish(2, arr2));

    int[][] arr4 = {{1, 2}, {2, 3}};
    System.out.println(canFinish(4, arr4));
    int[][] arr5 = {{0, 2}, {1, 2}, {2, 0}};
    System.out.println(canFinish(3, arr5));


  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    HashMap<Integer, List<Integer>> mapList = new HashMap<>();
    for (int i = 0; i < prerequisites.length; i++) {
      int key = prerequisites[i][0];
      int value = prerequisites[i][1];
      if (key == value) {
        return false;
      }
      if (mapList.containsKey(key)) {
        List<Integer> list = mapList.get(key);
        if (list.contains(value)) {
          return false;
        } else {
          backFind(mapList, list, value);
          list.add(value);
          mapList.put(key, list);
        }
      } else {
        List<Integer> valueList = mapList.get(value);
        if (valueList == null) {
          valueList = new ArrayList<>();
          valueList.add(key);
        } else {
          valueList.add(key);
        }
        mapList.put(value, valueList);
      }
    }
    return true;
  }

  public void backFind(HashMap<Integer, List<Integer>> mapList, List<Integer> list, int value) {
    //1-0,0-2 这种，应该也放进去一个1-2
    for (int x : list) {
      List<Integer> valueList = new ArrayList<>();
      valueList.add(x);
      List<Integer> temp = mapList.get(value);
      if (null != temp) {
        valueList.addAll(temp);
      }
      mapList.put(value, valueList);
    }
  }


  public int eliminateMaximum(int[] dist, int[] speed) {
    int n = dist.length;
    for (int i = 0; i < n; i++) {
      dist[i] = (dist[i] - 1) / speed[i] + 1;
    }
    Arrays.sort(dist);
    for (int i = 0; i < n; i++) {
      if (dist[i] <= i) {
        return i;
      }
    }
    return n;
  }

  public int eliminateMaximum2(int[] dist, int[] speed) {
    int sum = 0;
    HashMap<Integer, Integer> speedMap = new HashMap<>();
    for (int i = 0; i < dist.length; i++) {
      speedMap.put(i, speed[i]);
    }
    for (int i = 0; i < dist.length; i++) {
      PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
      HashMap<Integer, Integer> map = new HashMap<>();
      int countPerMin = 0;
      for (int x = 0; x < dist.length; x++) {
        if (dist[x] == 0) {
          continue;
        }
        int temp = dist[x] - speed[x] * (i + 1);
        if (temp <= 0) {
          countPerMin++;
          if (countPerMin > 1) {
            return sum;
          }
          sum++;
          dist[x] = 0;
          continue;
        }
        if (priorityQueue.isEmpty()) {
          priorityQueue.offer(temp);
          map.put(temp, x);
        } else {
          if (priorityQueue.peek() > temp) {
            priorityQueue.poll();
            priorityQueue.offer(temp);
            map.put(temp, x);
          } else if (priorityQueue.peek() == temp) {
            if (speed[x] > speedMap.get(map.get(priorityQueue.peek()))) {
              priorityQueue.poll();
              priorityQueue.offer(temp);
              map.put(temp, x);
            }
          }
        }
      }
      //no must die,delete min
      if (countPerMin == 0 && !priorityQueue.isEmpty()) {
        int z = priorityQueue.poll();
        dist[map.get(z)] = 0;
        sum++;
      }
    }

    return sum;
  }


  //：dist = [1,3,4], speed = [1,1,1]
  //输出：3
  public int captureForts(int[] forts) {
    int sum = 0;
    int index = 0;
    int last = -1;
    int n = forts.length;
    while (index < n) {
      if (forts[index] == 1 || forts[index] == -1) {
        if (last >= 0 && forts[index] != forts[last]) {
          sum = Math.max(sum, index - last - 1);
        }
        last = index;
      }
      index++;
    }
    return sum;
  }


  public void rotate(int[] nums, int k) {
    int n = nums.length;
    int[] arr = Arrays.copyOf(nums, n);
    k = k > n ? k % n : k;
    for (int i = 0; i < n; i++) {
      nums[i] = arr[(i + n - k) % n];
    }
    System.out.println(12);
  }


  //nums = [2,2,1,1,1,2,2]
  //输出：2
  public int majorityElement(int[] nums) {
    int n = nums.length;
    int sum = 0;
    int target = nums[0];
    for (int x : nums) {
      if (sum == 0) {
        target = x;
        sum++;
      } else if (sum > 0 && target != x) {
        sum--;
      } else {
        sum++;
        if (sum > n / 2) {
          return x;
        }
      }
    }
    return target;
  }

  //nums = [1,1,1,2,2,3]
  //输出：5, nums = [1,1,2,2,3]
  public int removeDuplicates(int[] nums) {
    int slow = 2;
    int fast = 2;
    while (fast < nums.length) {
      if (nums[slow - 2] == nums[fast]) {
        fast++;
      } else {
        nums[slow] = nums[fast];
        slow++;
        fast++;
      }
    }
    return slow;
  }


  //intervals = [[1,3],[2,6],[8,10],[15,18]]
  //输出：[[1,6],[8,10],[15,18]]

  @Test
  public void test333() {
    int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.toString(merge(arr)));

    int[][] arr7 = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
    System.out.println(Arrays.toString(merge(arr7)));
    int[][] arr6 = {{1, 4}, {0, 1}, {0, 2}};
    System.out.println(Arrays.toString(merge(arr6)));


    int[][] arr2 = {{1, 3}, {2, 6}, {8, 17}, {15, 18}};
    System.out.println(Arrays.toString(merge(arr2)));

    int[][] arr3 = {{1, 4}, {0, 4}};
    System.out.println(Arrays.toString(merge(arr3)));
    int[][] arr4 = {{1, 4}, {0, 1}};
    System.out.println(Arrays.toString(merge(arr4)));
    int[][] arr5 = {{1, 4}, {0, 0}};
    System.out.println(Arrays.toString(merge(arr5)));
  }

  public int[][] merge(int[][] intervals) {
    int index = 0;
    List<int[]> list = new ArrayList<>();
    int n = intervals.length;
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });
    while (index < n) {
      int max = intervals[index][1];
      int min = intervals[index][0];
      while (index + 1 < n && max >= intervals[index + 1][0]) {
        min = Math.min(min, intervals[index + 1][0]);
        max = Math.max(max, intervals[index + 1][1]);
        index++;
      }
      list.add(new int[]{min, max});
      index++;

    }
    int[][] res = new int[list.size()][2];
    for (int i = 0; i < list.size(); i++) {
      System.out.println(Arrays.toString(list.get(i)));
      res[i][0] = list.get(i)[0];
      res[i][1] = list.get(i)[1];
    }
    return res;
  }


  public int[][] merge1(int[][] intervals) {

    List<int[]> list = new ArrayList<>();
    int index = 0;
    int n = intervals.length;

    while (index < n) {
      int[] arr = new int[2];
      arr[0] = intervals[index][0];
      arr[1] = intervals[index][1];

      while (index + 1 < n && overlap(arr, intervals[index + 1])) {
        arr[0] = Math.min(arr[0], intervals[index + 1][0]);
        arr[1] = Math.max(arr[1], intervals[index + 1][1]);
        index++;
      }

      if (arr[0] > -1) {

        list.add(arr);
        arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;
      }
      index++;

    }


    int[][] res = new int[list.size()][2];
    for (int i = 0; i < list.size(); i++) {
      System.out.println(Arrays.toString(list.get(i)));
      res[i][0] = list.get(i)[0];
      res[i][1] = list.get(i)[1];
    }
    return res;
  }

  public boolean overlap(int[] arr, int[] brr) {
    if (arr[0] == brr[0] || arr[0] == brr[1] || arr[1] == brr[0] || arr[1] == brr[1]) {
      return true;
    }
    if (arr[0] > brr[0] && arr[1] > brr[1] && arr[0] < brr[1] || arr[0] < brr[1] && arr[1] < brr[1] && arr[1] > brr[0]) {
      return true;
    }
    if (arr[0] < brr[0] && arr[1] > brr[1] || arr[0] > brr[0] && arr[1] < brr[1]) {
      return true;
    }
    return false;
  }


  @Test
  public void testOverLap() {
    System.out.println(overlap(new int[]{1, 2}, new int[]{4, 5}));
    System.out.println(overlap(new int[]{1, 9}, new int[]{4, 5}));
    System.out.println(overlap(new int[]{1, 4}, new int[]{0, 1}));
    System.out.println(overlap(new int[]{0, 1}, new int[]{1, 4}));
  }


  public int subarraySum(int[] nums, int k) {
    int sum = 0;
    int temp = 0;
    for (int i = 0; i < nums.length; i++) {
      temp = 0;
      for (int j = i; j < nums.length; j++) {
        temp += nums[j];
        if (temp == k) {
          sum++;

        }
      }
    }
    return sum;

  }

  //nums = [1,2,3], k = 3
  //输出：2
  public int subarraySum2(int[] nums, int k) {
    int sum = 0;
    int index = 0;
    int n = nums.length;
    int temp = 0;
    int lastIndex = -1;
    int step = 1;
    while (index < n) {
      if (nums[index] == k) {
        if (k == 0 && lastIndex == index - 1) {
          step++;
          lastIndex = index;
          sum += step;
        } else {
          sum++;
          step = 1;
          lastIndex = index;
        }
      } else if (index < n - 1) {
        if (nums[index] < 0 && nums[index] + nums[index + 1] == k) {
          sum++;
          lastIndex = index;
        } else {
          temp += nums[index];
          if (temp == k) {
            sum++;
            lastIndex = index;
            temp = nums[index];
          }
        }
      }
      index++;
    }
    return sum;
  }


  //nums = [0,2,3,4,6,8,9]
  //输出：["0","2->4","6","8->9"]
  public List<String> summaryRanges(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    List<String> res = new ArrayList<>();
    int n = nums.length;
    String str = "->";
    int index = 0;
    while (index < n) {
      StringBuilder sb = new StringBuilder();
      int start = index;
      sb.append(nums[index]);
      while (index + 1 < n && nums[index + 1] - nums[index] == 1) {
        index++;
      }
      if (start == index) {
        res.add(sb.toString());
      } else {
        sb.append(str).append(nums[index]);
        res.add(sb.toString());
      }
      index++;

    }

    return res;
  }


  public boolean isIsomorphic(String s, String t) {
    int m = s.length();
    int n = t.length();
    if (m != n) {
      return false;
    }
    int left = 0;
    int right = 0;
    char x = s.charAt(0);
    char y = t.charAt(0);
    int diff = x - y;
    while (left < m && right < m) {
      int temp = s.charAt(left) - t.charAt(right);
      if (diff != temp && temp != 0) {
        return false;
      } else {
        diff = temp;
      }
      left++;
      right++;
    }
    return true;
  }


  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode res = head;
    while (head.next != null) {
      ListNode temp = head.next;
      if (head.val == head.next.val) {
        head.next = head.next.next;
        continue;
      }
      head = temp;
    }
    return res;
  }


  public void merge(int[] nums1, int m, int[] nums2, int n) {
    //nums1为空的情况，直接赋值nums2
    if (m == 0) {
      for (int i = 0; i < n; i++) {
        nums1[i] = nums2[i];
      }
      return;
    }
    //nums1的位置，倒序
    int left = m - 1;
    //nums2的位置，倒序
    int right = n - 1;
    //最终结果的指针，倒序
    int index = m + n - 1;
    while (left >= 0 || right >= 0) {
      if (left < 0) {
        nums1[index--] = nums2[right--];
      } else if (right < 0) {
        nums1[index--] = nums1[left--];
      } else if (nums1[left] >= nums2[right]) {
        nums1[index--] = nums1[left--];
      } else if (nums1[left] < nums2[right]) {
        nums1[index--] = nums2[right--];
      }
    }

    System.out.println(Arrays.toString(nums1));
  }


}
