package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.handlers.BotCommandHandler;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Back implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final BotStateCache cache;

    @Autowired @Lazy
    private BotCommandHandler context;

    @Override
    public Command getCommand() {
        return Command.BACK;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();

        Command command = cache.getPreviousBotState(chatID);
        if (Objects.nonNull(command)) {
            cache.setBotState(chatID, null);
            return context.getMessageByBotState(command, message);
        }
        return messageGenerator.generateMessage(chatID, "TODO: Something went wrong BACK button");
    }
}
