package myspring.dispatcher;

import javax.servlet.http.HttpServlet;

/**
 * Created by LSH on 2019/5/2 - 11:02.
 * <p>
 * declaration :
 */
public class MyDispatcherServlet extends HttpServlet implements Runnable {

    private static MyDispatcherServlet myDispatcherServlet = new MyDispatcherServlet();

    public static void main(String[] args) {
       for(int i=0;i<5;i++){
           if (i == 2) {
               continue;
           }
           System.out.println(i);
       }
    }

    @Override
    public void run() {
        System.out.println("123");
    }
}
