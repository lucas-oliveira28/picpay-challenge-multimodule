package io.github.lucasoliveira28;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "io.github.lucasoliveira28")
@EnableFeignClients(basePackages = "io.github.lucasoliveira28")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
