package springai.text_to_speech.services;

import springai.text_to_speech.records.Question;

public interface OpenAIService {

    byte[] getSpeech(Question question);

}
