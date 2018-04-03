package org.wzp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("org.wzp.mapper")
@ComponentScan(basePackages = "org.wzp.*")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


