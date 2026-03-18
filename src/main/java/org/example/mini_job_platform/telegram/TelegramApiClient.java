package org.example.mini_job_platform.telegram;


import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.config.TelegramBotProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TelegramApiClient {

    private final TelegramBotProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(Long chatId, String text) {
        String url = "https://api.telegram.org/bot" + properties.getToken() + "/sendMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "chat_id", chatId,
                "text", text
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public void sendDocument(Long chatId, String filePath, String caption) {
        String url = "https://api.telegram.org/bot" + properties.getToken() + "/sendDocument";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId);
        body.add("caption", caption);
        body.add("document", new FileSystemResource(new File(filePath)));

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(url, entity, String.class);
    }

    public List<Map<String, Object>> getUpdates(Long offset) {
        String url = "https://api.telegram.org/bot" + properties.getToken() + "/getUpdates";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "offset", offset,
                "timeout", 0
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        Object result = response.getBody().get("result");
        if (result instanceof List<?> list) {
            return (List<Map<String, Object>>) list;
        }

        return Collections.emptyList();
    }
}
