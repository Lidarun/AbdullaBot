package com.lidarunium.afpf.configuration;

import com.lidarunium.afpf.PersonalFinanceBot;
import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.cache.DeleteMessageCache;
import com.lidarunium.afpf.handlers.CallbackQueryHandler;
import com.lidarunium.afpf.handlers.MessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class SpringConfiguration {
    private final BotConfiguration botConfiguration;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder()
                .url(botConfiguration.getWebhookPath())
                .build();
    }

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setProxyPort(8080);
        options.setMaxThreads(69);
        return options;
    }

    @Bean
    public String botToken() {
        return botConfiguration.getBotToken();
    }

    @Bean
    public PersonalFinanceBot personalFinanceBot(DefaultBotOptions defaultBotOptions,
                                                 SetWebhook setWebhook,
                                                 String botToken,
                                                 MessageHandler messageHandler,
                                                 CallbackQueryHandler callbackQueryHandler,
                                                 BotStateCache botStateCache,
                                                 DeleteMessageCache deleteMessageCache) {
        PersonalFinanceBot bot = new PersonalFinanceBot(defaultBotOptions, setWebhook, botToken, messageHandler, callbackQueryHandler, botStateCache, deleteMessageCache);

        bot.setBotPath(botConfiguration.getWebhookPath());
        bot.setBotUsername(botConfiguration.getUsername());

        return bot;
    }
}