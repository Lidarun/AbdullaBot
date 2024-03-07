package com.lidarunium.afpf.holders;

import com.lidarunium.afpf.enums.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHolder {
    Command getCommand();
    SendMessage getMessage(Message message);
}