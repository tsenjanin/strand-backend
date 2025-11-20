package me.strand.utils.constants;

import com.openai.models.Reasoning;
import com.openai.models.ReasoningEffort;

public class SystemVariables {
    
    private SystemVariables() {
    }

    // General system variables
    public static final Integer USER_POST_LIMIT = 5;
    public static final Integer POST_LIMIT_WINDOW_MIN = 1;
    public static final Integer LLM_RATE_LIMIT_COUNT = 10;
    public static final Integer LLM_RATE_LIMIT_WINDOW_MIN = 5;

    // OpenAI specific system variables
    public static final Reasoning GLOBAL_REASONING_EFFORT = Reasoning.builder().effort(ReasoningEffort.MINIMAL).build();
    public static final String GLOBAL_OPENAI_MODEL = "gpt-5-nano";

    // Moderation variables
    public static final String MODERATION_RULESET = "1.No offensive words.";

    // Kafka specific variables
    public static final String KAFKA_TOPIC = "strand";
}
