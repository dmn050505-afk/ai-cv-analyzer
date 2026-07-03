package com.nunes.ai_cv_analyzer.integration.gemini.client;

import com.nunes.ai_cv_analyzer.config.GeminiProperties;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.GeminiRequestDTO;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.GeminiResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GeminiClient {

    private final RestClient restClient;
    private final GeminiProperties properties;

    public GeminiClient(RestClient restClient,
                        GeminiProperties properties) {

        this.restClient = restClient;
        this.properties = properties;
    }

    public GeminiResponseDTO generateContent(String prompt) {

        GeminiRequestDTO request = new GeminiRequestDTO(prompt);

        String url = properties.getApiUrl()
                + "/v1beta/models/"
                + properties.getModel()
                + ":generateContent?key="
                + properties.getApiKey();

        return restClient.post()
                .uri(java.net.URI.create(url))
                .body(request)
                .retrieve()
                .body(GeminiResponseDTO.class);
    }

}