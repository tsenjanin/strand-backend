package me.strand.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static me.strand.utils.constants.SystemVariables.*;

@Configuration
public class KafkaConfig {
//    @Bean
//    public KafkaAdmin kafkaAdmin(KafkaProperties kafkaProperties) {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.)
//
//        return admin;
//    }

    @Bean
    public NewTopic strand() {
        return TopicBuilder.name(KAFKA_TOPIC)
                .partitions(1)
                .replicas(3)
                .config(TopicConfig.RETENTION_MS_CONFIG, "16800000")
                .build();
    }
}
