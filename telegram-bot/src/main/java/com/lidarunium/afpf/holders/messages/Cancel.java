package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.UserCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class Cancel implements MessageHolder {
    private final MessageGenerator generator;
    private final UserCache userCache;

    @Override
    public Command getCommand() {
        return Command.CANCEL;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        userCache.setBotState(chatID, null);

        return generator.generateMessage(chatID, "Success!");
    }
}
