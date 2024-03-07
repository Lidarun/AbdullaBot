package com.lidarunium.afpf;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

public interface ExecuteMessageService {
    DeleteMessage executeMessage(SendMessage sendMessage);
    void deleteMessage(DeleteMessage deleteMessage);
}
