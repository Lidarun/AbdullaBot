package com.lidarunium.afpf.service.impl;

import com.lidarunium.afpf.enums.ButtonType;
import com.lidarunium.afpf.service.MarkupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkupServiceImpl implements MarkupService {
    @Override
    public InlineKeyboardMarkup getSingleLevelMarkup(List<ButtonType> list) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();

        list.forEach(t -> {
            buttonsRow.add(generateButton(t));
        });

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(buttonsRow);
        markup.setKeyboard(keyboard);

        return markup;
    }

    @Override
    public InlineKeyboardMarkup getBiLevelMarkup(List<ButtonType> firstRowTypes, List<ButtonType> secondRowTypes) {
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        firstRowTypes.forEach(t -> {
            firstRow.add(generateButton(t));
        });

        secondRowTypes.forEach(t -> {
            secondRow.add(generateButton(t));
        });

        keyboard.add(firstRow);
        keyboard.add(secondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private InlineKeyboardButton generateButton(ButtonType type) {
        return InlineKeyboardButton.builder()
                .text(type.getValue())
                .callbackData(type.getValue())
                .build();
    }
}
