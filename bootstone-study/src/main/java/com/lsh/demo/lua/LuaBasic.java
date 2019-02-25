package com.lsh.demo.lua;

/**
 * Created by lsh on 2019/2/25.
 *
 * 运行方式1
 * 在redis目录，通过cmd执行
 * redis-cli --eval 123.lua key1 key2 , 123
 * 123.lua 为脚本名称，空格 跟keys ，逗号之后 空格 跟 argv
 *
 * 运行方式2
 * 运行redis-cli.exe
 * eval "return redis.call('get','hh')" 0
 * 注意点：0是keys个数
 *
 */
public class LuaBasic {

}
