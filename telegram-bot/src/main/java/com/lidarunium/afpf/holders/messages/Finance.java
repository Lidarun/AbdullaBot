package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.enums.ButtonType;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MarkupService;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class Finance implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final MarkupService markupService;


    @Override
    public Command getCommand() {
        return Command.FINANCE;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        String msg = "Category: ";
        long chatID = message.getChatId();
        return messageGenerator.generateMessage(chatID, msg, getMessageButtons());
    }

    private InlineKeyboardMarkup getMessageButtons() {
        return markupService.getBiLevelMarkup(Arrays.asList(ButtonType.INCOME, ButtonType.EXPENSE), Collections.singletonList(ButtonType.BACK));
    }
}
