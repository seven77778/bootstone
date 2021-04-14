/**
 * 说下spring事务，@Transition 这个注解在哪些情况下不会生效 -- 事务失效
 *  1.public以外的方法上
 *   -- 解析 ：CGLib通过ASM动态操作指令生成了被代理类的子类，
 *   重写了目标类中所有的非private、非final、非static方法，也就是 public&protected&默认
 *
 *  2.Spring会对unchecked异常进行事务回滚；如果是checked异常则不回滚
 *  比如IO异常，网络超时就不回滚，nullpoint这种回滚
 *  3. 设置只读事务 readOnly=true
 *  4.springboot项目，有没有开启事务@EnableTransactionManagement
 *
 * Synchronized 基本原理
 *
 */

public class Answer1231Baidu {

}
