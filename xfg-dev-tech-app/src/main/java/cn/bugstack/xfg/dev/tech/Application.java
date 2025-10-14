package cn.bugstack.xfg.dev.tech;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: yangjian
 * @Date: 2025-10-13 - 10 - 13 - 16:36
 * @Description: cn.bugstack.xfg.dev.tech
 * @version: 1.0
 */
@SpringBootApplication
@Configurable
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
