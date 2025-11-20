package me.strand.service.llm;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static me.strand.utils.constants.SystemVariables.*;

@Service
@RequiredArgsConstructor
public class ModerationService {

    // TODO: This should be in scheduler along with apache kafka.
    public String moderateContent(String content) {
        try {
            OpenAIClient client = OpenAIOkHttpClient.fromEnv();

            ResponseCreateParams params = ResponseCreateParams.builder()
                    .model(GLOBAL_OPENAI_MODEL)
                    .reasoning(GLOBAL_REASONING_EFFORT)
                    .instructions(MODERATION_RULESET)
                    .input(content)
                    .build();

            Response response = client.responses().create(params);

            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
