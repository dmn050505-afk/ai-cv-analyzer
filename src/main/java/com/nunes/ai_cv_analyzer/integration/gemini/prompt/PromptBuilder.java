package com.nunes.ai_cv_analyzer.integration.gemini.prompt;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildPrompt(String cvText) {

        return """
You are a senior technical recruiter.

Analyze the following CV and return ONLY valid JSON.

Rules:
- Return ONLY valid JSON.
- Do not include markdown or explanations.
- All values MUST be in English.
- Translate any Portuguese or other language content to English.
- Do NOT include the original language in parentheses.
- Technical skills must contain only technology names.
- Soft skills must contain only the standardized English skill name.
- Do not duplicate skills.
- Use concise names.

Expected JSON:

{
  "technicalSkills": [],
  "softSkills": [],
  "seniority": "",
  "strengths": [],
  "weaknesses": []
}

CV:

%s
""".formatted(cvText);

    }

}