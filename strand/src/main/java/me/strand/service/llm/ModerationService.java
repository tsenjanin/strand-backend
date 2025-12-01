package me.strand.service.llm;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.*;
import lombok.RequiredArgsConstructor;
import me.strand.model.rest.response.ModerationResponse;
import org.springframework.stereotype.Service;

import static me.strand.utils.constants.SystemVariables.*;
import static me.strand.utils.objectmapper.ObjectMapperUtils.*;

@Service
@RequiredArgsConstructor
public class ModerationService {
    public ModerationResponse moderateContent(String content) {
        try {
            OpenAIClient client = OpenAIOkHttpClient.fromEnv();

            ResponseCreateParams params = ResponseCreateParams.builder()
                    .model(GLOBAL_OPENAI_MODEL)
                    .reasoning(GLOBAL_REASONING_EFFORT)
                    .instructions(MODERATION_RULESET)
                    .input(content)
                    .build();

            var response = client.responses().create(params);
            var responseOutputMessage = response.output().get(1).asMessage();
            var responseContent = responseOutputMessage.content();
            var responseOutputText = responseContent.getFirst().asOutputText();

            return convertJsonToObject(responseOutputText.text(), ModerationResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
