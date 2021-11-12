package com.lsh.demo.aaamianshi.springcircle;

/**
 * The dependencies of some of the beans in the application context form a cycle:
 * ┌─────┐
 * |  myA defined in file [D:\WORKCODE\bootstone\bootstone-study\target\classes\com\lsh\demo\aaamianshi\springcircle\MyA.class]
 * ↑     ↓
 * |  myB defined in file [D:\WORKCODE\bootstone\bootstone-study\target\classes\com\lsh\demo\aaamianshi\springcircle\MyB.class]
 * └─────┘
 *
 * spring循环依赖
 */
//@Component
public class MyA {
    public MyA(MyB m) {
    }
}
