package com.lidarunium.afpf.cache;

import com.lidarunium.afpf.enums.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cache implements BotStateCache, DeleteMessageCache {
    private Map<Long, Command> statesCache = new HashMap<>();
    private Map<Long, Command> previousStatesCache = new HashMap<>();
    private Map<Long, DeleteMessage> deleteMessagesCache = new HashMap<>();

    @Override
    public void setBotState(long chatID, Command command) {
        statesCache.put(chatID, command);
        previousStatesCache.put(chatID, command);
    }

    @Override
    public Command getBotState(long chatID) {
        return statesCache.get(chatID);
    }

    @Override
    public Command getPreviousBotState(long chatID) {
        return previousStatesCache.get(chatID);
    }

    @Override
    public void setDeleteMessage(long chatID, DeleteMessage deleteMessage) {
        deleteMessagesCache.put(chatID, deleteMessage);
    }

    @Override
    public DeleteMessage getDeleteMessage(long chatID) {
        return deleteMessagesCache.get(chatID);
    }
}
