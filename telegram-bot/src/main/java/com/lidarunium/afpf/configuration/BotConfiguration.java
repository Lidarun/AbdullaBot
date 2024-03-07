package com.lidarunium.afpf.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class BotConfiguration {
    @Value("${telegram.webhook-path}")
    private String webhookPath;
    @Value("${telegram.username}")
    private String username;
    @Value("${telegram.token}")
    private String botToken;
}
