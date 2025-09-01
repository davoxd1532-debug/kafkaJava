package com.compartamos;

public class AppKafkaProducer {

    public static void main(String[] args) {
        // Conexión sin credenciales
        //com.compartamos.controller.ConnectKafka connectKafka = new com.compartamos.controller.ConnectKafka("localhost:29092");

        // Si necesitas credenciales:
        com.compartamos.controller.ConnectKafka connectKafka = new com.compartamos.controller.ConnectKafka("localhost:29092", "user1", "pass1");

        String topic = "test-topic";

        // Simulación de un SDT de GeneXus como List<Map<String, String>>
        java.util.List<java.util.Map<String, String>> sdt = new java.util.ArrayList<>();

        java.util.Map<String, String> campo1 = new java.util.HashMap<>();
        campo1.put("Descripcion", "Canal");
        campo1.put("Valor", "AGENCIA");

        java.util.Map<String, String> campo2 = new java.util.HashMap<>();
        campo2.put("Descripcion", "Moneda");
        campo2.put("Valor", "PEN");

        sdt.add(campo1);
        //sdt.add(campo2);

        // Enviar SDT al topic
        connectKafka.sendSDT(topic, sdt);

        connectKafka.close();
    }
}