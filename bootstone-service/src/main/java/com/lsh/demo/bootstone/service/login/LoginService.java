package com.lsh.demo.bootstone.service.login;

import org.springframework.stereotype.Service;

/**
 * Created by lsh on 2019-06-10.
 */
@Service
public class LoginService {

    public boolean login(String name,String pw){

        if("77".equals(name) && "123".equals(pw)){
            return true;
        }else {
            return false;
        }
    }

    public boolean marry(String age){

        int ageInt = Integer.valueOf(age);
        if(ageInt>20 && ageInt <60){
            return true;
        }else {
            return false;
        }
    }



}
