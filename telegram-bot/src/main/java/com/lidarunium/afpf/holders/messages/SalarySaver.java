package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class SalarySaver implements MessageHolder {
    private final MessageGenerator messageGenerator;

    @Override
    public Command getCommand() {
        return Command.SAVE_SALARY;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        String userMsg = message.getText();

        String msg = String.format("Your salary with size %s was saved", userMsg);

        return messageGenerator.generateMessage(chatID, msg);
    }
}
