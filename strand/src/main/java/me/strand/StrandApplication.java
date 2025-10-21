package me.strand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableScheduling
@MapperScan("me.strand.mapper")
public class StrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrandApplication.class, args);
	}

}
