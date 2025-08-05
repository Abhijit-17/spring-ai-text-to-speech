package springai.text_to_speech.services;

public interface OpenAIService {

    byte[] getSpeech(String question);

}
