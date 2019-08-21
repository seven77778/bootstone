import java.security.PublicKey;

public class qqq {
    public static void main(String[] args) {
        int xiaoa = 3000;
        int xiaob = 4500;
        System.out.println("a要不要缴税：" + jiaoshui(xiaoa));
        System.out.println("b要不要缴税:" + jiaoshui(xiaob));

    }
    public static boolean jiaoshui (int x){
        if (x > 3500) {
            return true;
        }else {
            return false;
        }

    }
}
