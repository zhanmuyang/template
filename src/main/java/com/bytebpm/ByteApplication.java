package com.bytebpm;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 项目启动类，直接运行main方法就行
 */
@Slf4j
@SpringBootApplication //核心注解
@MapperScan("com.bytebpm") //扫面mybatis包
public class ByteApplication {
    public static void main(String[] args) {
        log.info("准备启动");
        SpringApplication.run(ByteApplication.class, args);
        log.info("启动成功！");
    }
}
