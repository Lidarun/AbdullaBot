package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.MessageHolder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BotCommandHandler {
    private final Map<Command, MessageHolder> messageHolders = new HashMap<>();

    public BotCommandHandler(List<MessageHolder> holders) {
        holders.forEach(h -> messageHolders.put(h.getCommand(), h));
    }

    public SendMessage getMessageByBotState(Command command, Message message) {
        MessageHolder holder = findMessageHolderByBotState(command);
        return holder.getMessage(message);
    }

    private MessageHolder findMessageHolderByBotState(Command command) {
        return messageHolders.get(command);
    }
}
