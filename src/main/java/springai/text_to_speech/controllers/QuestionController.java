package springai.text_to_speech.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springai.text_to_speech.services.OpenAIService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springai.text_to_speech.records.Question;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final OpenAIService openAIService;

    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/talk")
    public byte[] getSpeech(@RequestBody Question question) {
        return openAIService.getSpeech(question.question());
    }
    
    
}
