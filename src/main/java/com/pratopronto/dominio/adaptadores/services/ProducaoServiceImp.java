package com.pratopronto.dominio.adaptadores.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratopronto.aplicacao.adaptatores.controllers.exception.NotFoundException;
import com.pratopronto.dominio.Order;
import com.pratopronto.dominio.dtos.UpdateOrderDTO;
import com.pratopronto.dominio.portas.interfaces.ProducaoServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

public class ProducaoServiceImp implements ProducaoServicePort {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;
    public ProducaoServiceImp(QueueMessagingTemplate queueMessagingTemplate, ObjectMapper objectMapper) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void atualizarPedido(Long id, UpdateOrderDTO produtoDTO) throws NotFoundException {
        Order order = new Order();
        order.setId(id);
        order.setStatus(produtoDTO.getStatus());
        sendMessage(order);
    }

    @Override
    public void sendMessage(Order order) {
        try {
            queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(objectMapper.writeValueAsString(order)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
