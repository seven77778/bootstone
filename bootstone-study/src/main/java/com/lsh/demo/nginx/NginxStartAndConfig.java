package com.lsh.demo.nginx;

/**
 * 1.进入Nginx文件夹，在cmd中，执行 Nginx.exe,执行完了页面锁定
 * -- 使用 start nginx,窗口还能操作其他命令
 * 2.http://localhost:80,浏览器中访问成功的话，代表成功打开
 * 3.cmd中查看任务 tasklist /fi "imagename eq nginx.exe"
 * -- 修改了conf，Nginx.exe -s reload 重启
 * 4.根据pid关闭 ，tskill pid
 * 5.分流规则，默认是轮询，
 * server 127.0.0.1:8088 weight=8 down;  配置了down不会访问
 * backup 其它服务器时或down时才会请求backup服务器
 * max_fails 失败超过指定次数会暂停或请求转往其它服务器
 * fail_timeout 失败超过指定次数后暂停时间
 *
 * 6.正向代理代理客户端，反向代理代理服务器
 * 7.Nginx 做反向代理 --隐藏真实主机 、解决跨越
 */
public class  NginxStartAndConfig {
    /**
     * 关于反向代理 -- 用户有感知的访问叫正向代理如使用vpn访问youtube，用户无感知访问叫反向代理如负载均衡
     * 对于后端是动态服务来说，比如Java和PHP。这类服务器（如JBoss和PHP-FPM）的IO处理能力往往不高。
     * Nginx有个好处是它会把Request在读取完整之前buffer住，这样交给后端的就是一个完整的HTTP请求，
     * 从而提高后端的效率，而不是断断续续的传递（互联网上连接速度一般比较慢）。同样，Nginx也可以把response给buffer住，同样也是减轻后端的压力。
     *
     补充下 对很多高并发的机器来说, CPU, 内存, 带宽都不是瓶颈, 瓶颈是网络IO次数. 用比较通俗的话来比喻就是,
     快递一次给你送十个包裹, 你完全无压力,
     但是如果他一天分十次给你送十个包裹, 要十次interrupt你, 你就崩溃了. 反向代理起的作用就是当你的收发室, 收集了十个包裹再一次发给你.

     但是如果这样利用反向代理服务器, 反向代理服务器就必须部署在另外一台物理机器上, 否则就是你自己兼职做自己的收发室了, 毫无意义.

     这种做法很奢侈, 适合于财大气粗的土豪公司.

     如果可以的话, 还是换一个多队列网卡硬件, 或者用软件虚拟的RPS. 我曾经用过RPS, 效果确实很明显, 软中断很均匀的分布在机器的多个CPU核上,
     吞吐能力提升显著.
     */


    /*
     -- 分流
     worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

	upstream good {
		server 127.0.0.1:8088 weight=8;
		server 127.0.0.1:8086 weight=1;
		keepalive 2000;
	}


    server {
        listen       80;
        server_name  127.33.22.1;

        location / {
            proxy_pass   http://good;
			proxy_set_header Host $host:$server_port;
        }
    }


}
     */

    /* --可以完成从 127.33.22.1:80/hello/hello 到 localhost:8088
      worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
	upstream my_server {
		server 127.0.0.1:8088;
		keepalive 2000;
	}

    server {
        listen       80;
        server_name  127.33.22.1;

        location / {
            root   html;
            index  index.html index.htm;
        }

        location /hello/ {
            proxy_pass   http://my_server;
			proxy_set_header Host $host:$server_port;
        }

    }
}
     */


    /* -- 两个地址跳转到同一个
    worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
	upstream my_server {
		server 127.0.0.1:8088;
		keepalive 2000;
	}

    server {
        listen       80;
        server_name  127.33.22.1;

        location / {
            proxy_pass   http://my_server;
			proxy_set_header Host $host:$server_port;
        }
    }

	server {
        listen       80;
        server_name  127.44.55.1;

        location / {
            proxy_pass   http://my_server;
			proxy_set_header Host $host:$server_port;
        }
      }
    }
     */


    /* -- 多个地址跳转多个
    worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

	upstream good {
		server 127.0.0.1:8088;
		keepalive 2000;
	}

	upstream nice {
		server 127.0.0.1:8086;
		keepalive 2000;
	}

    server {
        listen       80;
        server_name  127.33.22.1;

        location / {
            proxy_pass   http://good;
			proxy_set_header Host $host:$server_port;
        }
    }

	server {
        listen       80;
        server_name  127.77.88.1;

        location / {
            proxy_pass   http://good;
			proxy_set_header Host $host:$server_port;
        }
    }

	server {
        listen       80;
        server_name  127.77.88.1;

        location / {
            proxy_pass   http://nice;
			proxy_set_header Host $host:$server_port;
        }
    }
}
     */


    /* 访问127.0.0.1  直接跳转163

      server {
        listen       80;
        server_name  127.0.0.1;

       location / {

		rewrite .+ http://www.163.com/ permanent;
	}
    }

     */
}
