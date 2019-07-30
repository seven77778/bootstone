package basic.eighth;

/**
 * Created by lsh on 2019-07-30.
 */
public class Game2 {

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
     * 思路，循环个10次，乘以2
     * @param args
     */
    public static void main(String[] args) {

        int cellNumbers = 1;//第一代细菌
        for (int i =0;i<9;i++){ //上面是第一代，在循环9次，就是第十代了吧
            cellNumbers = cellNumbers*2;//每一代都是上一代的2倍
        }
        System.out.println("第十代细菌 是 " + cellNumbers);
    }

}
