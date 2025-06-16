package org.kosa.hello.openai;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ChatResponse {
	private String id;
	private String object;
	private long created;
	private String model;
    private List<Choice> choices;

    @Data
    public static class Choice {
        private int index;
        private Message message;
        private Object logprobs;
        private String finish_reason;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
        private Object refusal;
        private List<Object> annotations;
    }
}
