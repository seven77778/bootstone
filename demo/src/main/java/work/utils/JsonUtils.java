package work.utils;

import org.junit.Test;

/**
 * Created by lsh on 2019-08-20.
 */
public class JsonUtils {


    /**
     * http返回的json，带有各种特殊符号，去除特殊符号
     * @param str
     * @return
     */
    public String getJson(String str){
        return str.replaceAll("[^a-zA-Z0-9\\{\\}\\:\"\\.,<>\\[\\]]","");
    }


    /**
     * 替换各种空白字符
     */
    public String replaceSpace(String str){
        return str.replaceAll("\\s*", "");
    }



    //****************************************************

    @Test
    public void test2(){
        String str = "123  234      ";
        System.out.println(replaceSpace(str));
    }


    @Test
    public void test(){
        String str = "{\n" +
                "    \"IsFinished\": true,\n" +
                "    \"Outcome\": {\n" +
                "        \"Rom\": \"1212\",\n" +
                "     \n" +
                "        \"LastReject\": false,\n" +
                "        \"CapturedLockers\": [],\n" +
                "      \n" +
                "        \"WiegandCode\": false\n" +
                "    },\n" +
                "    \"Error\": false\n" +
                "}";

        System.out.println(getJson(str));
    }

}
