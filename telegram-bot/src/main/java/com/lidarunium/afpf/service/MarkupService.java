package com.lidarunium.afpf.service;

import com.lidarunium.afpf.enums.ButtonType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public interface MarkupService {
    InlineKeyboardMarkup getSingleLevelMarkup(List<ButtonType> type);
    InlineKeyboardMarkup getBiLevelMarkup(List<ButtonType> firstLevelTypes, List<ButtonType> secondLevelTypes);
}
