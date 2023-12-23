package arthas;

/**
 * @author lsh
 * @date 2023/7/30 13:29
 */
public class Test {

  static String name = "hello";

  public static String getNewName(String str) throws InterruptedException {

    return name + ":" + str;
  }


  public static String getName(String str) {
    return name + " paramOut";
  }

  public static void main(String[] args) throws InterruptedException {
    while (true) {
      Thread.sleep(5000);
      getName("paramIn");
    }
  }

}
