package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.cache.Cache;
import com.lidarunium.afpf.enums.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final BotCommandHandler commandHandler;
    private final BotStateCache botStateCache;

    public SendMessage replyMessage(CallbackQuery callbackQuery) {
        String query = callbackQuery.getData();
        long chatID = callbackQuery.getMessage().getChatId();
        Command command = botStateCache.getBotState(chatID);

        if (Objects.isNull(command))
            command = switch (query) {
                case "Income" -> Command.INCOME;
                case "Expense" -> Command.EXPENSE;
                case "Cancel" -> Command.CANCEL;
                case "Salary", "Edit" -> Command.SALARY;
                case "Save" -> Command.SAVE_SALARY;

                default -> null;
            };

        else {
            if (Objects.equals(command, Command.SALARY))
                command = switch (query) {
                    case "Cancel" -> Command.CANCEL;
                    case "Back" -> Command.BACK;

                    default -> null;
                };
        }

        return commandHandler.getMessageByBotState(command, callbackQuery.getMessage());
    }
}
