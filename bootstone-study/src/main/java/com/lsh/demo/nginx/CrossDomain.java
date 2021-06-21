package com.lsh.demo.nginx;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomain {

    //火狐成功了，chrome因 CSRF 还不行

    /**
     *
     server {
     listen       9091;
     server_name  127.0.0.1;
     location /scm {
     proxy_pass http://127.0.0.1:5000/scm;
     proxy_cache_methods POST;
     }
     }
     */
    /*
    html：
       $(document).ready(function(){
            // 点击按钮 请求数据，
            $("#b02").click(function(){
                // 请求数据
                htmlobj=$.ajax({url:"http://www.bbb2.com/scm/noCross",
                    xhrFields: {
                           withCredentials: true
                         },
                    crossDomain: true,
                    async:false});
                // 将请求的数据显示在div中
                $("#myDiv").html(htmlobj.responseText);
            });
        });

     */

    /*
    版本 83.0.4103.97（正式版本） （64 位）
    这一套配置在 以上版本的chrome中使用

    需要在 chrome://flags/ 关闭 samesite cookie 等几个 disable
     */
    @RequestMapping("/noCross")
    public String noCross(String lsh, HttpServletRequest request, HttpServletResponse response){
        Cookie[] getC = request.getCookies();
        System.out.println("拿到前端cookie-" + (getC==null));
        Cookie cookie = new Cookie("no-cross-123-cookie-key","123-cookie-value");
        response.addCookie(cookie);
        String url = request.getHeader("Origin");
        response.addHeader("Access-Control-Allow-Origin", url);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        return "没加跨域的注解";
    }


    /*
    最终成功的配置：

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
   server {
        listen       9091;
        server_name  127.0.0.1;
		location /scm {
           proxy_pass http://127.0.0.1:5000/scm;
           proxy_cache_methods POST;
        }
    }
}
     */

}
