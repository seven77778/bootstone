//package com.lsh.demo.lua.luatest;
//
//import com.google.common.io.CharStreams;
//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import org.keplerproject.luajava.LuaState;
//import org.keplerproject.luajava.LuaStateFactory;
//import redis.clients.jedis.*;
//import java.io.*;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by wb-lsh301927 on 2018/1/12.
// *
// * @author wb-lsh301927
// * @date 2018/01/12
// */
//public class LuaTest {
//
//    private boolean accessLimit(String ip, int limit, int time, Jedis jedis) {
//        boolean result = true;
//
//        String key = "rate.limit:" + ip;
//        if (jedis.exists(key)) {
//            long afterValue = jedis.incr(key);
//            if (afterValue > limit) {
//                result = false;
//            }
//        } else {
//            Transaction transaction = jedis.multi();
//            transaction.incr(key);
//            transaction.expire(key, time);
//            transaction.exec();
//        }
//        return result;
//    }
//
//    private Jedis getCon (){
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("redis状态：" + jedis.ping());
//        return jedis;
//
//    }
//
//    @Test
//    public void tet() throws Exception{
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("redis状态：" + jedis.ping());
//        Pipeline pipe = jedis.pipelined();
//        pipe.set("pipe1","1");
//        pipe.set("pipe2","2");
//        List<Object> list = pipe.syncAndReturnAll();
//        Pipeline pipe2 = jedis.pipelined();
//        Response<String> key = pipe2.get("key");
//        String lockkey = "lockkey";
//        pipe2.watch(lockkey);
//        if(pipe2.get(lockkey).equals("1")){
//            pipe2.multi();
//            pipe2.del(lockkey);
//            pipe2.exec();
//            System.out.println("success");
//        }
//        List<Object> list2 = pipe2.syncAndReturnAll();
//        System.out.println("-->" + key.toString());
//
//    }
//
//    /**
//     * lua脚本实现
//     */
//    private  String accessLimitbyLua(String key1, int argv1) throws IOException {
//        return  getCon().eval(loadScriptString()).toString();
//    }
//
//    // 加载Lua代码
//    private static String loadScriptString() throws IOException {
//        String fileName = "com/alitrip/traveluac/bus/lua/limit.lua";
//        Reader reader = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream(fileName));
//        return CharStreams.toString(reader);
//    }
//
//   @Test
//    public void test() throws Exception{
//       String fileName = "com/alitrip/traveluac/bus/lua/limit.lua";
//       Reader reader = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream(fileName));
//       String res = getCon().eval(CharStreams.toString(reader)).toString();
//
//       System.out.println("-->" +res);
//   }
//
//   @Test
//    public void luaTest() throws  Exception{
//       LuaState state = LuaStateFactory.newLuaState();
//       state.openLibs();
//       System.out.println("**********");
//       // 加载脚本hello.lua,并执行
//       int res = state.LdoFile("tableTest.lua");
//       System.out.println("-->" + res);
//
//
//
//   }
//
//   @Test
//    public void test3() throws Exception{
//       Jedis jedis = new Jedis("localhost");
//       Transaction transaction = jedis.multi();
//       transaction.watch("key");
//       transaction.lpush("key", "11");
//       transaction.lpush("key", "22");
//       transaction.lpush("key", "33");
//       List<Object> list = transaction.exec();
//   }
//
//
//   @Test
//    public  void testWach(){
//        Jedis jedis = new Jedis("localhost");
//        String watch = jedis.watch("testabcd");
//        System.out.println(Thread.currentThread().getName()+"--"+watch);
//        Transaction multi = jedis.multi();
//        multi.set("testabcd", "23432");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<Object> exec = multi.exec();
//        System.out.println("---"+exec);
//        jedis.unwatch();
//    }
//
//    @Test
//    public void tt()throws Exception {
//        File ss = new File("return.lua");
//        FileUtils.openInputStream(new File("return.lua"));
//    }
//    @Test
//    public void testfile() throws Exception{
//        Jedis jedis = new Jedis("localhost") ;
//        InputStream input = new FileInputStream("return.lua");
//        byte[] by = new byte[input.available()];
//        input.read(by);
//        String script = new String(by);
//        Object obj = jedis.eval(script, Arrays.asList("127.0.0.1"), Arrays.asList("10"));
//        System.out.println("res  " + obj);
//
//    }
//
//    public static void main( String[] args ) throws Exception {
//        try(Jedis jedis = new Jedis("localhost") ;
//            InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
//                + "\\java\\com\\alitrip\\traveluac\\bus\\lua\\return.lua")) {
//            byte[] by = new byte[input.available()];
//            input.read(by);
//            String script = new String(by);
//            Object obj = jedis.eval(script, Arrays.asList("127.0.0.1"), Arrays.asList("10"));
//            System.out.println(obj);
//        }
//    }
//
//    @Test
//    public void testuid()throws Exception{
//        String uid = UUID.randomUUID().toString();
//        System.out.println("-->" + uid);
//    }
//
//
//    public String lock(Jedis jedis, String lockName,  Integer timeout) {
//        String uid = UUID.randomUUID().toString();
//        //3.0.1用法
//     /*   SetParams setParams = new SetParams();
//        setParams.ex(100);
//        setParams.nx();
//        if("OK".equals(jedis.set(lockName,uid,setParams))){
//            return uid;
//        }*/
//        //todo 3.0.1 中没有了此方法，2.9.0中还有
//        if("OK".equals(jedis.set(lockName, uid,"nx","px",timeout)) ) {
//            return uid;
//        }
//        return null;
//    }
//
//
//
//
//        public boolean unLock(Jedis jedis, String lockName, String uid) throws Exception{
//            jedis.watch(lockName);
//            //这里的判断uid和下面的del虽然不是原子性，有了watch可以保证不会误删锁
//            if (jedis.get(lockName).equals(uid)) {
//                redis.clients.jedis.Transaction transaction = jedis.multi();
//                transaction.del(lockName);
//                transaction.set("key1","value1");
//                transaction.lpush("key1","value1");
//                List<Object> exec = transaction.exec();
//                if (exec.get(0).equals("OK")) {
//                    transaction.close();
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        @Test
//        public void sssst()throws Exception{
//        Jedis jedis = new Jedis("localhost");
//            redis.clients.jedis.Transaction transaction = jedis.multi();
//            transaction.set("key1","value1");
//            transaction.lpush("key1","value2");
//            transaction.set("key3","value3");
//            List<Object> exec = transaction.exec();
//        }
//
//
//    @Test
//    public void testwhile (){
//        while(true){
//            System.out.println("true");
//        }
//    }
//
//}
