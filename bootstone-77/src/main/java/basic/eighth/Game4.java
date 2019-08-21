package basic.eighth;

public class Game4 {
    public static void main(String[] args) {
        float a = 80;
        float b = a/2;
        int i;

        for (i = 0;i < 5;i++){
            a = a + 2 * b;
            b = b / 2;
        }
        System.out.println(a);
        System.out.println(b);
    }
}
