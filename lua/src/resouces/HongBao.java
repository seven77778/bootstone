//package resouces;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Random;
//import java.util.concurrent.CountDownLatch;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.util.StopWatch;
//import redis.clients.jedis.Jedis;
//
///**
// *
// * @date 2018/01/17
// */
//public class HongBao {
//
//    static String host = "localhost";
//    static int honBaoCount = 100;
//    static int threadCount = 2;
//
//    static String hongBaoList = "hongBaoList";
//    static String hongBaoConsumedList = "hongBaoConsumedList";
//    static String hongBaoConsumedMap = "hongBaoConsumedMap";
//    static StopWatch watch = new StopWatch();
//
//    public static void main(String[] args) throws InterruptedException {
//        try {
//            generateTestData();
//            testTryGetHongBao();
//        }catch (Exception e){
//
//        }
//    }
//
//    static public void generateTestData() throws InterruptedException {
//        Jedis jedis = new Jedis(host);
//        jedis.flushAll();
//        final CountDownLatch latch = new CountDownLatch(threadCount);
//        for(int i = 0; i < threadCount; ++i) {
//            final int temp = i;
//            Thread threadlocal = new Thread() {
//                public void run() {
//                    Jedis jedis = new Jedis(host);
//                    int per = honBaoCount/threadCount;
//                    JSONObject object = new JSONObject();
//                    for(int j = temp * per; j < (temp+1) * per; j++) {
//                        object.put("Id", j);
//                        object.put("Money", j+1);
//                        jedis.lpush(hongBaoList, object.toJSONString());
//                    }
//                    latch.countDown();
//                }
//            };
//            threadlocal.start();
//        }
//        latch.await();
//    }
//
//    static public void testTryGetHongBao() throws Exception {
//        final CountDownLatch latch = new CountDownLatch(threadCount);
//        //System.err.println("start:" + System.currentTimeMillis()/1000);
//        watch.start();
//        for(int i = 0; i < threadCount; ++i) {
//            final int temp = i;
//            Thread threadlocal = new Thread() {
//                public void run() {
//                    Jedis jedis = new Jedis(host);
//                    int j = honBaoCount/threadCount * temp;
//                    while(true) {
//                        Object object = jedis.eval(getScript(), 4, hongBaoList, hongBaoConsumedList, hongBaoConsumedMap, "" + j);
//                        j++;
//                        if (object != null) {
//                            System.out.println("抢到红包:" + object);
//                        }else {
//                            //已经取完了
//                            if(jedis.llen(hongBaoList) == 0)
//                                break;
//                        }
//                    }
//                    latch.countDown();
//                }
//            };
//            threadlocal.start();
//        }
//
//        latch.await();
//        watch.stop();
//
//        System.err.println("time:" + watch.getTotalTimeSeconds());
//        System.err.println("speed:" + honBaoCount/watch.getTotalTimeSeconds());
//        System.err.println("end:" + System.currentTimeMillis()/1000);
//    }
//
//    private static String getScript(){
//        byte[] by = null;
//        try {
//            InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
//                + "\\java\\com\\alitrip\\traveluac\\bus\\redisLua\\hongbao.lua");
//            by = new byte[input.available()];
//            input.read(by);
//        }catch (Exception e){
//
//        }
//        return new String(by);
//    }
//}
