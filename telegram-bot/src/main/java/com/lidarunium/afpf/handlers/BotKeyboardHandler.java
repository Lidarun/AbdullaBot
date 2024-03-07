package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.KeyboardHolder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BotKeyboardHandler {
    private final Map<Command, KeyboardHolder> botCommandKeyboards = new HashMap<>();

    public BotKeyboardHandler(List<KeyboardHolder> messageHolder) {
        messageHolder.forEach(holder -> botCommandKeyboards
                .put(holder.getCommandHandler(), holder));
    }

    public ReplyKeyboardMarkup getKeyboardByBotCommand(Command command) {
        KeyboardHolder holder = findKeyboardHandlerByBotState(command);
        return holder.getMenuKeyboard();
    }

    private KeyboardHolder findKeyboardHandlerByBotState(Command command) {
        return botCommandKeyboards.get(command);
    }
}