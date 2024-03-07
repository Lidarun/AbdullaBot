package com.lidarunium.afpf.handlers;

import com.lidarunium.afpf.cache.UserCache;
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
    private final UserCache cache;

    public SendMessage replyMessage(CallbackQuery callbackQuery) {
        String query = callbackQuery.getData();
        long chatID = callbackQuery.getMessage().getChatId();
        Command command = cache.getBotState(chatID);

        if (Objects.isNull(command))
            command = switch (query) {
                case "Income" -> Command.INCOME;
                case "Expense" -> Command.EXPENSE;
                //Incomes
                case "Salary" -> Command.SALARY;

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
