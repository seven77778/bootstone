package work.writtenexam;

public class Singleton {

    //volatile 保证可见性 禁止重排序
    private volatile static Singleton singleton = null;

    //防止单例被破坏
    private Singleton() {
    }

    //方法1，双重检查模式
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


}
