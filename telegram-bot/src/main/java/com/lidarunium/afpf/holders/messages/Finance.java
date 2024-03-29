package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Finance implements MessageHolder {
    private final MessageGenerator generator;

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
        InlineKeyboardMarkup inlineKeyboard = getMessageButtons();

        return generator.generateMessage(chatID, msg, inlineKeyboard);
    }

    private InlineKeyboardMarkup getMessageButtons() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton salary = InlineKeyboardButton.builder()
                .text("Income")
                .callbackData("Income")
                .build();
        InlineKeyboardButton other = InlineKeyboardButton.builder()
                .text("Expense")
                .callbackData("Expense")
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(salary);
        keyboardButtonsRow.add(other);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardButtonsRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
