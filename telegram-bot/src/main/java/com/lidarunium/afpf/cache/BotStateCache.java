package com.lidarunium.afpf.cache;

import com.lidarunium.afpf.enums.Command;

public interface BotStateCache {
    void setBotState(long chatID, Command command);
    Command getBotState(long chatID);
    Command getPreviousBotState(long chatID);
}
