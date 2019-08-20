package basic.fifteen;

import org.junit.Test;

import java.io.*;

/**
 * Created by lsh on 2019-08-20.
 * <p>
 * 创建文件
 */
public class FifTeen {


    /**
     * 单独创建一个文件，如果有同名文件，则创建失败
     * 当然这是一个空文件
     * 媳妇儿啊，我想了下，mac和windows路径问题，下面的代码看看就好了
     *
     * 嘿嘿，我又修改了下，直接运行，在bootstone-77 根目录下找找看，有没有生成文件夹
     *
     */
    @Test
    public void test() {
        try {
            //创建文件
            File file = new File("77test.txt");//指定路径，C:/ 这种被称为 绝对路径
            if (file.createNewFile()) { //file.createNewFile() 返回值是布尔类型，代表了 是否创建成功
                System.out.println("文件创建成功！");
            } else {
                System.out.println("出错了，该文件已经存在。");
            }

            //创建文件夹：
            File file2 = new File("77/新建文件夹");
//            System.out.println(file2.mkdir());//如果没有父路径，不会报错，但不会创建文件夹
            if(file2.mkdirs()){//如果父路径不存在，会自动先创建路径所需的文件夹
                System.out.println("文件夹创建成功");
            }else {
                System.out.println("文件夹创建失败");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 请在mac中运行
     * 试着往mac中创建个文件哈，mac的根目录是 /user/
     */
    @Test
    public void test2(){
        try {
            //创建文件
            File file = new File("/user/77test.txt");//指定路径，C:/ 这种被称为 绝对路径
            if (file.createNewFile()) { //file.createNewFile() 返回值是布尔类型，代表了 是否创建成功
                System.out.println("文件创建成功！");
            } else {
                System.out.println("出错了，该文件已经存在。");
            }



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * 跑不通也没事,继续看，看到认识就行
     * 直接运行，然后在 bootstone-77 根目录找找看
     */
    @Test
    public void test3(){

        try {
            File writeName = new File("77写文件.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            PrintWriter out= new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(new FileOutputStream(writeName ),"utf-8")));
            String message="我是小七七，我会写入文件数据啦 \r\n 我是大菜鸡炒鸡无敌菜我是水货我玩云顶特别的菜而且" +
                    "系统还针对我辣鸡都是辣鸡哈哈哈 ";//需要写入的信息
            out.write(message);
            out.flush();
            out.close();
            System.out.println("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 有写入就有读取
     * 这些基本上都是固定用法，可以直接修改 77写文件.txt 中的内容，在读取
     */
    @Test
    public void test4()throws Exception{
        String str="77写文件.txt";
        File file =new File(str);
        String message="";//获取文件内容
        if(file.isFile() && file.exists()) {
            BufferedReader br=new BufferedReader(new InputStreamReader(new
                    FileInputStream(file),"utf-8"));
            String buff;
            while ((buff=br.readLine()) != null) {
                message=message+buff+"\n";
            }
            br.close();
            System.out.println(message);
        }
    }


}
