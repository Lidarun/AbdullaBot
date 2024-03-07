package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.UserCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Salary implements MessageHolder {
    private final MessageGenerator generator;
    private final UserCache cache;

    @Override
    public Command getCommand() {
        return Command.SALARY;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        Command command = cache.getBotState(chatID);
        String msg = null;

        if (Objects.isNull(command)) {
            msg = "How much?";
            command = Command.SALARY;

        } else {
            msg = "Salary: " + message.getText();
            command = null;
        }

        cache.setBotState(chatID, command);

        return generator.generateMessage(chatID, msg);
    }
}
