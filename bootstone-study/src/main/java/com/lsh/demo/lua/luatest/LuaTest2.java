//package com.lsh.demo.lua.luatest;
//
//import org.junit.Test;
//import org.keplerproject.luajava.LuaException;
//import org.keplerproject.luajava.LuaObject;
//import org.keplerproject.luajava.LuaState;
//import org.keplerproject.luajava.LuaStateFactory;
//import redis.clients.jedis.Jedis;
//
///**
// * Created by wb-lsh301927 on 2018/1/15.
// *
// * @author wb-lsh301927
// * @date 2018/01/15
// */
//public class LuaTest2 {
//
//
//    private Jedis getCon (){
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("redis状态：" + jedis.ping());
//        return jedis;
//    }
//
//
//    @Test
//    public void test1() throws Exception {
//        Jedis jedis = new Jedis("localhost");
//        //String fileName = "com/alitrip/traveluac/bus/lua/limit.lua";
//        //Reader reader = new InputStreamReader(Client.class.getClassLoader().getResourceAsStream(fileName));
//        //String res = getCon().eval(CharStreams.toString(reader)).toString();
//
//        //System.out.println("-->" +res);
//
//
//
//
//    }
//
//    @Test
//    public void test2() throws Exception{
//        Jedis jedis = new Jedis("localhost");
//        Object res2 = jedis.eval("return redis.call('GET',KEYS[1])", 1, "pipe1");
//        System.out.println("-->" +res2);
//    }
//
//    @Test
//    public void test4() throws Exception{
//        LuaState L = LuaStateFactory.newLuaState();
//        // 加载lua标准库,否则一些lua基本函数无法使用
//        L.openLibs();
//        // doFile
//        L.LdoFile("res/test01.lua");
//
//        //---------------------------------------------值传递测试
//        // 找到函数 sum
//        L.getField(LuaState.LUA_GLOBALSINDEX, "sum");
//
//        // 参数1压栈
//        L.pushNumber(100);
//
//        // 参数2压栈
//        L.pushNumber(50);
//
//        // 调用，共2个参数1个返回值
//        L.call(2, 1);
//
//        // 保存返回值到result中
//        L.setField(LuaState.LUA_GLOBALSINDEX, "result");
//
//        // 读入result
//        LuaObject lobj = L.getLuaObject("result");
//        // 打印结果
//        System.out.println(lobj.getNumber());
//
//        //---------------------------------------------对象传递测试
//        Value v = new Value();
//
//        L.getField(LuaState.LUA_GLOBALSINDEX, "test1");
//
//        try {
//            L.pushObjectValue(v);
//        } catch (LuaException e) {
//            e.printStackTrace();
//        }
//
//        L.call(1, 0);
//
//        v.print();
//    }
//
//    class Value {
//        private int i;
//
//        public void init(){
//            i = 111;
//        }
//
//        public void print(){
//            System.out.println(i);
//        }
//
//    }
//
//}
