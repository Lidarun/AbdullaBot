package com.lidarunium.afpf.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Service
public class MessageGenerator {
    public SendMessage generateMessage(long chatID, String textMessage) {
        return SendMessage.builder()
                .chatId(chatID)
                .text(textMessage)
                .build();
    }

    public SendMessage generateMessage(long chatID,
                                       String textMessage,
                                       ReplyKeyboard keyboard) {
        return SendMessage.builder()
                .chatId(chatID)
                .text(textMessage)
                .replyMarkup(keyboard)
                .build();
    }
}
