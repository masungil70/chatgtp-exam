package org.kosa.hello.openai;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * rest api로 호출 방법(콘솔에서 확인) 

curl -X POST http://localhost:8080/chat -H "Content-Type: application/json" -d "{\"model\": \"gpt-4o-mini\", \"messages\": [ {\"role\": \"user\", \"content\": \"대한민국의 수도는 어디인가요?\"}]}"
  
 * 
 */

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final OpenAiService openAiService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> chat(@RequestBody ChatRequest request) {
    	//request가 null이 잘못된 요청 오류 리턴 한다   
    	if (Objects.isNull(request)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	log.info("===========================");
    	log.info("request = {}", request);
    	ChatResponse response = openAiService.getChatResponse(request);
        return ResponseEntity.ok(
        		Map.of("status", "ok", 
        				"response", response));
    }
}