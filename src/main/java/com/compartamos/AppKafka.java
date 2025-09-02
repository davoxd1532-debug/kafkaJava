package com.compartamos;

import com.compartamos.conf.ConfigServer;
import com.compartamos.controller.ConnectKafka;

import java.util.*;

public class AppKafka {
    public static void main(String[] args) {
        String topic = "test-topic";

        // Caso con usuario y password
        ConfigServer config = new ConfigServer("localhost", 29092, "user1", "pass1");

        // Caso sin autenticación
        // ConfigServer config = new ConfigServer("localhost", 29092, "", "");

        ConnectKafka kafka = new ConnectKafka(config);

        try {
            // Enviar mensajes simples
            for (int i = 1; i <= 3; i++) {
                kafka.sendMessage(topic, "Mensaje simple #" + i);
            }

            // Enviar un SDT simulado (List<Map<String, String>>)
            Map<String, String> campo1 = new HashMap<>();
            campo1.put("Descripcion", "Canal");
            campo1.put("Valor", "AGENCIA");

            Map<String, String> campo2 = new HashMap<>();
            campo2.put("Descripcion", "Monto");
            campo2.put("Valor", "1000");

            kafka.sendSDT(topic, Arrays.asList(campo1, campo2));

        } finally {
            kafka.close();
        }
    }
}