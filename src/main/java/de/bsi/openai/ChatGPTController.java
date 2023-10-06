package de.bsi.openai;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatGPTController {
    
    @PostMapping(path = "/")
    public ResponseEntity<String> chat(@RequestBody String message) {
        try {
            // Process the message (e.g., interact with OpenAI ChatGPT API)
            String response = chatWithGpt3(message);

            // Return the response data as JSON
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in communication with OpenAI ChatGPT API.");
        }
    }

    @Autowired
    private ObjectMapper jsonMapper;
    @Value("${openai.api_key}") private String openaiApiKey;
    private HttpClient client = HttpClient.newHttpClient();
    private static final URI CHATGPT_URI = URI.create("https://api.openai.com/v1/completions");

    private String chatWithGpt3(String message) throws Exception {
        var request = HttpRequest.newBuilder()
                .uri(CHATGPT_URI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(chatMessageAsPostBody(message))
                .build();
        var responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }

    private BodyPublisher chatMessageAsPostBody(String message) throws JsonProcessingException {
        var completion = CompletionRequest.defaultWith(message);
        return BodyPublishers.ofString(jsonMapper.writeValueAsString(completion));
    }
}
