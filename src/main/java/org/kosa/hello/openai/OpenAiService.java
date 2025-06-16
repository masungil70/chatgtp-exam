package org.kosa.hello.openai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiService {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.api-url}")
    private String apiUrl;

    public ChatResponse getChatResponse(ChatRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> httpEntity = new HttpEntity<>(request, headers);
        
        log.info("httpEntity = {}", httpEntity.toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
			log.info("보낼 JSON = {}", mapper.writeValueAsString(request));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ResponseEntity<ChatResponse> response = restTemplate.postForEntity(
                apiUrl, httpEntity, ChatResponse.class
        );

        return response.getBody();
    }
}