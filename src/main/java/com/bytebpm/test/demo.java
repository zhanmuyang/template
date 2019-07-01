package com.bytebpm.test;

import com.bytebpm.mybatis.entity.TUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class demo {
    @RequestMapping("/test")
    public TUser test(){
        TUser tUser = new TUser();
        tUser.setId("testId");
        tUser.setPassword("password");
        tUser.setUpdateTime(new Date());
        tUser.setMail("Zl8324@163.com");
        return tUser;
    }
}
