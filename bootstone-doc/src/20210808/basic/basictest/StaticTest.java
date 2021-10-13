package basictest;

public class StaticTest {

    //声明为 private 避免调用默认构造方法创建对象
    private StaticTest() {
        System.out.println("1111");
    }

    // 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
    private static class SingletonHolder {
        private static final StaticTest INSTANCE = new StaticTest();
    }

    public static StaticTest getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。
     * 只有当调用 getUniqueInstance()方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，
     * 此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
     *
     * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(SingletonHolder.INSTANCE==null);//访问到instance才加载
        StaticTest s1 = StaticTest.getUniqueInstance();
        StaticTest s2 = StaticTest.getUniqueInstance();
        System.out.println(SingletonHolder.INSTANCE);
        System.out.println(s1==s2);//为什么不是每次去new？todo

    }

}

