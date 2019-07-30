package basic.eighth;

/**
 * Created by lsh on 2019-07-30.
 */
public class Game3 {
    /**
     * 生物实验室 细菌繁殖试验
     * 第一代 1 个
     * 第二代 2个
     * 第三代 4个
     * 以此类推的话，
     * 第四代 8个
     * 第五代 16个，好像每次都是乘以2
     * 6-32
     * 7-64
     * 8-128
     * 9-256
     * 10-512
     *
     * 思路，循环个10次,用while循环10次也是可以的鸭,引入一个中间变量，计数器，记到10次
     * @param args
     */
    public static void main(String[] args) {

        int cellNumbers = 1;//第一代细菌
        int count = 1;//纯粹的技术器，每次循环给它加1，加到10就停止
        while (count <= 10){
            cellNumbers = cellNumbers * 2;
            System.out.println("这是第" + count + "次执行，现在细胞数量为 " + cellNumbers);
            count++;
        }
        System.out.println("第十代细菌 是 " + cellNumbers);
    }
}
