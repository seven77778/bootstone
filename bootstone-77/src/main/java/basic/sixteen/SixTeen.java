package basic.sixteen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsh on 2019-08-21.
 *
 * 一种比较常见的格式 json，主要用在http协议中传递数据
 * 如下所示，json固定格式 大括号开始 ，大括号结束，中间包裹的所有数据，都带着双引号
 * 两组数据之间用 英文逗号 分割
 *
 * json最大的用途就是 传递数据，记得http post请求中，传递参数最常用的都是json
 * 访问百度啊 qq啊，登录时候填写的用户名+密码，都是通过json格式传递到他们系统的后端去验证滴
 *
 * 也是键值对，一个key对着一个value，类似于map，key必须是String，value可以是String，int（对于数字来说，没有双引号就是int，有双引号的都是String）
 *
 *
 * {
 *     "name":"xiaoqiqi",
 *     "age":"18",
 *     "sex":"女"
 * }
 *
 */
public class SixTeen {


    /**
     * 创建一个json
     */
    @Test
    public void test1(){
        //创建一个json object
        JSONObject object = new JSONObject();
        //string
        object.put("string","string");
        //int
        object.put("age",18);//18没加双引号，就是int类型，生成的json中，18也没有双引号
        //boolean
        object.put("boolean",true);
        //array
        List<Integer> integers = Arrays.asList(1,2,3);//快速创建数据的方法，类似于先new一个list，然后add 1 2 3
        object.put("list",integers);
        //null
        object.put("null",null);//放入null，等于没放
        System.out.println(object);
    }

    /**
     * 读取一个json
     */
    @Test
    public void test2(){
        // 见 同目录下的json.txt ，从txt中复制到java中，\n 是系统自动补全的符号，无需关注
        String str = "{\n" +
                "\"name\":\"xiaoliu\",\n" +
                "\"age\":\"18\"\n" +
                "}";

        //只要String字符串符合 json要求的格式，都可以通过 JSON.parseObject(str) 转换为一个 JSONObject
        JSONObject json = JSON.parseObject(str);
        String name = json.getString("name");
        System.out.println("从json中获取的name是 "+name);
    }



    /**
     * map 直接转换为json，也是比较常用的方式
     */
    @Test
    public void test3(){
        Map<String,String> map = new HashMap<>(16);
        map.put("name","777");
        map.put("date","2019-08-22");
        String str = JSON.toJSONString(map);
        System.out.println("map转换为的json 是 " + str);
    }

    /**
     * 以上是常用的json
     * 还有一种不太常用的，json数组，它是 []开头，里面不是键值对，就好像 数组，又或者list一样
     *
     */
    @Test
    public void test4(){

        //见 json.txt
        String str = "[\n" +
                "\"苹果\",\n" +
                "\"香蕉\",\n" +
                "\"橘子\"\n" +
                "]";
        JSONArray arr = JSON.parseArray(str);
        System.out.println("json转换数组的大小 " +arr.size());
        System.out.println("json转换数组内容 " +arr);


    }
}
