package basic.thirteen;


import javax.swing.*;

/**
 * Created by lsh on 2019-08-07.
 *
 * 小七七，今天是娱乐课程
 * 几乎所有的java初学者都会学习 JFrame
 * 但是工作中，完全用不到，因为界面太丑，emmm
 * 不过也可以写出来很多好玩的东西，随便看看
 *
 */
public class Thirteen extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("77的第一个JFrame");
        // 显示JFrame
        frame.setVisible(true);
        //窗口关闭，程序也跟着关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 设置尺寸
        frame.setSize(900, 500);
        // JFrame在屏幕居中
        frame.setLocationRelativeTo(null);

        // 面板。所有的组件都在面板中
        JPanel p1 = new JPanel();

        // 创建按钮
        JButton button = new JButton("77");

        // 向frame中添加一个按钮
        p1.add(button);

        /**
         * 大概总结一下哈，JFrame 是主题框框，JButton是按钮，new 就是创建一个按钮，然后 jframe.add 就是把这个组件添加进来
         * 下面的其他组件也是类似
         */
        // 文本域
        JTextField field = new JTextField(8);

        p1.add(field);

        // 标签
        JLabel passwd = new JLabel("密码");

        p1.add(passwd);

        // 密码域
        JPasswordField pass = new JPasswordField(8);

        p1.add(pass);

        // 单选按钮
        JLabel gender = new JLabel("性别");

        p1.add(gender);

        JRadioButton male = new JRadioButton("男");

        JRadioButton female = new JRadioButton("女");

        // 单选按钮组,同一个单选按钮组的互斥.
        ButtonGroup group = new ButtonGroup();

        group.add(male);

        group.add(female);

        // 注意,单选按钮组不能添加进容器
        p1.add(male);

        p1.add(female);

        // 复选框
        JLabel like = new JLabel("爱好:");

        p1.add(like);

        JCheckBox eat = new JCheckBox("吃饭");

        JCheckBox movie = new JCheckBox("看电影");

        JCheckBox sleep = new JCheckBox("睡觉");

        p1.add(eat);

        p1.add(movie);

        p1.add(sleep);

        // 文本域
        JLabel info = new JLabel("个人简介");

        p1.add(info);

        JTextArea area = new JTextArea(20, 20);

        p1.add(area);

        // 列表
        String[] data = {"one", "two", "three"};

        JList list = new JList(data);

        p1.add(list);

        /**
         * 最容易忘记的一步
         * 全部组件都在p1中，别忘了把p1放进去哈
         */
        frame.add(p1);
        // 自适应屏幕分辨率
        frame.pack();

    }
}
