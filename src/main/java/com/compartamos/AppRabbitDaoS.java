package com.compartamos;

public class AppRabbitDaoS {
    public static void main(String[] args) {
        try {
            String host = "10.10.2.149";
            int port = 5672;
            String user = "guest";
            String password = "guest";

            // Crear conexión dentro del bloque try-with-resources
            try (com.rabbitmq.client.Connection connection = com.compartamos.controller.ConnectRabbitmq.createConnection2(host, port, user, password)) {
                System.out.println("Conexión establecida con éxito.");

                // Crear una instancia de RabbitConfig
                com.compartamos.conf.RabbitConfig config = new com.compartamos.conf.RabbitConfig("biometria2.queue", "biometria2.exchange", "biometria2.routingkey");

                // Crear una instancia de Estructura con datos
                com.compartamos.model.Estructura estructura = new com.compartamos.model.Estructura();
                estructura.setCanal("AGENCIA");
                estructura.setEmpresa(123);
                estructura.setSucursal(456);
                estructura.setModulo(789);
                estructura.setTransaccion(1011);
                estructura.setRelacion(2021);
                //estructura.setFecha("2024-09-19");
                estructura.setPais(604);
                estructura.setTipoDocumento(1);
                estructura.setNumeroDocumento("ABC123");
                estructura.setTipo("Factura");
                estructura.setSubTipo("Electronica");
                estructura.setHora("12:00:00");
                estructura.setHoraFirma("12:00:05");
                estructura.setWorkstation("WS01");
                estructura.setUsuario("usuario1");
                estructura.setUsuarioSucursal(555);
                estructura.setTrace("20240606180115222-RPUMA-SG2");
                estructura.setAux1("Auxiliar1");
                estructura.setAux2("Auxiliar2");
                estructura.setAux3("Auxiliar3");
                estructura.setAux4(99.99);

                // Publicar mensaje en la cola usando RabbitConfig y Estructura
                com.compartamos.controller.ConnectRabbitmq.publishMessage(connection, config, estructura);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
