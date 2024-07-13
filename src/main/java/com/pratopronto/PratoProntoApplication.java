package com.pratopronto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
@EnableJpaRepositories()
public class PratoProntoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PratoProntoApplication.class, args);
    }

}
