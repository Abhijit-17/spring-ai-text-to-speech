package springai.text_to_speech.services;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.Speech;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springai.text_to_speech.records.Question;
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Override
    public byte[] getSpeech(Question question) {
        //print the text to be converted to speech
        System.out.println("Converting to speech: " + question.question());
        //creating SpeechOptions with required parameters
        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY) //Voice: ALLOY, ECHO, FABLE, NOVA, ONYX, SHIMMER
                .speed(1.0f)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3) //Response format: MP3, WAV, OGG
                .model(OpenAiAudioApi.TtsModel.TTS_1.value) //Model: GPT_3_5_TURBO, GPT_4_TURBO
                .build();

        // creating speech prompt
        SpeechPrompt speechPrompt = new SpeechPrompt(question.question(), options);

        //creating speech response
        SpeechResponse speechResponse = openAiAudioSpeechModel.call(speechPrompt);

        // return the MP3 audio content as byte array

        //print the response details
        Speech result = speechResponse.getResult();

        // print metadata from the result
        System.out.println("Speech ID: " + result.getMetadata());


        return speechResponse.getResult().getOutput();

    }
}
