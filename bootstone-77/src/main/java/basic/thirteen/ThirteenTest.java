package basic.thirteen;

import java.awt.*;
import java.awt.color.*;

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
        frame.getContentPane().setBackground(Color.BLUE);


        // 自适应屏幕分辨率,有了这一行代码，如果框框里面没东西，框框就会特别小，添加组件以后就大了
        frame.pack();
        //窗口关闭，程序也跟着关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //please go on~

        JPanel p2 = new JPanel();
        JButton button = new JButton("李苏恒");
        p2.add(button);
        JTextField field = new JTextField(8);
        p2.add(field);
        JLabel passwd = new JLabel("密码");
        p2.add(passwd);
        JPasswordField pass = new JPasswordField(8);
        p2.add(pass);
        JLabel gender = new JLabel("性别");
        p2.add(gender);
        JRadioButton male = new JRadioButton("男");
        JRadioButton female = new JRadioButton("女");
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);
        p2.add(male);
        p2.add(female);
        JLabel like = new JLabel("爱好:");
        JCheckBox eat = new JCheckBox("吃饭");
        JCheckBox movie = new JCheckBox("看电影");
        JCheckBox sleep = new JCheckBox("睡觉");
        p2.add(eat);
        p2.add(movie);
        p2.add(sleep);
        JLabel info = new JLabel("个人简介");
        p2.add(info);
        JTextArea area = new JTextArea(20, 20);
        p2.add(area);
        frame.add(p2);
        frame.pack();













    }

}
