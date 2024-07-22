package org.example.githubclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GithubclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubclientApplication.class, args);
    }

}
