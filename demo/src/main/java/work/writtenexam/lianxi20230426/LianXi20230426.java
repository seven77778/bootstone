package work.writtenexam.lianxi20230426;

/**
 * @author lsh
 * @date 2023/4/26 16:50
 */
public class LianXi20230426 {

    private volatile static LianXi20230426 singleton = null;

    public LianXi20230426() {

    }

    public static LianXi20230426 getSingleton() {
        if (singleton == null) {
            synchronized (LianXi20230426.class) {
                singleton = new LianXi20230426();
            }
        }
        return singleton;
    }
}
