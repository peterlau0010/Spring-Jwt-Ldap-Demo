package com.example.demo;

//import com.example.demo.config.RequestLoggingFilterConfig;

import com.example.demo.config.YamlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private YamlConfig yamlConfig;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private static final Logger logger = LogManager.getLogger(DemoApplication.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info(yamlConfig);

    }

}
