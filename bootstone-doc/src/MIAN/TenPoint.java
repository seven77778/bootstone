import org.junit.Test;

/**
 * Created by lsh on 2019-12-02.
 * 迈进java初中级程序员分水岭是否合格？十个题告诉你！
 * https://www.cnblogs.com/yichunguo/p/11968660.html
 */
public class TenPoint {


    @Test
    public void test1() {
        short s = 1;
        /*
        require short,found int
        s = s + 1 计算结果被提升为int类型，再向short类型赋值时发生错误

         */
//        s = s + 1;
        System.out.println(s);
    }


    /**
     * s+=1 可以认为是s=s+1，结果为int，因为1 是int，
     * int赋值给short，再向short类型赋值时发生错误，因为不能将取值范围 大的类型赋值到取值范围小的类型
     * <p>
     * 但是
     * <p>
     * s=s+1进行两次运算 ， += 是一个运算符，只运算一次，并带有强制转换的特点，也就是
     * s = (short)(s + 1)
     */
    @Test
    public void test2() {
        short s = 1;
        s += 1;
        System.out.println(s);
        System.out.println(((Object) s).getClass().toString());
    }


    /**
     * b3是byte，1+2=3，也还在byte范围内，b3不报错
     * b4报错，b2+b3 虽然都还是short，但是
     * 在编译的时候，编译器javac不确定b2+b3的结果是什 么，因此会将结果以int类型进行处理
     */
    @Test
    public void test3() {
        byte b1 = 1;
        byte b2 = 2;
        byte b3 = 1 + 2;
//        byte b4 = b2 + b3;
        System.out.println(b3);
//        System.out.println(b4);
    }


    @Test
    public void test4(){
        short s = 32767;
        s = (short)(s + 10);
        System.out.println(s);
    }
}
