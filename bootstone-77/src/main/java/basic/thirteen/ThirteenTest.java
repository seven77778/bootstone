package basic.thirteen;

import javax.swing.*;

/**
 * Created by lsh on 2019-08-08.
 */
public class ThirteenTest {

    public static void main(String[] args) {
        /**
         *下面是一个基本的框架，运行已经可以显示框框了
         * 发挥想象，随意添加组件吧
         */

        // 创建JFrame
        JFrame frame = new JFrame("77的第一个JFrame");
        // 显示JFrame
        frame.setVisible(true);

        // 设置尺寸
        frame.setSize(900, 500);
        // JFrame在屏幕居中
        frame.setLocationRelativeTo(null);

        // 自适应屏幕分辨率,有了这一行代码，如果框框里面没东西，框框就会特别小，添加组件以后就大了
        frame.pack();
        //窗口关闭，程序也跟着关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //please go on~
    }

}
