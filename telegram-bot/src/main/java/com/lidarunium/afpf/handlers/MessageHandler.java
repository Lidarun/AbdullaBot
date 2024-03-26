package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.cache.Cache;
import com.lidarunium.afpf.enums.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler {
    private final BotCommandHandler context;
    private final BotStateCache botStateCache;

    public SendMessage replyMessage(Message message) {
        long chatID = message.getChatId();
        Command command = botStateCache.getBotState(chatID);

        if (Objects.isNull(command))
            command = switch (message.getText()) {
                case "/start" -> Command.START;
                case "Finance" -> Command.FINANCE;
                default -> null;
            };

        return context.getMessageByBotState(command, message);
    }
}
