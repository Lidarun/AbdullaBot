package com.lidarunium.afpf.holders.menu;

import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.holders.KeyboardHolder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartMenu implements KeyboardHolder {
    @Override
    public Command getCommandHandler() {
        return Command.START;
    }

    @Override
    public List<KeyboardRow> getKeyboardRows() {
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton income = new KeyboardButton("Finance");
        KeyboardButton expense = new KeyboardButton("Something");

        row1.add(income);
        row1.add(expense);

        rows.add(row1);

        return rows;
    }
}
