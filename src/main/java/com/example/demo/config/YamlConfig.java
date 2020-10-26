package com.example.demo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.StringJoiner;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "environments")
public class YamlConfig {

    private String env;

    private String example;
    public String getExample() {
        return example;
    }
    public void setExample(String example) {
        this.example = example;
    }
    public String getEnv() {
        return env;
    }
    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", YamlConfig.class.getSimpleName() + "[", "]")
                .add("env='" + env + "'").add("example='" + example + "'").toString();
    }

}
