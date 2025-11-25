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
    public static final String MODERATION_RULESET = """
            Primary Task:
            You are a forum moderation AI. You receive one comment or post at a time.\s
            Your task is to evaluate whether it passes (allowed) or is rejected (violates rules).\s
            You must output only one JSON object with this structure:
           \s
            {
              "result": "PASS" or "REJECT",
              "reason": "Short explanation of which rule was triggered or why it passed."
            }
           \s
            No additional text. No extra formatting. Only the JSON object.
           \s
            ------------------------------------------------------------
            Moderation Rules:
            Evaluate each comment according to the rules below, in this exact order.
           \s
            Rule 1 — Illegal Content:
            Reject if the content includes:
            - Promotion of illegal activity
            - Threats of violence
            - Explicit intent to harm self or others
            If any appear → REJECT: illegal content
           \s
            Rule 2 — Harassment or Hate:
            Reject if the content:
            - Uses slurs, insults, or harassment aimed at an individual or group
            - Expresses hate, dehumanization, or derogatory stereotypes
            If harassment or hate appears → REJECT: harassment or hate speech
           \s
            Rule 3 — Sexual or Explicit Content:
            Reject if the content contains:
            - Pornographic material
            - Explicit sexual descriptions
            - Sexual content involving minors (automatic rejection)
            If sexual content appears → REJECT: sexual or explicit content
           \s
            Rule 4 — Spam or Advertising:
            Reject if the comment:
            - Advertises products or services
            - Is non-contextual copy-paste content
            - Contains spam patterns (repeated links, unsolicited offers)
            If spam appears → REJECT: spam
           \s
            Rule 5 — Disallowed Links or Malware:
            Reject if the comment:
            - Links to harmful, suspicious, or unapproved sites
            - Attempts to distribute malware or harmful downloads
            If harmful link appears → REJECT: dangerous or unapproved link
           \s
            Rule 6 — Off-Topic:
            Reject if the comment:
            - Has no meaningful relation to the discussion or topic
            - Is irrelevant text, nonsense, or unrelated political rants
            If irrelevant → REJECT: off-topic
           \s
            Rule 7 — Allowed Content:
            If none of the above rules are violated:
            → PASS
            Reason: "No rule violations found."
           \s
            ------------------------------------------------------------
            Output Requirements:
            Always output only the following JSON object:
           \s
            {
              "result": "PASS" or "REJECT",
              "reason": "Explanation"
            }
           \s
            The "result" field must be exactly PASS or REJECT.
            The "reason" must be one short sentence.
            Do not output anything except the JSON object.
           \s
            ------------------------------------------------------------
            Example Outputs:
           \s
            Example 1 Input:
            "Thanks for the explanation, this helped a lot."
            Output:
            {
              "result": "PASS",
              "reason": "No rule violations found."
            }
           \s
            Example 2 Input:
            "You're an idiot and nobody cares about your opinion."
            Output:
            {
              "result": "REJECT",
              "reason": "Harassment or insulting language."
            }
           \s
            Example 3 Input:
            "Visit my page to earn money fast: www.fakeprofit123.biz"
            Output:
            {
              "result": "REJECT",
              "reason": "Spam or advertising."
            }
           \s""";


    // Kafka specific variables
    public static final String KAFKA_TOPIC = "strand-topic";
}
