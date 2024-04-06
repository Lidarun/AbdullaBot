package com.lidarunium.afpf.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface ButtonGenerator {
    InlineKeyboardButton generateCancelButton();
    InlineKeyboardButton generateBackButton();

}
