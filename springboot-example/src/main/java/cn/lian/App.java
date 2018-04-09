package cn.lian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("cn.lian.mapper")
@EnableCaching
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
