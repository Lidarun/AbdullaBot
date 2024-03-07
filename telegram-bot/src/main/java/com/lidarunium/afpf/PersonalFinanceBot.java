package com.lidarunium.afpf;

import com.lidarunium.afpf.cache.UserCache;
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
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Getter
@Setter
@Component
public class PersonalFinanceBot extends SpringWebhookBot implements ExecuteMessageService {
    private String botPath;
    private String botUsername;
    private MessageHandler messageHandler;
    private CallbackQueryHandler callbackQueryHandler;
    private final UserCache userCache;

    public PersonalFinanceBot(DefaultBotOptions options,
                              SetWebhook setWebhook,
                              String botToken,
                              MessageHandler messageHandler,
                              CallbackQueryHandler callbackQueryHandler, UserCache userCache) {
        super(options, setWebhook, botToken);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.userCache = userCache;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);

        } catch (Exception e){
            log.error(e.getMessage());
        }

        return null;
    }

    private BotApiMethod<?> handleUpdate(Update update) {
        SendMessage sendMessage = null;

        if (update.hasMessage())
            sendMessage = messageHandler.replyMessage(update.getMessage());

        if (update.hasCallbackQuery())
            sendMessage = callbackQueryHandler.replyMessage(update.getCallbackQuery());

        return sendMessage;
    }

    @Override
    public DeleteMessage executeMessage(SendMessage sendMessage) {
        try {
            Integer msgId = execute(sendMessage).getMessageId();
            return new DeleteMessage(sendMessage.getChatId(), msgId);

        } catch (TelegramApiException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessage(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);

        } catch (TelegramApiException e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
}
