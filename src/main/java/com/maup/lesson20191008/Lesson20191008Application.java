package com.maup.lesson20191008;

import com.maup.lesson20191008.cofig.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Lesson20191008Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson20191008Application.class, args);
    }

}
