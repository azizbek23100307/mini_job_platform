package org.example.mini_job_platform.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.config.TelegramBotProperties;
import org.example.mini_job_platform.telegram.TelegramApiClient;
import org.example.mini_job_platform.telegram.TelegramNotifierService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramNotifierServiceImp
 implements TelegramNotifierService {
    private final TelegramApiClient telegramApiClient;
    private final TelegramBotProperties telegramBotProperties;

    @Override
    public void notifyNewApplication(String message) {
        telegramApiClient.sendMessage(telegramBotProperties.getAdminChatId(), message);
    }

    @Override
    public void sendApplicationFile(String filePath, String caption) {
        telegramApiClient.sendDocument(telegramBotProperties.getAdminChatId(), filePath, caption);
    }
}
