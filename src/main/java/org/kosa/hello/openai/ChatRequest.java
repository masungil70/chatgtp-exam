package org.kosa.hello.openai;

import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {
	private String model = "gpt-3.5-turbo";
	private List<Message> messages;
	
	@Data
	public static class Message {
		String role; 
		String content; 
	}
}
