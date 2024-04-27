package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.ButtonGenerator;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class Salary implements MessageHolder {
    private final MessageGenerator messageGenerator;
    private final ButtonGenerator buttonGenerator;
    private final BotStateCache botStateCache;

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
        Command command = botStateCache.getBotState(chatID);
        String userMsg = message.getText();
        SendMessage sendMessage = null;
        String msg = null;

        if (Objects.isNull(command)) {
            msg = "How much?";
            sendMessage = messageGenerator.generateMessage(chatID, msg);
            command = Command.SALARY;

        } else {
            boolean containDigits = userMsg.matches(".*\\d.*");
            if (containDigits) {
                msg = "Salary: " + getDigits(userMsg);
                sendMessage = messageGenerator.generateMessage(chatID, msg);
                command = null;

            } else {
                msg = "Send me the real numbers " +
                        "or click ↓";
                sendMessage = messageGenerator.generateMessage(chatID, msg, getMessageButtons());
                command = Command.SALARY;
            }
        }

        botStateCache.setBotState(chatID, command);
        return sendMessage;
    }

    private long getDigits(String msg) {
        String digits = msg.replaceAll("\\D", "");
        return StringUtils.isBlank(digits) ? 0 : Long.parseLong(digits);
    }

    private InlineKeyboardMarkup getMessageButtons() {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(buttonGenerator.generateCancelButton());
        keyboardButtonsRow.add(buttonGenerator.generateBackButton());

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(keyboardButtonsRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
