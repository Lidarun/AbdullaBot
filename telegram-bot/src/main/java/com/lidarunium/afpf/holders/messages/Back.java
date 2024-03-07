package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class Back implements MessageHolder {
    private final MessageGenerator generator;

    @Override
    public Command getCommand() {
        return Command.BACK;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        String msg = "TODO return previous buttons";
        long chatID = message.getChatId();

        return generator.generateMessage(chatID, msg);
    }
}
