package org.example.mini_job_platform.telegram;

public interface TelegramNotifierService {
    void notifyNewApplication(String message);
    void sendApplicationFile(String filePath, String caption);
}
