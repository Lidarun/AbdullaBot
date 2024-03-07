package com.lidarunium.afpf.holders.messages;

import com.lidarunium.afpf.cache.Cache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.handlers.BotKeyboardHandler;
import com.lidarunium.afpf.holders.MessageHolder;
import com.lidarunium.afpf.service.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
@RequiredArgsConstructor
public class Start implements MessageHolder {
    private final MessageGenerator generator;
    private final BotKeyboardHandler keyboardHandler;
    private final Cache cache;

    @Override
    public Command getCommand() {
        return Command.START;
    }

    @Override
    public SendMessage getMessage(Message message) {
        return generateMessage(message);
    }

    private SendMessage generateMessage(Message message) {
        long chatID = message.getChatId();

        String msg = "Greetings Mr. " + message.getChat().getFirstName() + "!";
        ReplyKeyboardMarkup replyKeyboard = keyboardHandler.getKeyboardByBotCommand(Command.START);

        cache.setBotState(chatID, null);
        return generator.generateMessage(chatID, msg, replyKeyboard);
    }
}
