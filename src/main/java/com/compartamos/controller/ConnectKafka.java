package com.compartamos.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConnectKafka {

    private String bootstrapServers;
    private String username;
    private String password;
    private boolean useAuth;

    private KafkaProducer<String, String> producer;

    public ConnectKafka(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        this.useAuth = false;
        this.producer = createProducer();
    }

    public ConnectKafka(String bootstrapServers, String username, String password) {
        this.bootstrapServers = bootstrapServers;
        this.username = username;
        this.password = password;
        this.useAuth = true;
        this.producer = createProducer();
    }

    private KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        if (useAuth) {
            props.put("security.protocol", "SASL_PLAINTEXT");
            props.put("sasl.mechanism", "PLAIN");
            props.put("sasl.jaas.config",
                    "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                            + username + "\" password=\"" + password + "\";");
        }

        return new KafkaProducer<>(props);
    }

    /**
     * Envía un SDT representado como List<Map<String, String>> al topic indicado
     */
    public void sendSDT(String topic, List<Map<String, String>> sdt) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = mapper.writeValueAsString(sdt);

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, jsonMessage);
            producer.send(record);

            System.out.println("Enviado al topic [" + topic + "]: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (producer != null) {
            producer.close();
        }
    }
}
