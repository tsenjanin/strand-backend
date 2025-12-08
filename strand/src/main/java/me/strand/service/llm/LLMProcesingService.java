package me.strand.service.llm;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.Reasoning;
import com.openai.models.responses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class LLMProcesingService {
    public <T> T processContent(String content, String instructions, Reasoning reasoning, Class<T> clazz) {
        try {
            OpenAIClient client = OpenAIOkHttpClient.fromEnv();

            ResponseCreateParams params = ResponseCreateParams.builder()
                    .model(GLOBAL_OPENAI_MODEL)
                    .reasoning(reasoning)
                    .instructions(instructions)
                    .input(content)
                    .build();

            var response = client.responses().create(params);
            var responseOutputMessage = response.output().get(1).asMessage();
            var responseContent = responseOutputMessage.content();
            var responseOutputText = responseContent.getFirst().asOutputText();

            return convertJsonToObject(responseOutputText.text(), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
