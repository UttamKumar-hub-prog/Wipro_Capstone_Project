package com.wipro.NotificationService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import com.wipro.NotificationService.dto.PaymentEventDTO;

@Configuration
public class KafkaConfig {
	@Bean
	public ConsumerFactory<String, PaymentEventDTO> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service-group");

		// Use ErrorHandlingDeserializer to handle empty/invalid messages
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				org.apache.kafka.common.serialization.StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				org.springframework.kafka.support.serializer.ErrorHandlingDeserializer.class);

		// Delegate to JsonDeserializer
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wipro.NotificationService.dto");
		props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.wipro.NotificationService.dto.PaymentEventDTO");
		props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PaymentEventDTO> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, PaymentEventDTO> factory =
	            new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());

	    // Create a DefaultErrorHandler to handle bad messages
	    DefaultErrorHandler errorHandler = new DefaultErrorHandler(
	        (record, exception) -> {
	            // Handle bad message here
	            System.err.println("Failed to deserialize message: " + record.value() +
	                               ", exception: " + exception.getMessage());
	        },
	        new FixedBackOff(0L, 0L) // no retry, just skip bad message
	    );

	    factory.setCommonErrorHandler(errorHandler);
	    return factory;
	}
}