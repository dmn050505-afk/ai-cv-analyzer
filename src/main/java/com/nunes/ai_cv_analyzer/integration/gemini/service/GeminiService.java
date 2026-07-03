package com.nunes.ai_cv_analyzer.integration.gemini.service;

import com.nunes.ai_cv_analyzer.integration.gemini.client.GeminiClient;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.CVAnalysisDTO;
import com.nunes.ai_cv_analyzer.integration.gemini.dto.GeminiResponseDTO;
import com.nunes.ai_cv_analyzer.integration.gemini.prompt.PromptBuilder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class GeminiService {

    private final GeminiClient geminiClient;

    private final PromptBuilder promptBuilder;

    private final ObjectMapper objectMapper;

    public GeminiService(GeminiClient geminiClient,
                         PromptBuilder promptBuilder,
                         ObjectMapper objectMapper) {

        this.geminiClient = geminiClient;
        this.promptBuilder = promptBuilder;
        this.objectMapper = objectMapper;
    }

    public CVAnalysisDTO analyze(String cvText)  {

        String prompt = promptBuilder.buildPrompt(cvText);

        GeminiResponseDTO response =
                geminiClient.generateContent(prompt);

        String json = response.getCandidates()
                .getFirst()
                .getContent()
                .getParts()
                .getFirst()
                .getText();

        json = json.replace("```json", "")
                .replace("```", "")
                .trim();

        try {
            return objectMapper.readValue(json, CVAnalysisDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing Gemini response.", e);
        }
    }


}