package com.lidarunium.afpf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ButtonType {
    BACK("Back"),
    SAVE("Save"),
    EDIT("Edit"),
    CANCEL("Cancel"),
    INCOME("Income"),
    EXPENSE("Expense");

    private final String value;
}
