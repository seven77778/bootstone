package algorithm.leetcode.my;

import org.junit.Test;

import java.util.*;

/**
 * @author lsh
 * @date 2023/9/11 20:30
 */
public class PicService {
  public static void main(String[] args) {
    BitSet set = new BitSet();
    set.set(1, 3);
    set.set(2, 2);
    System.out.println(set.get(1));
    System.out.println(set.get(2));
  }

  @Test
  public void test() {

    System.out.println(singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99, 1, 0}));
    System.out.println(singleNumber(new int[]{2, 2, 3, 2, 3, 3, 4, 5, 5, 5, 6, 6, 6}));
    System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
    System.out.println(singleNumber(new int[]{-2, -2, 1, 1, 4, 1, 4, 4, -4, -2}));
    System.out.println(singleNumber(new int[]{30000, 500, 100, 30000, 100, 30000, 100}));
  }


  //nums = [0,1,0,1,0,1,99]
  //输出：99
  public int singleNumber(int[] nums) {
    int[] arr = new int[60002];
    for (int i : nums) {
      if (arr[i + 30000] != 0) {
        arr[i + 30000] = 2;
        continue;
      }
      arr[i + 30000] = 1;
    }
    for (int x = 0; x < arr.length; x++) {
      if (arr[x] == 1) {
        return x-30000;
      }
    }
    return 0;
  }


  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> res = new ArrayList<>();
    List<List<Integer>> list = new ArrayList<>();
    int n = graph.length;
    for (int i = 1; i < n; i++) {
      list.add(new ArrayList<>());
    }
    for (int i = 1; i < n; i++) {
      for (int x : graph[i]) {
        list.get(i).add(x);
      }
    }
    for (int x : graph[0]) {

    }


    return res;

  }

  public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
    List<List<Integer>> res = new ArrayList<>();
    List<List<Integer>> list = new ArrayList<>();
    int n = graph.length;
    for (int i = 0; i < n; i++) {
      List<Integer> list1 = new ArrayList<>();
      for (int x : graph[i]) {
        list1.add(x);
      }
      list.add(list1);
    }

    for (int i : graph[0]) {
      ArrayList<Integer> arrayList = new ArrayList<>();
      arrayList.add(0);
      arrayList.add(i);
      allPathsSourceTargetDfs(n, i, list, res, arrayList);
    }

    return res;

  }

  public void allPathsSourceTargetDfs(int n, int i, List<List<Integer>> list, List<List<Integer>> res, ArrayList<Integer> cur) {
    if (cur.get(cur.size() - 1) == n - 1) {
      res.add(cur);
    }
    for (int x : list.get(i)) {
      ArrayList<Integer> arrayList = new ArrayList<>();
      arrayList.addAll(cur);
      arrayList.add(x);
      allPathsSourceTargetDfs(n, x, list, res, arrayList);
    }
  }


  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    List<List<Integer>> list = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    int index = 0;

    for (int i = 0; i < numCourses; i++) {
      list.add(new ArrayList<>());
    }
    for (int[] arr : prerequisites) {
      list.get(arr[1]).add(arr[0]);
      inDegree[arr[0]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      res[index++] = cur;
      for (int x : list.get(cur)) {
        inDegree[x]--;
        if (inDegree[x] == 0) {
          queue.offer(x);
        }
      }
    }
    if (index != numCourses) {
      return new int[]{};
    }
    return res;

  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    List<List<Integer>> list = new ArrayList<>();
    int[] inDegree = new int[numCourses];
    int index = 0;

    for (int i = 0; i < numCourses; i++) {
      list.add(new ArrayList<>());
    }
    for (int[] arr : prerequisites) {
      list.get(arr[1]).add(arr[0]);
      inDegree[arr[0]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      res[index++] = cur;
      for (int x : list.get(cur)) {
        inDegree[x]--;
        if (inDegree[x] == 0) {
          queue.offer(x);
        }
      }
    }
    if (index != numCourses) {
      return false;
    }
    return true;
  }

}
