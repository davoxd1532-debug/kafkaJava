package com.compartamos;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class AppKafkaProducerL {
    public static void main(String[] args) {
        String topic = "test-topic";

        // Configuración del producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // Seguridad SASL
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"user1\" password=\"pass1\";");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 1; i <= 5; i++) {
                String message = "Mensaje Kafka #" + i;
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
                producer.send(record);
                System.out.println("📤 Enviado: " + message);
            }
            System.out.println("Todos los mensajes fueron enviados.");
        }
    }
}
