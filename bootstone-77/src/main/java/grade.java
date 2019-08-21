import java.util.Scanner;
public class grade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入成绩:");
        int grade = sc.nextInt();
        if(grade < 60){
            System.out.println("不合格");
        }else if (grade >= 60 && grade < 80){
            System.out.println("合格");
        }else if (grade >= 80 && grade < 90){
            System.out.println("良好");
        }else if (grade >= 90){
            System.out.println("优秀");
        }
        sc.close();
    }

}
