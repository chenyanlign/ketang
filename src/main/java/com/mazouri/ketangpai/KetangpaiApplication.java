package com.mazouri.ketangpai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mazouri
 */
@SpringBootApplication
@MapperScan("com.mazouri.ketangpai.mapper")
@ComponentScan(basePackages = {"com.mazouri"})
public class KetangpaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KetangpaiApplication.class, args);
    }

}
