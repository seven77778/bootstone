package work.utils;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;

import java.io.InputStream;

public class ResourecPathUtil {

    @Test
    public void testStaticPath(){
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String filePath = path + "static/testabc.txt";
        System.out.println(filePath);
    }

    /**
     * class path resource [static/testabc.txt] cannot be opened because it does not exist
     * @throws Exception
     */
    @Test
    public void test()throws Exception{
        ClassPathResource classPathResource = new ClassPathResource("/static/testabc.txt");
        InputStream inputStream = classPathResource.getInputStream();

    }
}
