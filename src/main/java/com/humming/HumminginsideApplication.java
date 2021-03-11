package com.humming;

import com.humming.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class HumminginsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumminginsideApplication.class, args);
    }

}
