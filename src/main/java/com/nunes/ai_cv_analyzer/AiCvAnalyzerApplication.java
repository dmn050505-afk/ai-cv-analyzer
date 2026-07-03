package com.nunes.ai_cv_analyzer;

import com.nunes.ai_cv_analyzer.config.GeminiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(GeminiProperties.class)
@SpringBootApplication
public class AiCvAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCvAnalyzerApplication.class, args);
	}
}
