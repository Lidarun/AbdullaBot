package com.lidarunium.afpf.cache;

import com.lidarunium.afpf.enums.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserCache implements Cache {
    private Map<Long, Command> statesCache = new HashMap<>();
    private Map<Long, DeleteMessage> deleteMessagesCache = new HashMap<>();

    @Override
    public void setBotState(long chatID, Command command) {
        statesCache.put(chatID, command);
    }

    @Override
    public Command getBotState(long chatID) {
        return statesCache.get(chatID);
    }

    @Override
    public void setDeleteMessage(long userID, DeleteMessage deleteMessage) {
        deleteMessagesCache.put(userID, deleteMessage);
    }

    @Override
    public DeleteMessage getDeleteMessage(long chatID) {
        return deleteMessagesCache.get(chatID);
    }
}
