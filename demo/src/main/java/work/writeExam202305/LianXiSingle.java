package work.writeExam202305;

/**
 * @author lsh
 * @date 2023/5/16 16:13
 */
public class LianXiSingle {

    public LianXiSingle() {

    }

    private  volatile static LianXiSingle single = null;

    public static LianXiSingle getInstance() {
        if (single == null) {
            synchronized (LianXiSingle.class) {
                if (single == null) {
                    single = new LianXiSingle();
                }
            }
        }
        return single;
    }

}
