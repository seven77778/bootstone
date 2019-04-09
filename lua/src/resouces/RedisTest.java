//package resouces;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * Created by wb-lsh301927 on 2018/1/17.
// *
// * @author wb-lsh301927
// * @date 2018/01/17
// */
//public class RedisTest {
//
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        final LuaDemo redis = ctx.getBean("luaDemo",LuaDemo.class);
//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                String key = "cheng";
//
//                @Override
//                public void run() {
//                    boolean lock = redis.lock(key, 30);
//                    System.out.println(lock + "-");
//
//                }
//            }).start();
//            ;
//        }
//        // redis.unlock(key);
//        // ctx.close();
//    }
//
//
//    @org.junit.Test
//    public  void test2() {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        final LuaDemo redis = ctx.getBean(LuaDemo.class);
//        for (int i = 0; i < 500; i++) {
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//
//            }
//            //if (i % 2 == 0) {
//                new Thread(new Runnable() {
//                    String key = "lock";
//                    public void run() {
//                        boolean lock = redis.lock(key, 100000);
//                        if (lock) {
//                            System.out.println(lock + "-------------------");
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } finally {
//                                redis.unlock(key);
//                            }
//                            lock = redis.lock(key, 1);
//                            if (lock)
//                                System.out.println(lock + "*************");
//                        }
//                    }
//                }).start();
//            //} else {
//                new Thread(new Runnable() {
//                    String key = "cheng";
//                    public void run() {
//                        boolean lock = redis.lock(key, 1);
//                        if (lock) {
//                            System.out.println(lock + "----------*********");
//                            try {
//                                Thread.sleep(980);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } finally {
//                                redis.unlock(key);
//                            }
//                        }
//                    }
//                }).start();
//            //}
//        }
//        // redis.unlock(key);
//        // ctx.close();
//    }
//
//    @Autowired
//    private RedisClient client;
//
//    @Test
//    public void t() throws Exception{
//        String ss = client.getConn().get("key");
//        System.out.println("-->" + ss);
//    }
//
//}
