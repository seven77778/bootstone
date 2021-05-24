package com.lsh.demo.bootstone.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * Created by lsh on 2020-01-06.
 */
@RestController
@Component
@RequestMapping("/")
@CrossOrigin("*")
public class QiQiController {


    @PostMapping("/login")
    public String login(String name, String password) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("user.txt"));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                if (StringUtils.isNotBlank(str)) {
                    System.out.println(str);
                    sb.append(str);
                }
            }
            System.out.println("sb " + sb);
            JSONArray array = JSON.parseArray(sb.toString());
            System.out.println(array);
            for (Object o : array) {
                JSONObject jsonObject = (JSONObject) o;
                System.out.println("name--" + jsonObject.getString("name"));
                if (name.equals(jsonObject.getString("name"))) {
                    if (password.equals(jsonObject.getString("password"))) {
                        System.out.println("ok");
                        return "1";
                    } else {
                        return "error";
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return "none";
    }


    @PostMapping("/regsterUser")
    public String regsterUser(String name, String password) {
        try {
            File file = new File("user.txt");
            FileOutputStream fos = null;
            if (!file.exists()) {
                file.createNewFile();
                fos = new FileOutputStream(file);//首次写入获取
            } else {
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file, true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件
            osw.write(name + "-" + password + "~~");

            osw.close();
            System.out.println("ok");
            return "1";
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "error";
    }


    @GetMapping("/getBooks")
    public String getBook() {
        System.out.println("getBooks");

        return "[\n" +
                "  {\n" +
                "    \"img\": \"img/1.jpg\",\n" +
                "    \"price\": \"11元\",\n" +
                "    \"name\": \"西游记\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"img\": \"img/2.jpg\",\n" +
                "    \"price\": \"22元\",\n" +
                "    \"name\": \"水许传\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"img\": \"img/3.jpg\",\n" +
                "    \"price\": \"33元\",\n" +
                "    \"name\": \"三国演义\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"img\": \"img/4.jpg\",\n" +
                "    \"price\": \"44元\",\n" +
                "    \"name\": \"红楼梦\"\n" +
                "  }\n" +
                "]";
    }

    public static void main(String[] args) {

        String str = "lsh-123~~qq-789";
        System.out.println(str.indexOf("qq"));

    }

}
