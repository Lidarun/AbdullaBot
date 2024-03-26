package com.lidarunium.afpf;

import com.lidarunium.afpf.cache.BotStateCache;
import com.lidarunium.afpf.cache.DeleteMessageCache;
import com.lidarunium.afpf.enums.Command;
import com.lidarunium.afpf.handlers.CallbackQueryHandler;
import com.lidarunium.afpf.handlers.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.util.Objects;

@Slf4j
@Getter
@Setter
@Component
public class PersonalFinanceBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private MessageHandler messageHandler;
    private CallbackQueryHandler callbackQueryHandler;
    private final BotStateCache botStateCache;
    private final DeleteMessageCache deleteMessageCache;

    public PersonalFinanceBot(DefaultBotOptions options,
                              SetWebhook setWebhook,
                              String botToken,
                              MessageHandler messageHandler,
                              CallbackQueryHandler callbackQueryHandler,
                              BotStateCache botStateCache,
                              DeleteMessageCache deleteMessageCache) {
        super(options, setWebhook, botToken);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.botStateCache = botStateCache;
        this.deleteMessageCache = deleteMessageCache;
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handleUpdate(update);
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        SendMessage sendMessage = null;

        if (update.hasMessage())
            sendMessage = messageHandler.replyMessage(update.getMessage());

        if (update.hasCallbackQuery())
            sendMessage = callbackQueryHandler.replyMessage(update.getCallbackQuery());

        assert sendMessage != null;
        Command command = botStateCache.getBotState(Long.parseLong(sendMessage.getChatId()));
        if (Objects.equals(command, Command.DELETE_PREVIOUS_MESSAGE) || Objects.equals(command, Command.SALARY)) {
            deleteMessage(deleteMessageCache.getDeleteMessage(Long.parseLong(sendMessage.getChatId())));

            if (Objects.equals(command, Command.DELETE_PREVIOUS_MESSAGE))
                botStateCache.setBotState(Long.parseLong(sendMessage.getChatId()), null);
        }

        DeleteMessage deleteMessage = executeMessage(sendMessage);
        deleteMessageCache.setDeleteMessage(Long.parseLong(deleteMessage.getChatId()), deleteMessage);
        return null;
    }

    private DeleteMessage executeMessage(SendMessage sendMessage) {
        try {
            Integer msgId = execute(sendMessage).getMessageId();
            return new DeleteMessage(sendMessage.getChatId(), msgId);

        } catch (TelegramApiException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void deleteMessage(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);

        } catch (TelegramApiException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
