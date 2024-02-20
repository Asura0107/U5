package com.example.u5w3d1.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class ServerConfig {
    @Bean
    public Cloudinary cloudinary(@Value("${cloudinary.name}") String name, @Value("${cloudinary.key}") String key,
                                 @Value("${cloudinary.secret}") String secret) {
        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", name);
        config.put("api_key", key);
        config.put("api_secret", secret);

        return new Cloudinary(config);
    }
}
