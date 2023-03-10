package study;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create( kafkaAdmin.getConfigurationProperties() );
    }

    @Bean
    public NewTopic clip2() {
        // 토픽의 정보를 가져와서 설정하는 로직이 있다..
        return TopicBuilder.name("clip2-listener").build();
    }

    @Bean
    public NewTopics clip2s() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("clip2-part1").build(),
                TopicBuilder.name("clip2-part2").build(),
                TopicBuilder.name("clip2-part3").build(),
                TopicBuilder.name("clip2-part4")
                        .partitions(3)
                        .replicas(1)
                        .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000*60*60)) // 1시간 Retention Time
                        .build()
        );
    }

}
