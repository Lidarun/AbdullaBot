package com.lidarunium.afpf.service.impl;

import com.lidarunium.afpf.service.ButtonGenerator;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Service
public class ButtonGeneratorImpl implements ButtonGenerator {
    @Override
    public InlineKeyboardButton generateCancelButton() {
        return InlineKeyboardButton.builder()
                .text("Cancel")
                .callbackData("Cancel")
                .build();
    }

    @Override
    public InlineKeyboardButton generateBackButton() {
        return InlineKeyboardButton.builder()
                .text("Back")
                .callbackData("Back")
                .build();
    }
}
