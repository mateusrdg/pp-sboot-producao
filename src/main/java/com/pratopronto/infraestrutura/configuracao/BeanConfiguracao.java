package com.pratopronto.infraestrutura.configuracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.dominio.adaptadores.services.ProducaoServiceImp;
import com.pratopronto.dominio.portas.interfaces.ProducaoServicePort;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    ProducaoServicePort pagamentoServicePort(QueueMessagingTemplate queueMessagingTemplate, ObjectMapper objectMapper) {
        return new ProducaoServiceImp(queueMessagingTemplate, objectMapper);
    }

}
