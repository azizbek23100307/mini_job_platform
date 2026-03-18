package org.example.mini_job_platform.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mini_job_platform.telegram.TelegramApiClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramPollingService {

    private final TelegramApiClient telegramApiClient;
    private final TelegramCommandService telegramCommandService;

    private Long offset = 0L;

    @Scheduled(fixedDelay = 3000)
    public void pollUpdates() {
        try {
            List<Map<String, Object>> updates = telegramApiClient.getUpdates(offset);

            for (Map<String, Object> update : updates) {
                Long updateId = ((Number) update.get("update_id")).longValue();
                offset = updateId + 1;

                Object messageObj = update.get("message");
                if (!(messageObj instanceof Map<?, ?> rawMessage)) {
                    continue;
                }

                Map<String, Object> message = (Map<String, Object>) rawMessage;
                Map<String, Object> chat = (Map<String, Object>) message.get("chat");

                Long chatId = ((Number) chat.get("id")).longValue();
                String text = (String) message.get("text");

                telegramCommandService.handleMessage(chatId, text);
            }
        } catch (Exception e) {
            log.error("Telegram polling error: {}", e.getMessage(), e);
        }
    }
}