package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.BotStateCache;
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
public class Income implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final BotStateCache botStateCache;

    @Override
    public Command getCommand() {
        return Command.INCOME;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();
        String msg = "Income: ";
        InlineKeyboardMarkup inlineKeyboard = getMessageButtons();

        botStateCache.setBotState(chatID, Command.DELETE_PREVIOUS_MESSAGE);
        return messageGenerator.generateMessage(chatID, msg, inlineKeyboard);
    }

    private InlineKeyboardMarkup getMessageButtons() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton salary = InlineKeyboardButton.builder()
                .text("Salary")
                .callbackData("Salary")
                .build();
        InlineKeyboardButton other = InlineKeyboardButton.builder()
                .text("Other")
                .callbackData("Other")
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
