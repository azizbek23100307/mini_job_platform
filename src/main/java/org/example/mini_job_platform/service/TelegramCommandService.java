package org.example.mini_job_platform.service;


import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.entity.Application;
import org.example.mini_job_platform.telegram.TelegramApiClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramCommandService {

    private final ApplicationQueryService applicationQueryService;
    private final TelegramApiClient telegramApiClient;

    public void handleMessage(Long chatId, String text) {
        if (text == null || text.isBlank()) {
            telegramApiClient.sendMessage(chatId, "Buyruq yubor.");
            return;
        }

        switch (text.trim()) {
            case "/start" -> telegramApiClient.sendMessage(
                    chatId,
                    "Assalomu alaykum.\n/applications - barcha arizalarni ko'rish\nChat ID: " + chatId
            );

            case "/applications" -> sendApplications(chatId);

            default -> telegramApiClient.sendMessage(
                    chatId,
                    "Noma'lum buyruq.\n/start yoki /applications yubor."
            );
        }
    }

    private void sendApplications(Long chatId) {
        List<Application> applications = applicationQueryService.getAllApplications();

        if (applications.isEmpty()) {
            telegramApiClient.sendMessage(chatId, "Hozircha arizalar yo'q.");
            return;
        }

        StringBuilder sb = new StringBuilder("Arizalar ro'yxati:\n\n");

        for (Application application : applications) {
            sb.append("ID: ").append(application.getId()).append("\n")
                    .append("Ism: ").append(application.getFullName()).append("\n")
                    .append("Telefon: ").append(application.getPhone()).append("\n")
                    .append("Email: ").append(application.getEmail()).append("\n")
                    .append("Lavozim: ").append(application.getPosition()).append("\n")
                    .append("Xabar: ").append(application.getMessage() != null ? application.getMessage() : "-").append("\n")
                    .append("CV: ").append(application.getCvFileName() != null ? application.getCvFileName() : "Yo'q").append("\n")
                    .append("-----------------------\n");
        }

        telegramApiClient.sendMessage(chatId, sb.toString());
    }
}