package com.lidarunium.afpf.cache;


import com.lidarunium.afpf.enums.Command;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public interface Cache {
    void setBotState(long chatID, Command command);
    Command getBotState(long chatID);
    void setDeleteMessage(long chatID, DeleteMessage deleteMessage);
    DeleteMessage getDeleteMessage(long chatID);
}
