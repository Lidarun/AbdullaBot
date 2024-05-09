package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class Expense implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final BotStateCache botStateCache;

    @Override
    public Command getCommand() {
        return Command.EXPENSE;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        botStateCache.setBotState(chatID, Command.DELETE_PREVIOUS_MESSAGE);

        return messageGenerator.generateMessage(chatID, "Success!");
    }
}
