package com.lidarunium.afpf.cache;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public interface DeleteMessageCache {
    void setDeleteMessage(long chatID, DeleteMessage deleteMessage);
    DeleteMessage getDeleteMessage(long chatID);
}
