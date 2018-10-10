package com.blog.erueka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BlogEruekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogEruekaServerApplication.class, args);
	}
}
